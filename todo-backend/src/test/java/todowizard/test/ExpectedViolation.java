package todowizard.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.rules.ExternalResource;

/**
 * Bean Validation を使って検証を行うためのルール
 * 
 * @author t_endo
 */
public class ExpectedViolation<T> extends ExternalResource {

    private Validator validator;

    private Set<ConstraintViolation<T>> actualViolations;

    @Override
    public void before() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        this.validator = factory.getValidator();
    }

    public ExpectedViolation<T> expect(T object) {
        actualViolations = validator.validate(object);
        return this;
    }

    public void beValid() {
        assertThat(actualViolations.size(), is(notNullValue()));
        assertThat(actualViolations.size(), is(0));
    }

    /**
     * 検証した結果のエラー内容に、引数で与えたプロパティが含まれているかを検証する。
     * 
     * @param property
     */
    public void hasErrorWith(String property) {
        assertThat(actualViolations.size(), is(notNullValue()));
        assertThat(actualViolations.size(), is(not(0)));

        List<String> errorPropertyPaths = actualViolations.stream()
                .map((violation) -> violation.getPropertyPath().toString())
                .collect(Collectors.toList());
        assertThat(errorPropertyPaths, hasItem(property));
    }
}

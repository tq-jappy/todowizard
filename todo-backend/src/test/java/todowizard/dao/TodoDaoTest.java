package todowizard.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import todowizard.entity.Todo;
import todowizard.test.TestConfig;
import todowizard.test.TestingDbResource;

/**
 * @author t_endo
 *
 */
public class TodoDaoTest {

    @Rule
    public final TestingDbResource dbResource = new TestingDbResource();

    private final TodoDao dao = new TodoDaoImpl(TestConfig.singleton());

    @Test
    public void selectById() {
        dbResource.execute(() -> {
            List<Todo> actual = dao.selectAll();

            assertThat(actual, is(notNullValue()));
            assertThat(actual.size(), is(3));
        });
    }
}

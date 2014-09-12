package todowizard.core.guice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.doma.jdbc.tx.TransactionAttribute;

/**
 * AOP を使ってトランザクション管理を行う場合に、トランザクションの境界を示すアノテーション。
 * 
 * @author t_endo
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DomaTransactional {

    TransactionAttribute attribute() default TransactionAttribute.REQURED;
}

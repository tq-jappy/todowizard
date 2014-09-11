package todowizard.core.guice;

import java.util.Objects;

import javax.ws.rs.Path;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * 
 * @author t_endo
 */
public class CommonModule extends AbstractModule {

    private final Config domaConfig;

    public CommonModule(Config domaConfig) {
        this.domaConfig = domaConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        Objects.requireNonNull(domaConfig, "domaConfig");

        bind(Config.class).toInstance(domaConfig);

        bind(TransactionManager.class).toInstance(
                domaConfig.getTransactionManager());

        MethodInterceptor interceptor = new DomaLocalTxInterceptor();
        requestInjection(interceptor);

        bindInterceptor(Matchers.annotatedWith(Path.class), Matchers.any(),
                interceptor);
        bindInterceptor(Matchers.annotatedWith(Transactional.class),
                Matchers.any(), interceptor);
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(Transactional.class), interceptor);
    }
}

package todowizard.core.guice;

import java.util.Objects;

import org.aopalliance.intercept.MethodInterceptor;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * Dropwizard, Guice, Doma を連携して便利に使う上で必要な共通の設定を行うモジュールクラス。
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

        // トランザクションマネージャ
        bind(TransactionManager.class).toInstance(
                domaConfig.getTransactionManager());

        // トランザクション AOP
        MethodInterceptor interceptor = new DomaLocalTxInterceptor();
        requestInjection(interceptor);

        bindInterceptor(Matchers.annotatedWith(Transactional.class),
                Matchers.any(), interceptor);
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(Transactional.class), interceptor);
    }
}

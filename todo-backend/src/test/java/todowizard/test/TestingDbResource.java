package todowizard.test;

import org.junit.rules.ExternalResource;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import todowizard.dao.AppDao;
import todowizard.dao.AppDaoImpl;

/**
 * 
 * @author t_endo
 */
public class TestingDbResource extends ExternalResource {

    private final TestConfig config;

    private final AppDao dao;

    private final TransactionManager tm;

    public TestingDbResource(TestConfig config) {
        this.config = config;
        this.dao = new AppDaoImpl(config);
        this.tm = config.getTransactionManager();
    }

    @Override
    protected void before() throws Throwable {
        tm.required(() -> {
            dao.create();
        });
    }

    @Override
    protected void after() {
        tm.required(() -> {
            dao.drop();
        });
    }

    public Config getConfig() {
        return config;
    }

    public void execute(Runnable runnable) {
        tm.required(runnable);
    }
}

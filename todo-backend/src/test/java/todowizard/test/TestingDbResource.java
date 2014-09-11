package todowizard.test;

import org.junit.rules.ExternalResource;
import org.seasar.doma.jdbc.tx.TransactionManager;

import todowizard.dao.AppDao;
import todowizard.dao.AppDaoImpl;

/**
 * 
 * @author t_endo
 */
public class TestingDbResource extends ExternalResource {

    private AppDao dao = new AppDaoImpl(TestConfig.singleton());

    private TransactionManager tm = TestConfig.singleton()
            .getTransactionManager();

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

    public void execute(Runnable runnable) {
        tm.required(runnable);
    }
}

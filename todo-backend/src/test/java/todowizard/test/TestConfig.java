package todowizard.test;

import javax.sql.DataSource;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * インメモリ H2 データベース用の設定
 * 
 * @author t_endo
 */
@SingletonConfig
public class TestConfig implements Config {

    private static final TestConfig CONFIG = new TestConfig();

    private final Dialect dialect;

    private final LocalTransactionDataSource dataSource;

    private final TransactionManager transactionManager;

    private TestConfig() {
        dialect = new H2Dialect();
        dataSource = new LocalTransactionDataSource(
                "jdbc:h2:mem:todo-test;DB_CLOSE_DELAY=1", "sa", null);
        transactionManager = new LocalTransactionManager(
                dataSource.getLocalTransaction(getJdbcLogger()));
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public static TestConfig singleton() {
        return CONFIG;
    }
}

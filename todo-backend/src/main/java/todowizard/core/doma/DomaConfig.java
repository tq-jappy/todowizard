package todowizard.core.doma;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * Dropwizard 上で Doma を利用する場合の Config
 * 
 * @author t_endo
 */
public class DomaConfig implements Config {

    protected final String dataSourceName;

    protected final DataSource originalDataSource;

    protected final LocalTransactionDataSource dataSource;

    protected final TransactionManager transactionManager;

    protected final Dialect dialect;

    public DomaConfig(String dataSourceName, DataSource dataSource,
            Dialect dialect) {
        this.dataSourceName = dataSourceName;
        this.originalDataSource = dataSource;
        this.dataSource = new LocalTransactionDataSource(dataSource);
        this.transactionManager = new LocalTransactionManager(
                this.dataSource.getLocalTransaction(getJdbcLogger()));
        this.dialect = dialect;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public String getDataSourceName() {
        return dataSourceName;
    }

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public DataSource getOriginalDataSource() {
        return originalDataSource;
    }
}
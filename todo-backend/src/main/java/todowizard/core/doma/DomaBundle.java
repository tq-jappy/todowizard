package todowizard.core.doma;

import static com.google.common.base.Preconditions.*;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.dialect.Db2Dialect;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.dialect.HsqldbDialect;
import org.seasar.doma.jdbc.dialect.MssqlDialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.dialect.OracleDialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.dialect.SqliteDialect;

/**
 * Doma2 用の Dropwizard バンドル
 * 
 * @author t_endo
 */
public abstract class DomaBundle<T extends Configuration> implements
        ConfiguredBundle<T>, DatabaseConfiguration<T> {

    private final String dataSourceName;

    private DomaConfig domaConfig;

    public DomaBundle(String dataSourceName) {
        this.dataSourceName = checkNotNull(dataSourceName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(T configuration, Environment environment) throws Exception {
        DataSourceFactory dataSourceFactory = getDataSourceFactory(configuration);
        DataSource dataSource = dataSourceFactory.build(environment.metrics(),
                "doma");

        this.domaConfig = new DomaConfig(dataSourceName, dataSource,
                inferDialectFromDriverClass(dataSourceFactory.getDriverClass()));
    }

    public DomaConfig getDomaConfig() {
        return domaConfig;
    }

    private static Dialect inferDialectFromDriverClass(String className) {
        if (className == null) {
            return null;
        }
        switch (className) {
        case "org.postgresql.Driver":
            return new PostgresDialect();
        case "org.h2.Driver":
            return new H2Dialect();
        case "com.mysql.jdbc.Driver":
            return new MysqlDialect();
        case "oracle.jdbc.driver.OracleDriver":
            return new OracleDialect();
        case "com.microsoft.sqlserver.jdbc.SQLServerDriver":
            return new MssqlDialect();
        case "com.ibm.db2.jcc.DB2Driver":
            return new Db2Dialect();
        case "org.hsqldb.jdbcDriver":
            return new HsqldbDialect();
        case "org.sqlite.JDBC":
            return new SqliteDialect();
        default:
            return null;
        }
    }
}

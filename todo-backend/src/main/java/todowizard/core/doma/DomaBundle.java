package todowizard.core.doma;

import static com.google.common.base.Preconditions.*;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.sql.DataSource;

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
                DialectUtil.inferDialect(dataSourceFactory.getDriverClass()));
    }

    public DomaConfig getDomaConfig() {
        return domaConfig;
    }
}

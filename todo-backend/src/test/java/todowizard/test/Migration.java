package todowizard.test;

import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.metrics.ConsoleReporterFactory;
import io.dropwizard.metrics.CsvReporterFactory;
import io.dropwizard.metrics.Slf4jReporterFactory;

import java.io.File;

import javax.validation.Validation;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;

import org.junit.rules.ExternalResource;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import todowizard.TodoConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.google.common.util.concurrent.UncheckedExecutionException;

/**
 * @author t_endo
 *
 */
public class Migration extends ExternalResource {

    final String configPath;

    TestConfig testConfig;

    Liquibase liquibase;

    TransactionManager transactionManager;

    public Migration(String configPath) {
        this.configPath = configPath;
    }

    @Override
    public void before() throws Exception {
        this.testConfig = createTestConfig();

        testConfig.getTransactionManager().requiresNew(
                () -> {
                    try {
                        DatabaseConnection dbconn = new JdbcConnection(
                                testConfig.getDataSource().getConnection());
                        Database database = DatabaseFactory.getInstance()
                                .findCorrectDatabaseImplementation(dbconn);
                        liquibase = new Liquibase("migrations.xml",
                                new ClassLoaderResourceAccessor(), database);

                        String context = null;
                        liquibase.update(context);
                    } catch (Throwable t) {
                        throw new UncheckedExecutionException(t);
                    }
                });
    }

    @Override
    @SneakyThrows
    public void after() {
        liquibase.dropAll();
    }

    public Config getConfig() {
        return testConfig;
    }

    public TransactionManager getTransactionManager() {
        return testConfig.getTransactionManager();
    }

    @SneakyThrows
    private TestConfig createTestConfig() {
        ObjectMapper mapper = Jackson.newObjectMapper();

        ConfigurationFactory<TodoConfiguration> factory = new ConfigurationFactory<>(
                TodoConfiguration.class, Validation
                        .buildDefaultValidatorFactory().getValidator(), mapper,
                "dw");

        // mapper.getSubtypeResolver().registerSubtypes(
        // ConsoleReporterFactory.class, CsvReporterFactory.class,
        // Slf4jReporterFactory.class);

        TodoConfiguration config = factory.build(new File(Resources
                .getResource(configPath).toURI()));

        DataSourceFactory dataSourceFactory = config.getDataSourceFactory();

        // dataSourceFactory.build(null, "doma");

        System.out.println("*******" + dataSourceFactory.getDriverClass());
        System.out.println(dataSourceFactory.getUrl());

        Class.forName(dataSourceFactory.getDriverClass());

        return new TestConfig(dataSourceFactory.getDriverClass(),
                dataSourceFactory.getUser(), dataSourceFactory.getPassword(),
                dataSourceFactory.getPassword());
    }
}

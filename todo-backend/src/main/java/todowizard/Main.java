package todowizard;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import todowizard.core.doma.DomaBundle;
import todowizard.core.doma.DomaConfig;
import todowizard.core.guice.CommonModule;
import todowizard.health.TodoHealthCheck;
import todowizard.resource.TodoResource;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * アプリケーションのメインクラス
 * 
 * @author t_endo
 */
public class Main extends Application<TodoConfiguration> {

    private DomaBundle<TodoConfiguration> domaBundle;

    /**
     * main
     * 
     * @param args
     *            "-h": show usage<br />
     *            "-v": show version<br />
     *            "server" to start server or "check" to check only
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        Main app = new Main();
        // app.run(args);
        String configPath = "config/config.yml";
        app.run(new String[] { "db", "migrate", configPath });
        app.run(new String[] { "server", configPath });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {
        // Doma
        this.domaBundle = new DomaBundle<TodoConfiguration>("doma") {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    TodoConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };
        bootstrap.addBundle(domaBundle);

        // Assets
        bootstrap.addBundle(new AssetsBundle("/app", "/todo", "index.html",
                "assets"));
        bootstrap.addBundle(new AssetsBundle("/bower_components",
                "/todo/bower_components", null, "bower_components"));

        // Migrations
        bootstrap.addBundle(new MigrationsBundle<TodoConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    TodoConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(TodoConfiguration configuration, Environment environment)
            throws Exception {
        DomaConfig domaConfig = domaBundle.getDomaConfig();

        Injector injector = Guice.createInjector(new CommonModule(domaConfig),
                new TodoModule());

        environment.jersey().register(injector.getInstance(TodoResource.class));

        environment.healthChecks().register("todo",
                injector.getInstance(TodoHealthCheck.class));
    }
}

package todowizard;

import static org.junit.Assume.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import todowizard.Main;

/**
 * @author t_endo
 *
 */
public class ManagerApplicationTest {

    @Rule
    public TestName testName = new TestName();

    final String test = "server";

    @Before
    public void setUp() {
        assumeTrue(testName.getMethodName().equals(test));
    }

    @Test
    public void check() throws Exception {
        Main app = new Main();
        app.run(new String[] { "check", "setting/configuration.yml" });
    }

    @Test
    public void server() throws Exception {
        Main app = new Main();
        app.run(new String[] { "server", "setting/configuration.yml" });
    }

    @Test
    public void dbmigrate() throws Exception {
        Main app = new Main();
        app.run(new String[] { "db", "migrate", // "--dry-run",
                "setting/configuration.yml" });
    }
}

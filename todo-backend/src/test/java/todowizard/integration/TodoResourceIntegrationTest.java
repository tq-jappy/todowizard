package todowizard.integration;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.ClassRule;
import org.junit.Test;

import todowizard.Main;
import todowizard.TodoConfiguration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * @author t_endo
 *
 */
public class TodoResourceIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<TodoConfiguration> rule = new DropwizardAppRule<>(
            Main.class, "src/test/resources/config-test.yml");

    @Test
    public void test() throws Exception {
        Client client = new Client();

        String url = String.format("http://localhost:%d/api/todos",
                rule.getLocalPort());

        ClientResponse response = client.resource(url)
                .get(ClientResponse.class);
        assertThat(response.getStatus(), is(200));
    }
}

package todowizard.integration;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.ClassRule;
import org.junit.Test;

import todowizard.Main;
import todowizard.TodoConfiguration;

import com.google.common.io.Resources;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author t_endo
 *
 */
public class TodoHealthCheckIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<TodoConfiguration> rule = new DropwizardAppRule<>(
            Main.class, Resources.getResource("config-test.yml").getPath());

    static Client client = new Client();

    @Test
    public void shouldBeSuccess() throws Exception {
        String url = String.format("http://localhost:%d/healthcheck",
                rule.getAdminPort());

        ClientResponse response = client.resource(url)
                .get(ClientResponse.class);
        assertThat(response.getStatus(), is(200));
    }
}

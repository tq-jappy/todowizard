package todowizard.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * 
 * @author t_endo
 */
public class TodoHealthCheck extends HealthCheck {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}

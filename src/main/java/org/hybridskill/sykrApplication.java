package org.hybridskill;

import org.hybridskill.health.AppHealthCheck;
import org.hybridskill.resources.Cryptography;
import org.hybridskill.resources.RestorRedis;

import com.codahale.metrics.health.HealthCheck;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class sykrApplication extends Application<sykrConfiguration> {

    public static void main(final String[] args) throws Exception {
        new sykrApplication().run(args);
    }

    @Override
    public String getName() {
        return "sykr";
    }

    @Override
    public void initialize(final Bootstrap<sykrConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final sykrConfiguration configuration,
                    final Environment environment) {
        final Cryptography resource = new Cryptography(configuration.getKeyLength(),configuration.getCypherAlgo(),configuration.getInitVector());
		environment.jersey().register(resource);
		final AppHealthCheck healthCheck =
		        new AppHealthCheck(configuration.getHealthCheck());
		    environment.healthChecks().register("healthCheck", healthCheck);
		    environment.jersey().register(resource);
    }

}

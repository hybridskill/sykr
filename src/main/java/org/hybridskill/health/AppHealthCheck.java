package org.hybridskill.health;

import com.codahale.metrics.health.HealthCheck;

public class AppHealthCheck extends HealthCheck {

	private String healthCheck;

	public AppHealthCheck(String healthCheck) {
	this.healthCheck = healthCheck;
	}

	@Override
	protected Result check() throws Exception {
		 final String status = String.format(healthCheck,"CHECK");
		 if (!status.contains("FINE")) {
	            return Result.unhealthy("SomeThing Went Wrong!");
	        }
	        return Result.healthy(status);
		
	}

}

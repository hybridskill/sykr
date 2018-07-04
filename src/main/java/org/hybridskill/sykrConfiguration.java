package org.hybridskill;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

import java.util.Map;

import javax.validation.constraints.*;

public class sykrConfiguration extends Configuration {
 
    @NotNull
    private int   keyLength ;
    @NotEmpty
    private String healthCheck;
    @NotEmpty
    private String cypherAlgo;
    @NotEmpty
    private String initVector;
    @JsonProperty
    public String getCypherAlgo() {
		return cypherAlgo;
	}
    @JsonProperty
	public String getInitVector() {
		return initVector;
	}

	public void setInitVector(String initVector) {
		this.initVector = initVector;
	}

	public void setCypherAlgo(String cypherAlgo) {
		this.cypherAlgo = cypherAlgo;
	}
	@JsonProperty
    public String getHealthCheck() {
		return healthCheck;
	}
	public void setHealthCheck(String healthCheck) {
		this.healthCheck = healthCheck;
	}
	@JsonProperty
	public int getKeyLength() {
		return keyLength;
	}
	public void setKeyLength(int keyLength) {
		this.keyLength = keyLength;
	}
	
 
}

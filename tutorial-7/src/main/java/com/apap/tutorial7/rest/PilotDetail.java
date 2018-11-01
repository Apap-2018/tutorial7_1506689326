package com.apap.tutorial7.rest;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * PilotDetail
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotDetail {
	private String Status;
	
	@JsonProperty("valid-until")
	private Date validUntil;
	
	public void setStatus(String status) {
		this.Status = status;
	}
	
	public void setValidUntil(Date validUntil) {
			this.validUntil = validUntil;
	}
	
	public Date getValidUntil() {
		return validUntil;
	}

	public String getStatus() {
		return Status;
	}

}

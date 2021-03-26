/**
 * 
 */
package com.mazhar.gateway.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author mazhar
 *
 * Dec 12, 2020
 */
@MappedSuperclass
public class BaseModel {
	
	@Column(name = "delete", nullable = false, columnDefinition = "boolean default false")
	private boolean deleted;

	@JsonIgnore
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}

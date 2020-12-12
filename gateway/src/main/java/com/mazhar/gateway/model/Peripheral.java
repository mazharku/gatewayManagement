/**
 * 
 */
package com.mazhar.gateway.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author mazhar
 *
 *         Dec 11, 2020
 */
@Entity
public class Peripheral extends BaseModel {

	@Id
	@GeneratedValue
	private int id;
	
	private String VendorName;

	@CreationTimestamp
	private Date createDate;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVendorName() {
		return VendorName;
	}

	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}

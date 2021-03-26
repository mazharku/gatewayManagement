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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((VendorName == null) ? 0 : VendorName.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + id;
		result = prime * result + (status ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peripheral other = (Peripheral) obj;
		if (VendorName == null) {
			if (other.VendorName != null)
				return false;
		} else if (!VendorName.equals(other.VendorName))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	

}

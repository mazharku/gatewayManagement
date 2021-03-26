/**
 * 
 */
package com.mazhar.gateway.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author mazhar
 *
 *         Dec 11, 2020
 */
@Entity
public class Gateway extends BaseModel{
	
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String serialNo;
	private String gateayName;
	private String ipAddress;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gateway_serial", referencedColumnName = "serialNo")
	private List<Peripheral> peripherals;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getGateayName() {
		return gateayName;
	}

	public void setGateayName(String gateayName) {
		this.gateayName = gateayName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public List<Peripheral> getPeripherals() {
		return peripherals;
	}

	public void setPeripherals(List<Peripheral> peripherals) {
		this.peripherals = peripherals;
	}

}

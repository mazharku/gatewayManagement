/**
 * 
 */
package com.mazhar.gateway;

import java.sql.Date;

import com.mazhar.gateway.util.GatewayUtil;



/**
 * @author mazhar
 *
 * Dec 13, 2020
 */

public class IPAddressValidation {
    public static String ips[] = {"192.168.0.102","192.168.0.256"};
	public static void main(String[] args) {
		/*
		 * for(String ip : ips) { System.out.println("ip address "+
		 * ip+" is "+GatewayUtil.isValidIPAddress(ip)); }
		 */
		System.out.println(GatewayUtil.isValidDateFormat("12-14-2020"));
		System.out.println(GatewayUtil.isValidDateFormat("2020-11-12"));
		
	}
	
}

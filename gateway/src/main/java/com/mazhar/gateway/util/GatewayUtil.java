/**
 * 
 */
package com.mazhar.gateway.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mazhar
 *
 * Dec 13, 2020
 */
public class GatewayUtil {

	public static boolean isValidIPAddress(String IPAddress) {
		String ipValidationRegex = "'\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b'";
		Pattern p = Pattern.compile(ipValidationRegex);
		if (IPAddress == null) { 
            return false; 
        } 
        Matcher m = p.matcher(IPAddress); 
        return m.matches(); 
		
	}
}

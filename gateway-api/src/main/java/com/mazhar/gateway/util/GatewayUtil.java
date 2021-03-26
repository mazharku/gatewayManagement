/**
 * 
 */
package com.mazhar.gateway.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * @author mazhar
 *
 * Dec 13, 2020
 */
public class GatewayUtil {
	private static final String IP_VALIDATION_REGEX = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static boolean isValidIPAddress(String IPAddress) {

		Pattern p = Pattern.compile(IP_VALIDATION_REGEX);
		if (IPAddress == null) {
			return false;
		}
		Matcher m = p.matcher(IPAddress);
		return m.matches();

	}
	
	public static boolean isValidDateFormat(String date) {
		if (date == null || date.isEmpty()) {
			return false;
		}
		try {
			LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));

			return true;

		} catch (Exception e) {
			return false;
		}

	}
}

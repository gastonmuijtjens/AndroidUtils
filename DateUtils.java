package com.company.product.models.utils;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Utilities class for handling dates
 *
 * @author Gaston Muijtjens
 * @since 23-06-17
 */
public class DateUtils {
	
	/**
	 * This function will return a new Calendar object from a given input with the given days added
	 *
	 * @param input The input to add the days to
	 * @param days  The days to add to the input
	 * @return New Calendar object with the days added to the input
	 */
	public static Calendar addDays(@NonNull Calendar input, int days) {
		return add(input, days, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * This function will return a new Calendar object from a given input with the given hours added
	 *
	 * @param input The input to add the hours to
	 * @param hours The hours to add to the input
	 * @return New Calendar object with the hours added to the input
	 */
	public static Calendar addHours(@NonNull Calendar input, int hours) {
		return add(input, hours, Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * This function will return a new Calendar object from a given input with the given minutes added
	 *
	 * @param input   The input to add the minutes to
	 * @param minutes The minutes to add to the input
	 * @return New Calendar object with the minutes added to the input
	 */
	public static Calendar addMinutes(@NonNull Calendar input, int minutes) {
		return add(input, minutes, Calendar.MINUTE);
	}
	
	private static Calendar add(@NonNull Calendar input, int amount, int field) {
		Calendar output = GregorianCalendar.getInstance();
		output.setTime(input.getTime());
		output.add(field, amount);
		return output;
	}
	
	/**
	 * This function will return whether two days are the same day
	 *
	 * @param first  The first date to check
	 * @param second The second date to check
	 * @return Boolean indicating whether the date is on the same day or not
	 */
	public static boolean isSameDay(@NonNull Calendar first, @NonNull Calendar second) {
		return first.get(Calendar.YEAR) == second.get(Calendar.YEAR) && first.get(Calendar.MONTH) == second.get(Calendar.MONTH) && first.get(Calendar.DAY_OF_MONTH) == second.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * This function will replace a given date into the a date format (dd-MM-yyyy) as a String
	 *
	 * @param date The date that needs to be formatted
	 * @return String containing the formatted date
	 */
	public static String getDateFormat(Calendar date) {
		return getDateTimeFormat(date, "dd-MM-yyyy");
	}
	
	/**
	 * This function will replace a given date into a date format including time (dd-mm-YYYY hh:mm:ss) as a String
	 *
	 * @param date The date that needs to be formatted
	 * @return String containing the formatted date and time
	 */
	public static String getDateTimeFormat(Calendar date) {
		return getDateTimeFormat(date, "yyyy-MM-dd") + "T" + getDateTimeFormat(date, "hh:mm:ss");
	}
	
	/**
	 * This function will replace a given date into the given format as a String
	 *
	 * @param date   The date that needs to be formatted
	 * @param format Format to use to format the date
	 * @return String containing the formatted date
	 */
	private static String getDateTimeFormat(Calendar date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.US);
		formatter.setTimeZone(date.getTimeZone());
		return formatter.format(date.getTime());
	}
	
	/**
	 * This function will convert a date and time string to a Calendar object
	 *
	 * @param date The string to convert, has to be format YYYY-dd-MMTmm:ss:MM. If incorrect format, an IllegalArgumentException will be thrown
	 * @return Date string converted to Calendar object
	 * @throws IllegalArgumentException If an incorrect input format is provided
	 */
	public static Calendar convertDateTime(@NonNull String date) {
		return convertDateTime(date, "dd-MM-yyyy'T'hh:mm");
	}
	
	/**
	 * This function will convert a date string to a Calendar object
	 *
	 * @param date The string to convert, has to be format YYYY-dd-MMTmm:ss:MM. If incorrect format, an IllegalArgumentException will be thrown
	 * @return Date string converted to Calendar object
	 * @throws IllegalArgumentException If an incorrect input format is provided
	 */
	public static Calendar convertDate(@NonNull String date) {
		return convertDateTime(date, "dd-MM-yyyy");
	}
	
	private static Calendar convertDateTime(@NonNull String date, @NonNull String pattern) {
		DateFormat dateFormatter = new SimpleDateFormat(pattern, Locale.US);
		
		try {
			Date dateObject = dateFormatter.parse(date);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(dateObject);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid input format");
		}
	}
}

package se.arctisys.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

	public static String dateToString(Date date) {
		String result = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return result;		
	}
	public static String dateAndTimeToString(Date date) {
		String result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
		return result;		
	}
	public static Date stringToDate(String dateStr) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dateStr);
		return date;		
	}
	public static Date stringToDateAndTime(String dateStr) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = format.parse(dateStr);
		return date;		
	}
	public static Double stringToDouble(String value) {
		Double doubleValue = 0.0;
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			Number number = format.parse(value);
			doubleValue = number.doubleValue();
		} catch (ParseException e) {
		}
		return doubleValue;
	}
	public static boolean isToday(Date date) {
		boolean result = false;
		String today = dateToString(new Date());
		String datePar = dateToString(date);
		if (today.equals(datePar)) {
			result = true;
		}
		return result;
	}
	public static String shortDateStringToDateString(String shortDate) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyMMdd");		
		Date date = format.parse(shortDate);
		return dateToString(date);		
	}
	public static Date getDateByDaysBack(Long daysBack) throws ParseException {
		return getDateByDaysBack(daysBack, new Date());
	}

	public static Date getDateByDaysBack(Long daysBack, Date startDate) throws ParseException {
		Date before = new Date(startDate.getTime() - daysBack * 24 * 3600 * 1000); //Subtract n days  
		return before;
	}
	
	public static Double round(Double value, Integer places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    Long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public static Long daysBetweenDates(Date firstDate, Date lastDate) {
	    return (long)( (lastDate.getTime() - firstDate.getTime()) / (1000 * 60 * 60 * 24));
	}
	public static Long daysBeforeNow(Date date) {
	    return (long)( (new Date().getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
	}
	
}

package se.arctisys;

import java.util.Calendar;
import java.util.List;

import se.arctisys.util.Util;
import se.arctisys.yahoofinance.QuotesRequest;
import se.arctisys.yahoofinance.Quote;
import se.arctisys.yahoofinance.QueryInterval;

public class YahooTester {

	private static String QUOTE_URL = "https://query2.finance.yahoo.com/v8/finance/chart/";
	private static String HISTORY_QUOTE_URL = "";
	
	private static void getHistory() {
		try {
			Calendar today = Calendar.getInstance();
			Calendar start = Util.getYearsFromNow(1);
			QuotesRequest request = new QuotesRequest("SEB-A.ST", start, today, QueryInterval.DAILY);
			List<Quote> quotes = request.getResult();
			for (Quote quote : quotes) {
				System.out.println(quote.toString());
			}
		} catch (Exception e) {
		}
	}

	private static void getCurrent() {
		
		try {
			//Calendar yesterday = Util.getMinutesFromNow(1440);
			Calendar yesterday = Util.getMinutesFromNow(5);
			
			Calendar tomorrow = Util.getTomorrow();
			//Calendar today = Calendar.getInstance();
			//HistQuotesQuery2V8Request request = new HistQuotesQuery2V8Request("SEB-A.ST", yesterday, today, QueryInterval.DAILY);
			QuotesRequest request = new QuotesRequest("ACAN-B.ST", yesterday, tomorrow, QueryInterval.MINUTE);
			System.out.println("Test 1");
			List<Quote> quotes = request.getResult();
			System.out.println("Test 2");
			for (Quote quote : quotes) {
				System.out.println(quote.toString());
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	private static void getCurrentDay() {
		
		try {
			//Calendar yesterday = Util.getMinutesFromNow(1440);
			Calendar yesterday = Util.getDaysFromNow(1);
			
			Calendar tomorrow = Util.getTomorrow();
			//Calendar today = Calendar.getInstance();
			//HistQuotesQuery2V8Request request = new HistQuotesQuery2V8Request("SEB-A.ST", yesterday, today, QueryInterval.DAILY);
			QuotesRequest request = new QuotesRequest("ACAN-B.ST", yesterday, tomorrow, QueryInterval.DAILY);
			System.out.println("Test 1");
			List<Quote> quotes = request.getResult();
			System.out.println("Test 2");
			for (Quote quote : quotes) {
				System.out.println(quote.toString());
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getCurrentDay();
		//getHistory();		
	}

}

package se.arctisys;

import java.util.Calendar;
import java.util.List;

import se.arctisys.util.Util;
import se.arctisys.yahoofinance.HistQuotesQuery2V8Request;
import se.arctisys.yahoofinance.HistoricalQuote;
import se.arctisys.yahoofinance.QueryInterval;

public class YahooTester {

	private static String QUOTE_URL = "https://query2.finance.yahoo.com/v8/finance/chart/";
	private static String HISTORY_QUOTE_URL = "";
	
	private static void getHistory() {
		try {
			Calendar today = Calendar.getInstance();
			Calendar start = Util.getYearsFromNow(1);
			HistQuotesQuery2V8Request request = new HistQuotesQuery2V8Request("SEB-A.ST", start, today, QueryInterval.DAILY);
			List<HistoricalQuote> quotes = request.getResult();
			for (HistoricalQuote quote : quotes) {
				System.out.println(quote.toString());
			}
		} catch (Exception e) {
		}
	}

	private static void getCurrent() {
		
		try {
			Calendar yesterday = Util.getDaysFromNow(7);
			Calendar today = Calendar.getInstance();
			//HistQuotesQuery2V8Request request = new HistQuotesQuery2V8Request("SEB-A.ST", yesterday, today, QueryInterval.DAILY);
			HistQuotesQuery2V8Request request = new HistQuotesQuery2V8Request("ACAN-B.ST", yesterday, today, QueryInterval.MINUTE);
			List<HistoricalQuote> quotes = request.getResult();
			for (HistoricalQuote quote : quotes) {
				System.out.println(quote.toString());
			}
		} catch (Exception e) {
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getCurrent();
		//getHistory();		
	}

}

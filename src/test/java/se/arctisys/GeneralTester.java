package se.arctisys;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GeneralTester {

	public static void main(String[] args) {		
		
		Calendar calendar = new GregorianCalendar(2017,0,1);
		//Stock stock = YahooFinance.get("ABB.ST", calendar, Interval.DAILY);
		
		//,1511276040,1511276100,1511276160
		java.util.Date time=new java.util.Date((long)1511276040*1000);
		System.out.println(time);
		time=new java.util.Date((long)1511276100*1000);
		System.out.println(time);
		time=new java.util.Date((long)1511276160*1000);
		System.out.println(time);
		
	}
		
		/*
		try {
			Document doc = Jsoup.connect("http://www.di.se/borssidor/large-cap/").get();
			Element table = doc.select("table.fh-table-strip").get(0); //select the table.
			for (Element row : table.select("tr")) {
				Elements tds = row.select("td");
				if (tds.size() > 2) {						
					System.out.println("Aktie: " + tds.get(0).text());
					System.out.println("Senaste: " + tds.get(1).text());
					System.out.println("%: " + Util.stringToDouble(tds.get(2).text(), Locale.FRANCE));
					System.out.println("+/-: " + tds.get(3).text());
					System.out.println("Köp: " + tds.get(4).text());
					System.out.println("Sälj: " + tds.get(5).text());
					System.out.println("Högst: " + tds.get(6).text());
					System.out.println("Lägst: " + tds.get(7).text());
					System.out.println("Årsskifte %: " + tds.get(8).text());
					System.out.println("ATH: " + tds.get(9).text());
					System.out.println("Datum: " + tds.get(10).text());
					System.out.println("Tid: " + tds.get(11).text());
					System.out.println("-------------------------------------");
				}
			}
			System.out.println(Util.shortDateStringToDateString("150701"));
		} catch (IOException e) {
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*
		
	}
		/*
		
		String file = "ERIC_A-2015-03-14.csv";
		Integer idx = file.indexOf('-');
		System.out.println(file.substring(0, idx));

		try {
			WebClient webClient = new WebClient();
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getCookieManager().setCookiesEnabled(true);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getCookieManager().setCookiesEnabled(true);
			Page page = webClient.getPage("http://www.di.se/borssidor/large-cap/");
			WebResponse response = page.getWebResponse();
			String content = response.getContentAsString();
			System.out.println(content);		
			//printPageContent();
			
			
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	*/
	/*
	private static void printPageContent(){
		try {
			WebClient webClient = new WebClient();
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getCookieManager().setCookiesEnabled(true);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getCookieManager().setCookiesEnabled(true);
			HtmlPage page = null;
			try {
			    page = webClient.getPage("http://www.nasdaqomxnordic.com/aktier");
			} catch (Exception e) {
			    System.out.println("Get page error");
			}
			JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();
			while (manager.getJobCount() > 0) {
			    Thread.sleep(1000);
			}
			System.out.println(page.asXml());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	/*
	private static void printPageContent2(){
		client.open("GET", reportUrl, true);
		client.onreadystatechange = responseListener;
		client.send();

		var reportContents = document.getElementById("reportContents");
		// Since this could considerably slow browsers for large reports,
		// don't edit report contents for every readystate equal to 3.
		// Do it every 10.
		var batchUpdate = 10;
		var responsesSinceUpdate = 0;
		// Don't update the whole contents, just add the new stuff
		// since the last update.
		var currentLengthOfReportHtml = 0;
		// Have max recommended length of report before showing warning.
		var maxRecommendedReportLength = 500000;

		function responseListener()
		{
		    if (this.status == 200)
		    {
		        var readyState = this.readyState;

		        if (readyState == 3 || readyState == 4)
		        {
		            var updatePage = false;

		            if (readyState == 4)
		            {
		                updatePage = true;
		                var loadingDiv = document.getElementById("reportLoading");
		                loadingDiv.innerHTML = "";
		                toggleLargeReportWarning(false);
		            }
		            else
		            {
		                responsesSinceUpdate++;

		                if (responsesSinceUpdate > batchUpdate)
		                {
		                    updatePage = true;
		                    responsesSinceUpdate = 0;
		                }
		            }

		            if (updatePage)
		            {
		                var reportLength = this.responseText.length;
		                reportContents.innerHTML += this.responseText.substring(currentLengthOfReportHtml);
		                currentLengthOfReportHtml = reportLength;

		                if (reportLength > maxRecommendedReportLength && readyState == 3)
		                {
		                    toggleLargeReportWarning(true);
		                }
		            }
		        }
		    }
		}
	}
	*/

}

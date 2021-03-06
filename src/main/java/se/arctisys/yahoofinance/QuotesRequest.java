package se.arctisys.yahoofinance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Stijn Strickx
 */
public class QuotesRequest {

    private static final Logger log = LoggerFactory.getLogger(QuotesRequest.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final int CONNECTION_TIMEOUT = 10000;
    private final String symbol;
    private final Calendar from;
    private final Calendar to;
    private final QueryInterval interval;
    private final String quotesBaseUrl;

    public static final Calendar DEFAULT_FROM = Calendar.getInstance();

    static {
        DEFAULT_FROM.add(Calendar.YEAR, -1);
    }
    public static final Calendar DEFAULT_TO = Calendar.getInstance();
    public static final QueryInterval DEFAULT_INTERVAL = QueryInterval.MONTHLY;

    public QuotesRequest(String symbol, Calendar from, Calendar to, QueryInterval interval, String quotesBaseUrl) {
        this.symbol = symbol;
        this.from = this.cleanHistCalendar(from);
        this.to = this.cleanHistCalendar(to);
        this.interval = interval;
        this.quotesBaseUrl = quotesBaseUrl;
    }

    /**
     * Put everything smaller than days at 0
     * @param cal calendar to be cleaned
     */
    private Calendar cleanHistCalendar(Calendar cal) {
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        return cal;
    }

    public List<Quote> getResult() throws IOException {
        String json = getJson();
        JsonNode resultNode = objectMapper.readTree(json).get("chart").get("result").get(0);
        JsonNode timestamps = resultNode.get("timestamp");
        JsonNode indicators = resultNode.get("indicators");
        JsonNode quotes = indicators.get("quote").get(0);
        JsonNode closes = quotes.get("close");
        JsonNode volumes = quotes.get("volume");
        JsonNode opens = quotes.get("open");
        JsonNode highs = quotes.get("high");
        JsonNode lows = quotes.get("low");
        //JsonNode adjCloses = indicators.get("adjclose").get(0).get("adjclose");
        //JsonNode adjCloses = indicators.get("quote").get(0);

        List<Quote> result = new ArrayList<Quote>();
        for (int i = 0; i < timestamps.size(); i++) {
            try {
				long timestamp = timestamps.get(i).asLong();
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timestamp * 1000);
				//BigDecimal adjClose = adjCloses.get(i).decimalValue();
				long volume = volumes.get(i).asLong();
				BigDecimal open = opens.get(i).decimalValue();
				BigDecimal high = highs.get(i).decimalValue();
				BigDecimal low = lows.get(i).decimalValue();
				BigDecimal close = closes.get(i).decimalValue();

				Quote quote = new Quote(
				    symbol,
				    calendar,
				    open,
				    low,
				    high,
				    close,
				    close,
				    volume);
				result.add(quote);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return result;
    }

    public String getJson() throws IOException {

        if(this.from.after(this.to)) {
            log.warn("Unable to retrieve historical quotes. "
                    + "From-date should not be after to-date. From: "
                    + this.from.getTime() + ", to: " + this.to.getTime());
            return "";
        }

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("period1", String.valueOf(this.from.getTimeInMillis() / 1000));
        params.put("period2", String.valueOf(this.to.getTimeInMillis() / 1000));
        if (this.interval != null) {
        	params.put("interval", this.interval.getTag());
        }
        params.put("events", "div|split");

        String url = quotesBaseUrl + URLEncoder.encode(this.symbol , "UTF-8") + "?" + getURLParameters(params);

        // Get CSV from Yahoo
        log.info("Sending request: " + url);

        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(CONNECTION_TIMEOUT);
        URLConnection connection = redirectableRequest.openConnection();

        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        StringBuilder builder = new StringBuilder();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(line);
        }
        return builder.toString();
    }
    private String getURLParameters(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        for (Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                key = URLEncoder.encode(key, "UTF-8");
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                log.error(ex.getMessage(), ex);
                // Still try to continue with unencoded values
            }
            sb.append(String.format("%s=%s", key, value));
        }
        return sb.toString();
    }

}

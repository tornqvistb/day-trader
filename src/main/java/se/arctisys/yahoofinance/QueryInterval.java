
package se.arctisys.yahoofinance;

/**
 *
 * @author Stijn Strickx
 */
public enum QueryInterval {
    
    DAILY("1d"),
    WEEKLY("5d"),
    MONTHLY("1mo"),
	MINUTE("1m");
    
    private final String tag;
    
    QueryInterval(String tag) {
        this.tag = tag;
    }
    
    public String getTag() {
        return this.tag;
    }
    
}

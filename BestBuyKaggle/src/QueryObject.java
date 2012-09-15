import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


public class QueryObject {

	private String userID;
	private String sku;
	private String category;
	private String query;
	private Date clickTime;
	private Date queryTime;
	private HashMap<String, Object> featureHash = new HashMap<String, Object>();
	
	public QueryObject(String userid, String Sku, String Category, String Query, String cT, String qT){
		userID = userid;
		sku = Sku;
		category = Category;
		query = Query;
		setClickTime(cT);
		setQueryTime(qT);
		
		queryToClickDifference();
	}
	
	public void setUserID(String s){
		userID = s;
	}
	
	public String getUserID(){
		return userID;
	}
	
	private Date getTimeFromString(String string) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		
		// replace with your start date string
		Date d = null;
		try{
			d = df.parse(string);
		} catch (ParseException parseException){
			//System.out.println(parseException.getLocalizedMessage());
		}
		return d; 
	}
	private void queryToClickDifference(){
		if(queryTime != null && clickTime != null){
			Long s =  clickTime.getTime()-queryTime.getTime();
			featureHash.put("queryClickDifference", s);
		}
	}

	
	// Get/Set
	private void setClickTime(String s) {
		clickTime = getTimeFromString(s);

	}
	private void setQueryTime(String s) {
		queryTime = getTimeFromString(s);
	}
	
	public long getClickTime(){
		return clickTime.getTime();
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public HashMap<String, Object> getFeatures(){
		return featureHash;
	}
	
	public Object getFeature(String feature){
		return featureHash.get(feature);
	}
	

	
	
}

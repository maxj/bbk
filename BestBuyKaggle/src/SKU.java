import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class SKU {

	private String sku;
	private List<String> queries = new ArrayList<String>();
	private List<KeyValue> counts = new ArrayList<KeyValue>();
	
	
	
	public SKU (){
		sku = null;
	}
	
	public SKU (String s, String query){
		sku = s;
		queries.add(query);
	}
	public void addQuery(String query){
		queries.add(query);
	}
	public String getSKU(){
		return sku;
	}
	public List<String> getQueries(){
		return queries;
	}
	
	private void findWordFrequency(){
		for(String s : queries){
			String[] tokens = s.split(" ");
			for(String st : tokens){
				boolean newkv = true;
				for(KeyValue kv : counts){
					if(kv.getString().equalsIgnoreCase(st)){
						kv.increase();
						newkv = false;
					}
				}
				if(newkv){
					counts.add(new KeyValue(st));
				}

			}
		}
	}
	
	public String findMostFrequentWord(){
		findWordFrequency();
		int maxCount = 0;
		String maxString = "";
		for(KeyValue kv : counts){
			if(kv.getCount() > maxCount){
				maxString = kv.getString();
				maxCount = kv.getCount();
			}
		}
		return maxString;
	}
	
}

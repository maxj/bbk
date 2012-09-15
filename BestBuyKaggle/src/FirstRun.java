import java.io.File;
import java.util.Date;
import java.util.List;


public class FirstRun {

	public static void main(String[] args) {
	
		// Load in training data
		File file = new File("train.csv");
		List<QueryObject> QueryObjects = DataLoad.readCSVbyLine(file);
		
	
		Long diff = FeatureUtility.getAverageClickDifference(QueryObjects);
		Date secondsDifference = new Date(diff);
		
		System.out.println("Average time between query and click: " + secondsDifference.getSeconds() + " seconds.");
		System.out.println("Total Records: " + QueryObjects.size());
		
		//
		// Now that we have an average time - can I use that as an accuracy measurement?
		//
		QueryObjects = FeatureUtility.thinByClickTimeThreshold(QueryObjects, diff);
		diff = FeatureUtility.getAverageClickDifference(QueryObjects);
		List<SKU> SKUs = FeatureUtility.findSKUs(QueryObjects);
		secondsDifference = new Date(diff);
		System.out.println(" :: After thinning ::");
		System.out.println("Average time between query and click: " + secondsDifference.getSeconds() + " seconds.");
		System.out.println("Total Records: " + QueryObjects.size());
		System.out.println("Unique SKUs: " + SKUs.size());
		
		System.out.println("");
		System.out.println("");
		
		
		// Lets look into which queries mapped to which SKUs
		for(int i =0; i<3; i++){
			SKU Sku = SKUs.get(i);
			Sku.findMostFrequentWord();
			StringBuilder sb = new StringBuilder();
			for(String s : Sku.getQueries()){
				sb.append(s + ", ");
			}
			System.out.println("SKU: " + Sku.getSKU());
			System.out.println(sb.toString());
			System.out.println("Most Common word: " + Sku.findMostFrequentWord());
			System.out.println();
		}
	
	}
	
}

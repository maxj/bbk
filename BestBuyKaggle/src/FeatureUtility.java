import java.util.ArrayList;
import java.util.List;


public class FeatureUtility {

	
	public static List<SKU> findSKUs(List<QueryObject> objects){
		List<SKU> SKUs = new ArrayList<SKU>();
		boolean newSKU = true;
		for(QueryObject qo : objects){
			newSKU = true;
			for(SKU sku : SKUs){
				if(sku.getSKU().equalsIgnoreCase(qo.getSku())){
					sku.addQuery(qo.getQuery());
					newSKU = false;
				}
			}
			if(newSKU){
				SKUs.add(new SKU(qo.getSku(), qo.getQuery()));
			}			
		}
		return SKUs;
	}
	
	public static Long getAverageClickDifference(List<QueryObject> list){
		Long sum = new Long(0);
		Long count = new Long(0);
		for(QueryObject qo : list){
			if(qo.getFeature("queryClickDifference")!=null){
				sum = sum + (Long) qo.getFeature("queryClickDifference");
				count = count + 1;
			}
		}
		return (sum/count);
	}
	
	public static List<QueryObject> thinByClickTimeThreshold(List<QueryObject> objects, Long threshold){
		List<QueryObject> thinnedList = new ArrayList<QueryObject>();  
		for(QueryObject qo : objects){
			if(qo.getFeature("queryClickDifference")!=null && (Long)qo.getFeature("queryClickDifference") < threshold ){
				thinnedList.add(qo);
			}
		}
		return thinnedList;
	}

}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DataLoad {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataLoad dl = new DataLoad();
		System.out.println("RUNNING");
		File file = new File("train.csv");
		List<QueryObject> QueryObjects = dl.readCSVbyLine(file);
		Date d = dl.getAverageClickDifference(QueryObjects);
		System.out.println("Average time between query and click: " + d.getSeconds() + " seconds.");
		
	}
	
	public List<QueryObject> readCSVbyLine(File f){
		 String[] out = null;
		 List<QueryObject> objects = new ArrayList<QueryObject>();
		if (!f.exists() && f.length() < 0) {
             System.out.println("The specified file does not exist");
     	} 
		else {
			try{
     			FileReader fr = new FileReader(f);
     			BufferedReader reader = new BufferedReader(fr);
     			String st = "";
     			int limit = 0;
     			
     			while ((st = reader.readLine()) != null) {
                   //  System.out.println(st);
     				out = st.split(",");
                    QueryObject qo;
                    if(limit>0){
                    		out[4] = out[4].replaceAll("(^\")|(\"$)","");
                    		out[5] = out[5].replaceAll("(^\")|(\"$)","");
                    		qo = new QueryObject(out[0], out[1], out[2], out[3], out[4], out[5]);
                    		objects.add(qo);
                    }
                    limit++;   
     			} 
     		 } catch (FileNotFoundException fileNotFoundException){
     			 System.out.println(fileNotFoundException.getLocalizedMessage());     			
     		 } catch (IOException ioException){
     			 System.out.println(ioException.getLocalizedMessage());
     		 }
		}
		System.out.println(objects.size());
		System.out.println(objects.get(0).getUserID());
		System.out.println(objects.get(0).getQuery());
		return objects; 	
	}
	
	public Date getAverageClickDifference(List<QueryObject> list){
		Long sum = new Long(0);
		Long count = new Long(0);
		for(QueryObject qo : list){
			if(qo.getFeature("queryClickDifference")!=null){
				sum = sum + (Long) qo.getFeature("queryClickDifference");
				count = count + 1;
			}
		}
		return new Date(sum/count);
	}
	
	
	
	
	
	
	
}

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
	
	public static List<QueryObject> readCSVbyLine(File f){
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
                    		out[3] = out[3].replaceAll("(^\")|(\"$)","");
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
		return objects; 	
	}
	
	public static List<String> uniqueSKUs(List<QueryObject> objects){
		List<String> SKUs = new ArrayList<String>();
		for(QueryObject qo : objects){
			if(!SKUs.contains(qo.getSku())){
				SKUs.add(qo.getSku());
			}
			
		}
		return SKUs;
	}
	


	
	
	
}

package info.kodelogic.apptegy;
import java.util.ArrayList;
import org.json.JSONArray;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ParentOrg extends Activity{

	final String TAG_NAME = "name"; 
	String[] items;
	private ArrayList<String> arrayItems = new ArrayList<String>();
	ListView lst;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parent_org);
		lst = (ListView)findViewById(R.id.lstParent);
		
		JSONParser jsonParser = new JSONParser();
				String url="http://www.kodelogic.info/api/v1/parent_organizations.js";
				String token="UpXCZdTsPuzayVfHdaDg";
				String result = jsonParser.getJSONFromUrl(url,token);
		try
		{
			 JSONArray array = new JSONArray(result);
	            
	            for (int i = 0; i< array.length(); i++) {
	            	String name = array.getJSONObject(i).getString(TAG_NAME).toString();
	            	arrayItems.add(name);
	            }
		}catch(Exception e) {
			
		}
	           
	            
	            
	            
	        
	  
	    
	    	    
	   
	    lst.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayItems));
	    
	   
		
		
		
	}
	
}

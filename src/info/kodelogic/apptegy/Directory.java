package info.kodelogic.apptegy;

import info.kodelogic.utils.DirectoryDetails;
import info.kodelogic.utils.SportEventsDetails;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Directory extends Activity{
ListView lst;
	
	private final static String ADDRESS="address";
	private final static String C_TYPE = "contactable_type";
	private final static String DEPARTMENT = "department";
	private final static String EMAIL = "email";
	private final static String FAX_NUMBER = "fax_number";
	private final static String FIRST_NAME = "first";
	private final static String LAST_NAME = "last";
	private final static String NOTES = "notes";
	private final static String PHONE = "phone_number";
	private final static String TITLE = "title";
	
	private final static String TOKEN="UpXCZdTsPuzayVfHdaDg";
	private final static String SITE_URL="http://www.kodelogic.info/api/v1/directory.js";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directory);
		JSONParser jsonParser = new JSONParser();
		String result = jsonParser.getJSONFromUrl(SITE_URL,TOKEN);
		
		ListView list;
		list = (ListView)findViewById(R.id.listView2);
		
		ArrayList<DirectoryDetails> arrayItems = new ArrayList<DirectoryDetails>();
		GetDirectoryAdapter adapter = new GetDirectoryAdapter(this,arrayItems);
		
		list.setAdapter(adapter);
		
		
		
		try
		{
			 JSONArray array = new JSONArray(result);
	            
	            for (int i = 0; i< array.length(); i++) {
	            	
	            	String address = array.getJSONObject(i).getString(ADDRESS).toString();
	            	String ctype = array.getJSONObject(i).getString(C_TYPE).toString();
	            	String dpt = array.getJSONObject(i).getString(DEPARTMENT).toString();
	            	String em  = array.getJSONObject(i).getString(EMAIL).toString();
	            	String fnbr = array.getJSONObject(i).getString(FAX_NUMBER).toString();
	            	String fname = array.getJSONObject(i).getString(FIRST_NAME).toString();
	            	String lname = array.getJSONObject(i).getString(LAST_NAME).toString();
	            	String notes = array.getJSONObject(i).getString(NOTES).toString();
	            	String ph = array.getJSONObject(i).getString(PHONE).toString();
	            	String tt = array.getJSONObject(i).getString(TITLE).toString();
	            	arrayItems.add(new DirectoryDetails(address,ctype,dpt,em,fnbr,fname,lname,notes,ph,tt));
	            	//arrayItems.add(new SportEventsDetails("",address,"",awayScore,awayTeam,"",hScore,hTeam,"",""));
	            	
	            }
	            
		}catch(Exception e) {}
	            }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	


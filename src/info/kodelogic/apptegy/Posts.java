package info.kodelogic.apptegy;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Posts extends Activity{
ListView lst;
private final static String ID="id";
private final static String TITLE="title";
private final static String DESCRIPTION="description";
private final static String START="start";
private final static String END="end";
private final static String ALL_DAY="allDay";
private final static String RECURRENCE="recurrence";
private final static String URL="url";
private final static String TOKEN="UpXCZdTsPuzayVfHdaDg";
private final static String SITE_URL="http://www.kodelogic.info/api/v1/events/district.js";
private ArrayList<String> arrayItems = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.posts);
		lst = (ListView)findViewById(R.id.lstPost);
		JSONParser jsonParser = new JSONParser();
		String result = jsonParser.getJSONFromUrl(SITE_URL,TOKEN);
		
		try
		{
			 JSONArray array = new JSONArray(result);
	            
	            for (int i = 0; i< array.length(); i++) {
	            	String id = array.getJSONObject(i).getString(ID).toString();
	            	String title = array.getJSONObject(i).getString(TITLE).toString();
	            	String descr = array.getJSONObject(i).getString(DESCRIPTION).toString();
	            	String start = array.getJSONObject(i).getString(START).toString();
	            	String end = array.getJSONObject(i).getString(END).toString();
	            	String allDay = array.getJSONObject(i).getString(ALL_DAY).toString();
	            	String recrr = array.getJSONObject(i).getString(RECURRENCE).toString();
	            	
	            	
	            	arrayItems.add("Id: "+id+"\nTitle: "+title+"\nDescription:"+descr+"\nStart: "+start+"\nEnd: "+end+"\nAllDay: "+
	            	allDay+"\nRecurrency: "+recrr);
	            	
	            }
		}catch(Exception e) {
			
		}
	     
		
		  lst.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayItems));
		   
		
	}
	
	
}

package info.kodelogic.apptegy;

import info.kodelogic.utils.SportEventsDetails;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class SportEvents extends Activity {

	ListView lst;
	
	private final static String ADDRESS="address";
	private final static String AWAY_SCORE="away_score";
	private final static String AWAY_TEAM="away_team";
	private final static String HOME_TEAM="home_team";
	private final static String HOME_SCORE="home_score";
	
	private final static String TOKEN="UpXCZdTsPuzayVfHdaDg";
	private final static String SITE_URL="http://www.kodelogic.info/api/v1/sport_events.js";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sport_events);
		
		JSONParser jsonParser = new JSONParser();
		String result = jsonParser.getJSONFromUrl(SITE_URL,TOKEN);
		
		ListView list;
		list = (ListView)findViewById(R.id.listView2);
		
		ArrayList<SportEventsDetails> arrayItems = new ArrayList<SportEventsDetails>();
		GetSportEventsAdapter adapter = new GetSportEventsAdapter(this,arrayItems);
		
		list.setAdapter(adapter);
		
		
		
		try
		{
			 JSONArray array = new JSONArray(result);
	            
	            for (int i = 0; i< array.length(); i++) {
	            	
	            	String address = array.getJSONObject(i).getString(ADDRESS).toString();
	            	String awayScore = array.getJSONObject(i).getString(AWAY_SCORE).toString();
	            	String awayTeam = array.getJSONObject(i).getString(AWAY_TEAM).toString();
	            	String hScore = array.getJSONObject(i).getString(HOME_SCORE).toString();
	            	String hTeam = array.getJSONObject(i).getString(HOME_TEAM).toString();
	            
	            	arrayItems.add(new SportEventsDetails("",address,"",awayScore,awayTeam,"",hScore,hTeam,"",""));
	            	
	            }
	            
		}catch(Exception e) {}
	            }
		
		
		
		
		
		
		
		
	}
	
	


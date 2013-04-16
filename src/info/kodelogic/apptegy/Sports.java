package info.kodelogic.apptegy;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import info.kodelogic.POJO.SportEventsDetails;
import info.kodelogic.POJO.SportsDetails;
import info.kodelogic.adapters.GetSportEventsAdapter;
import info.kodelogic.adapters.GetSportsAdapter;
import info.kodelogic.db.ApptegyDBHelper;
import info.kodelogic.web.JSONParser;
import info.kodelogic.web.JSONParser2;
import android.app.Activity;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class Sports extends Activity{
  ListView lst;
  private final static String ID = "id";
  private final static String NAME = "name";
  private final static String COUNTER = "counter";
  private final static String TOKEN = "ARTVKxWKSsPcVpyuwux8";
  private final static String SITE_URL = "http://www.kodelogic.info/api/v1/sports.js";
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sports);
    
    new MyAsyncTask().execute("");
   
    
  }

  class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<SportsDetails>> {

	  
	  private int insSport(int id, String name, int c)
		{
			int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(Sports.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "INSERT INTO parent_org(id,name) VALUES(" +id+
					",'"+name+"')";
			try{
				db.execSQL(sql);
				rows=1;
				
			}catch(Exception ex)
			{
				rows=-1;
			}
			db.close();
			
			return rows;
			
		}  
	  
	  
	 private int insSportDetails(int idSport, String away_score, String away_team,
			 String home_score,String home_team, String place, String address,
			 String city_state, String zip, String date_text)
	 {
		 int rows=-1;
		if (away_score=="" || away_score==null)
		{away_score="0";}
		if (home_score=="" || home_score==null)
		{home_score="0";}
		
		
		
		 ApptegyDBHelper dbHelper = new ApptegyDBHelper(Sports.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "INSERT INTO sport_event(idSport,away_score,away_team,home_score," +
					"home_team,place,address,city_state,zip,date_text) VALUES(" + idSport +
					",'"+away_score+"','"+away_team+"','"+home_score+"','"+home_team+"','"+
					place+"','"+address+"','"+city_state+"','"+zip+"','"+date_text+"')";
			
			try{
				db.execSQL(sql);
				Log.i("sql",sql);
				rows=1;
				
			}catch(Exception ex)
			{
				rows=-1;
				Log.e("error",ex.getMessage());
			}
			db.close();
			
			return rows;
		}
	  
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(Sports.this);
      progressDialog.setCancelable(true);
      progressDialog.setMessage("Loading...");
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.setProgress(0);
      progressDialog.show();
    }



    @Override
    protected ArrayList<SportsDetails> doInBackground(String... arg0) {
    	 JSONParser2 jParser = new JSONParser2();
    	 JSONObject json = jParser.getJSONFromUrl(SITE_URL+"?token="+TOKEN);
    	 JSONArray sports=null;
    	     
      
      ArrayList<SportsDetails> arrayItems = new ArrayList<SportsDetails>();
      try {
      	sports=json.getJSONArray("sports");
      	 for(int i = 0; i < sports.length(); i++){
      	        JSONObject c = sports.getJSONObject(i);
      	        String name = c.getString(NAME);
      	        int id = Integer.parseInt(c.getString(ID));
      	        int ct = Integer.parseInt(c.getString(COUNTER));
      	        insSport(id,name,ct);
      	        arrayItems.add(new SportsDetails(name));
      	        
      	        String sport_events = c.getString("sport_events").toString();
      	        JSONArray jArr = new JSONArray(sport_events);
      	        for(int k=0; k<jArr.length(); k++)
      	        {
      	        	JSONObject jObj = jArr.getJSONObject(k);
      	        	
      	        	String home_score="0";
      	        	if (jObj.getString("home_score").length()>0)
      	        	{
      	        		 home_score=jObj.getString("home_score");	
      	        	}else{
      	        		 
      	        	}
      	        	String away_score="0";
      	        	if (jObj.getString("away_score").length()>0)
      	        	{
      	        		away_score=jObj.getString("away_score");	
      	        	}else{
      	        		
      	        	}
      	        	
      	        	String away_team = jObj.getString("away_team");
      	        	String home_team = jObj.getString("home_team");
      	        	String place = jObj.getString("place");
      	        	String address = jObj.getString("address");
      	        	String city = jObj.getString("city_state");
      	        	String zip = jObj.getString("zip");
      	        	String date = jObj.getString("date_text");
      	        	
      	        	insSportDetails(id,away_score,away_team,home_score,home_team,place,address,
      	        	city,zip,date);
      	        	
      	        }
      	        
      	        
        }

      } catch(Exception e) {}
      return arrayItems;
    }

    @Override
    protected void onPostExecute(ArrayList<SportsDetails> arrayItems) {
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetSportsAdapter adapter = new GetSportsAdapter(Sports.this,arrayItems);
      list.setAdapter(adapter);
      progressDialog.dismiss();
    }
  }
}
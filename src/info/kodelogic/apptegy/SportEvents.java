package info.kodelogic.apptegy;

import info.kodelogic.POJO.SecondaryOrgsDetails;
import info.kodelogic.POJO.SportEventsDetails;
import info.kodelogic.adapters.GetSportEventsAdapter;
import info.kodelogic.apptegy.Sports.MyAsyncTask;
import info.kodelogic.web.JSONParser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class SportEvents extends Activity {

  ListView lst;

  private final static String ADDRESS = "address";
  private final static String AWAY_SCORE = "away_score";
  private final static String AWAY_TEAM = "away_team";
  private final static String HOME_TEAM = "home_team";
  private final static String HOME_SCORE = "home_score";
  private final static String DATE = "adate";
  private final static String TIME = "atime";
  private final static String DATE_TEXT = "date_text";
  private final static String CITY_STATE = "city_state";
  private final static String PLACE = "place";
  private final static String ZIP = "zip";


  private final static String TOKEN = "UpXCZdTsPuzayVfHdaDg";
  private final static String SITE_URL = "http://www.kodelogic.info/api/v1/sport_events.js";
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sport_events);
    new MyAsyncTask().execute("");
  }


  class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<SportEventsDetails>> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(SportEvents.this);
      progressDialog.setCancelable(true);
      progressDialog.setMessage("Loading...");
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.setProgress(0);
      progressDialog.show();
    }


    @Override
    protected ArrayList<SportEventsDetails> doInBackground(String... arg0) {
      JSONParser jsonParser = new JSONParser();
      String result = jsonParser.getJSONFromUrl(SITE_URL,TOKEN);
      ArrayList<SportEventsDetails> arrayItems = new ArrayList<SportEventsDetails>();

      try {
        JSONArray array = new JSONArray(result);

        for (int i = 0, j = array.length(); i < j; i++) {
          JSONObject obj = array.getJSONObject(i);

          String address = obj.getString(ADDRESS).toString();
          String awayScore = obj.getString(AWAY_SCORE).toString();
          String awayTeam = obj.getString(AWAY_TEAM).toString();
          String hScore = obj.getString(HOME_SCORE).toString();
          String hTeam = obj.getString(HOME_TEAM).toString();
          String date = obj.getString(DATE).toString();
          String tm = obj.getString(TIME).toString();
          String date_text = obj.getString(DATE_TEXT).toString();
          String c = obj.getString(CITY_STATE).toString();
          String p = obj.getString(PLACE).toString();
          String z = obj.getString(ZIP).toString();

         // arrayItems.add(new SportEventsDetails(date,address,tm,awayScore,awayTeam,c,hScore,hTeam,p,z,date_text));
        }

      } catch(Exception e) {}

      return arrayItems;
    }

    @Override
    protected void onPostExecute(ArrayList<SportEventsDetails> result) {
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetSportEventsAdapter adapter = new GetSportEventsAdapter(SportEvents.this,result);
      list.setAdapter(adapter);
      progressDialog.dismiss();
    }
  }
}
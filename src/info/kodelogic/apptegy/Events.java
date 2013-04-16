package info.kodelogic.apptegy;

import info.kodelogic.POJO.DirectoryDetails;
import info.kodelogic.POJO.DistrictEventsDetails;
import info.kodelogic.POJO.FacebookDetails;
import info.kodelogic.POJO.SportsDetails;
import info.kodelogic.adapters.GetDistrictEventsAdapter;
import info.kodelogic.adapters.GetSportsAdapter;
import info.kodelogic.apptegy.Directory.MyAsyncTask;
import info.kodelogic.utils.Converters;
import info.kodelogic.web.JSONParser;
import info.kodelogic.web.JSONParser2;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Events extends Activity {
  ListView lst;
  private final static String DESCRIPTION = "description";
  private final static String TITLE = "title";
  private final static String VENUE = "venue";
  private final static String ALL_DAY = "all_day";
  private final static String EVENTABLE_TYPE = "eventable_type";
  private final static String START = "start";
  private final static String END = "end";
  private final static String TOKEN = "ARTVKxWKSsPcVpyuwux8";
  private final static String SITE_URL = "http://www.kodelogic.info/api/v1/events/district.json";
  private ArrayList<String> arrayItems = new ArrayList<String>();
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.posts);
    new MyAsyncTask().execute("");
  }

  class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<DistrictEventsDetails>> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(Events.this);
      progressDialog.setCancelable(true);
      progressDialog.setMessage("Loading...");
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.setProgress(0);
      progressDialog.show();
    }

    @Override
    protected ArrayList<DistrictEventsDetails> doInBackground(String... arg0) {
    	 JSONParser2 jParser = new JSONParser2();
    	 JSONObject json = jParser.getJSONFromUrl(SITE_URL+"?token="+TOKEN);
    	 JSONArray events=null;
    	 
    	 
      ArrayList<DistrictEventsDetails> arrayItems = new ArrayList<DistrictEventsDetails>();
      try {
        events = json.getJSONArray("events");

        for (int i = 0, j = events.length(); i < j; i++) {
          JSONObject json_object = events.getJSONObject(i);
          String title = json_object.getString(TITLE).toString();
          String descr = json_object.getString(DESCRIPTION).toString();
          String venue = json_object.getString(VENUE).toString();
          
/*
          if (descr=="null") {descr="No description";}

          String date = json_object.getString(START_TEXT).toString();

          String end = json_object.getString(END_TEXT).toString();
*/
          
          String allDay = json_object.getString(ALL_DAY).toString();

          if (allDay == "null") {
            allDay = "No";
          }
          String eventable = json_object.getString(EVENTABLE_TYPE).toString();
          String start = json_object.getString(START).toString();
          String end = json_object.getString(END).toString();
          
          
          arrayItems.add(new DistrictEventsDetails(title, descr, venue, allDay, eventable, start, end));
        }
      } catch(Exception e) {

      }
      return arrayItems;
    }

    @Override
    protected void onPostExecute(ArrayList<DistrictEventsDetails> result) {
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetDistrictEventsAdapter adapter = new GetDistrictEventsAdapter(Events.this,result);
      list.setAdapter(adapter);
      list.setTextFilterEnabled(true);
      progressDialog.dismiss();
    }
  }
}
package info.kodelogic.apptegy;

import info.kodelogic.POJO.SportsDetails;
import info.kodelogic.POJO.TwitterDetails;
import info.kodelogic.adapters.GetTwitterAdapter;
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

public class TwitterFeeds extends Activity{

  private final static String SITE_URL = "http://search.twitter.com/search.json?q=ApptegyEducatio";
  private final static String TEXT = "text";
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.twitter_feed);
    new MyAsyncTask().execute("");
  }


  class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<TwitterDetails>> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(TwitterFeeds.this);
      progressDialog.setCancelable(true);
      progressDialog.setMessage("Loading...");
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.setProgress(0);
      progressDialog.show();
    }

    @Override
    protected ArrayList<TwitterDetails> doInBackground(String... arg0) {
      JSONParser jsonParser = new JSONParser();
      String result = jsonParser.getJSONFromUrlTwitter(SITE_URL);
      ArrayList<TwitterDetails> arrayItems = new ArrayList<TwitterDetails>();

      try {
        JSONArray data = null;
        JSONObject obj = new JSONObject(result);
        data = obj.getJSONArray("results");

        for (int i = 0, j = data.length(); i < j; i++) {
          JSONObject c = data.getJSONObject(i);
          String text = c.getString(TEXT);

          arrayItems.add(new TwitterDetails(text));
        }
      } catch(Exception e) {}
      return arrayItems;
    }

    @Override
    protected void onPostExecute(ArrayList<TwitterDetails> arrayItems) {
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetTwitterAdapter adapter = new GetTwitterAdapter(TwitterFeeds.this,arrayItems);
      list.setAdapter(adapter);
      progressDialog.dismiss();
    }
  }
}
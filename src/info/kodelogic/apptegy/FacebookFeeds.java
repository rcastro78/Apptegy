package info.kodelogic.apptegy;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import info.kodelogic.POJO.DistrictPostDetails;
import info.kodelogic.POJO.FacebookDetails;
import info.kodelogic.adapters.GetFacebookAdapter;
import info.kodelogic.apptegy.DistrictPosts.MyAsyncTask;
import info.kodelogic.web.JSONFBParser;
import info.kodelogic.web.JSONParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class FacebookFeeds extends Activity {
  private final static String MESSAGE = "message";
  private final static String TOKEN = "310496722363362%7C9qOoCNqpzXXozZ4dYvtHJcTo_18";
  private final static String SITE_URL = "https://graph.facebook.com/471018456285995/feed";
  //%7C represents the special character |

  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_facebook);
    new MyAsyncTask().execute("");
  }

  class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<FacebookDetails>> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(FacebookFeeds.this);
      progressDialog.setCancelable(true);
      progressDialog.setMessage("Loading...");
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.setProgress(0);
      progressDialog.show();
    }


    @Override
    protected ArrayList<FacebookDetails> doInBackground(String... arg0) {

      JSONFBParser jsonParser = new JSONFBParser();
      String newsfeed = jsonParser.getFBJSONFromUrl(SITE_URL,TOKEN);
      ArrayList<FacebookDetails> arrayItems = new ArrayList<FacebookDetails>();

      try {
        JSONArray data = null;
        JSONObject obj = new JSONObject(newsfeed);
        data = obj.getJSONArray("data");
        for (int i=0; i <= data.length(); i++) {
          JSONObject c = data.getJSONObject(i);
          String msg = c.getString("message");
          if (msg.length() > 0) {
            //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            arrayItems.add(new FacebookDetails(msg));
          }
        }

      } catch(Exception e) {}

      return arrayItems;
    }

    @Override
    protected void onPostExecute(ArrayList<FacebookDetails> result) {
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetFacebookAdapter adapter = new GetFacebookAdapter(FacebookFeeds.this,result);
      list.setAdapter(adapter);
      progressDialog.dismiss();
    }
  }
}
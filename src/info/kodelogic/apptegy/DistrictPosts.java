package info.kodelogic.apptegy;

import info.kodelogic.POJO.DirectoryDetails;
import info.kodelogic.POJO.DistrictPostDetails;
import info.kodelogic.adapters.GetDistrictPostsAdapter;
import info.kodelogic.web.JSONParser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class DistrictPosts extends Activity {

  private final static String TITLE= "title";
  private final static String CONTENT= "content";
  private final static String PUBLISHED_ON= "published_text";
  private final static String POSTABLE_TYPE= "postable_type";

  private final static String TOKEN= "ARTVKxWKSsPcVpyuwux8";
  private final static String SITE_URL= "http://www.kodelogic.info/api/v1/posts/district.js";
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.district_post);
    new MyAsyncTask().execute("");
  }



  class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<DistrictPostDetails>> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(DistrictPosts.this);
      progressDialog.setCancelable(true);
      progressDialog.setMessage("Loading...");
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.setProgress(0);
      progressDialog.show();
    }

    @Override
    protected ArrayList<DistrictPostDetails> doInBackground(String... arg0) {
      JSONParser jsonParser = new JSONParser();
      String result = jsonParser.getJSONFromUrl(SITE_URL,TOKEN);
      ArrayList<DistrictPostDetails> arrayItems = new ArrayList<DistrictPostDetails>();
      try {
        JSONArray array = new JSONArray(result);

        for (int i = 0, j = array.length(); i < j; i++) {
          JSONObject json_object = array.getJSONObject(i);

          String title = json_object.getString(TITLE).toString();
          String content = json_object.getString(CONTENT).toString();
          String pub = json_object.getString(PUBLISHED_ON).toString();
          String postable = json_object.getString(POSTABLE_TYPE).toString();
          arrayItems.add(new DistrictPostDetails(title,pub,content,postable));
        }

      } catch(Exception e) {}

      return arrayItems;
    }

    @Override
    protected void onPostExecute(ArrayList<DistrictPostDetails> result) {
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetDistrictPostsAdapter adapter = new GetDistrictPostsAdapter(DistrictPosts.this, result);
      list.setAdapter(adapter);
      progressDialog.dismiss();
      list.setTextFilterEnabled(true);
    }
  }
}
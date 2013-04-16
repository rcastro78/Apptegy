package info.kodelogic.apptegy;

import java.util.ArrayList;

import org.json.JSONArray;

import info.kodelogic.POJO.DistrictEventsDetails;
import info.kodelogic.POJO.SecondaryOrgsDetails;
import info.kodelogic.POJO.SportsDetails;
import info.kodelogic.adapters.GetSecondaryOrgsAdapter;
import info.kodelogic.adapters.GetSportsAdapter;
import info.kodelogic.apptegy.Events.MyAsyncTask;
import info.kodelogic.web.JSONParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class Secondary extends Activity{
  ListView lst;
  private final static String NAME = "name";
  private final static String TOKEN="UpXCZdTsPuzayVfHdaDg";
  private final static String SITE_URL="http://www.kodelogic.info/api/v1/secondary_organizations.js";
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.secondary_orgs);
    new MyAsyncTask().execute("");
  }


  class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<SecondaryOrgsDetails>> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(Secondary.this);
      progressDialog.setCancelable(true);
      progressDialog.setMessage("Loading...");
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.setProgress(0);
      progressDialog.show();
    }

    @Override
    protected ArrayList<SecondaryOrgsDetails> doInBackground(String... arg0) {
      JSONParser jsonParser = new JSONParser();
      String result = jsonParser.getJSONFromUrl(SITE_URL,TOKEN);
      ArrayList<SecondaryOrgsDetails> arrayItems = new ArrayList<SecondaryOrgsDetails>();
      try {
        JSONArray array = new JSONArray(result);

        for (int i = 0, j = array.length(); i < j; i++) {
          String name = array.getJSONObject(i).getString(NAME).toString();
          arrayItems.add(new SecondaryOrgsDetails(name));
        }

      } catch(Exception e) {}
      return arrayItems;
    }

    @Override
    protected void onPostExecute(ArrayList<SecondaryOrgsDetails> result) {
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetSecondaryOrgsAdapter adapter = new GetSecondaryOrgsAdapter(Secondary.this,result);
      list.setAdapter(adapter);
      progressDialog.dismiss();
    }
  }
}
package info.kodelogic.apptegy;

import info.kodelogic.POJO.DirectoryDetails;
import info.kodelogic.POJO.DistrictPostDetails;
import info.kodelogic.POJO.SportEventsDetails;
import info.kodelogic.adapters.GetDirectoryAdapter;
import info.kodelogic.web.JSONParser;
import info.kodelogic.web.JSONParser2;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class Directory extends Activity {
  ListView lst;

  private final static String FULLNAME="full_name";
  private final static String ADDRESS="address";
  private final static String DEPARTMENT = "department";
  private final static String EMAIL = "email";
  private final static String FAX_NUMBER = "fax_number";
  private final static String FIRST_NAME = "first";
  private final static String LAST_NAME = "last";
  private final static String NOTES = "notes";
  private final static String PHONE = "phone_number";
  private final static String TITLE = "title";

  private final static String TOKEN="ARTVKxWKSsPcVpyuwux8";
  private final static String SITE_URL="http://www.kodelogic.info/api/v1/directory.json";
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.directory);
    new MyAsyncTask().execute("");
  }

  class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<DirectoryDetails>> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(Directory.this);
      progressDialog.setCancelable(true);
      progressDialog.setMessage("Loading...");
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      progressDialog.setProgress(0);
      progressDialog.show();
    }

    @Override
    protected ArrayList<DirectoryDetails> doInBackground(String... arg0) {
    	 JSONParser2 jParser = new JSONParser2();
    	 JSONObject json = jParser.getJSONFromUrl(SITE_URL+"?token="+TOKEN);
    	 JSONArray direct=null;
    	 
      ArrayList<DirectoryDetails> arrayItems = new ArrayList<DirectoryDetails>();

      try {
    	 direct = json.getJSONArray("directory");
        for (int i = 0, j = direct.length(); i < j; i++) {
          JSONObject json_object = direct.getJSONObject(i);

          String address = json_object.getString(ADDRESS).toString();
          String fullname = json_object.getString(FULLNAME).toString();
          String dpt = json_object.getString(DEPARTMENT).toString();
          String em  = json_object.getString(EMAIL).toString();
          String fnbr = json_object.getString(FAX_NUMBER).toString();
          String fname = json_object.getString(FIRST_NAME).toString();
          String lname = json_object.getString(LAST_NAME).toString();
          String notes = json_object.getString(NOTES).toString();
          String ph = json_object.getString(PHONE).toString();
          String tt = json_object.getString(TITLE).toString();
          arrayItems.add(new DirectoryDetails(fullname,address, dpt, em, fnbr, fname, lname, notes, ph, tt));
        }

      } catch(Exception e) {}
      return arrayItems;
    }

    @Override
    protected void onPostExecute(ArrayList<DirectoryDetails> result) {
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetDirectoryAdapter adapter = new GetDirectoryAdapter(Directory.this,result);
      list.setAdapter(adapter);
      list.setTextFilterEnabled(true);
      progressDialog.dismiss();
    }
  }
}
package info.kodelogic.apptegy;
import info.kodelogic.POJO.ParentOrgsDetails;
import info.kodelogic.POJO.SurveyDetails;
import info.kodelogic.adapters.GetDistrictEventsAdapter;
import info.kodelogic.adapters.GetParentOrgsAdapter;
import info.kodelogic.db.ApptegyDBHelper;
import info.kodelogic.web.JSONParser2;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ParentOrg extends Activity{

  public static final String ID = "id";	
  public static final String NAME = "name";
  private final static String TOKEN = "ARTVKxWKSsPcVpyuwux8";
  private final static String SITE_URL = "http://www.kodelogic.info/api/v1/parent_organizations.json";
  ListView lst;
  private ProgressDialog progressDialog;
  private ArrayList<ParentOrgsDetails> arrayItems = new ArrayList<ParentOrgsDetails>();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.parent_org);
    new ParentsAsyncTask().execute("");
    lst = (ListView)findViewById(R.id.lstParent);
    
    lst.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
                Object o = lst.getItemAtPosition(position);
                ParentOrgsDetails s = (ParentOrgsDetails)o;
                Intent sec = new Intent(ParentOrg.this,SeconOrg.class);
                sec.putExtra("id", s.getId());
                startActivity(sec);
        }
    });
    
    
    }
  
  class ParentsAsyncTask extends AsyncTask<String, Integer, ArrayList<ParentOrgsDetails>>
  {
	
	 private int delParent()
	 {
		 int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(ParentOrg.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "DELETE FROM parent_org";
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
	 
	 private int delSecond()
	 {
		 int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(ParentOrg.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "DELETE FROM second_org";
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
	 
	 
	 private int insParentOrgs(int id, String name)
	 {
		 int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(ParentOrg.this,null,1);
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
	  
	 private int insSecondOrgs(int id, String name)
	 {
		 int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(ParentOrg.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "INSERT INTO second_org(idParent,name) VALUES(" +id+
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
	 
	 
	  @Override
	    protected void onPreExecute() {
	      super.onPreExecute();
	      progressDialog = new ProgressDialog(ParentOrg.this);
	      progressDialog.setCancelable(true);
	      progressDialog.setMessage("Loading...");
	      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	      progressDialog.setProgress(0);
	      progressDialog.show();
	    }  
	  
	@Override
	protected ArrayList<ParentOrgsDetails> doInBackground(String... arg0) {
		JSONParser2 jParser = new JSONParser2();
   	    JSONObject json = jParser.getJSONFromUrl(SITE_URL+"?token="+TOKEN);
   	    JSONArray parents=null;
   	    
   	    ArrayList<ParentOrgsDetails> arrayItems = new ArrayList<ParentOrgsDetails>();
   	    delSecond();
   	    delParent();
   	    try{
   	    	  parents = json.getJSONArray("parent_organizations");
   	    	  for (int i = 0, j = parents.length(); i < j; i++) {
   	            JSONObject json_object = parents.getJSONObject(i);
   	            String id = json_object.getString(ID).toString();
   	            String name = json_object.getString(NAME).toString();
   	            insParentOrgs(Integer.parseInt(id),name);
   	            arrayItems.add(new ParentOrgsDetails(id,name));
   	            String secondary=json_object.getString("secondary_organizations").toString();
   	    	    JSONArray jsonArray = new JSONArray(secondary);
   	    	    
   	    	    for (int k=0; k<jsonArray.length(); k++)
   	    	    {
   	    	    	JSONObject jObj = jsonArray.getJSONObject(k);
   	    	    	String sec_name = jObj.getString(NAME);
   	    	    	insSecondOrgs(Integer.valueOf(id),sec_name);
   	    	    }
   	    	  }
   	    	 
   	    	 
   	    }catch(Exception e) {}
   	   return arrayItems;
	}
	
	
	 @Override
	    protected void onPostExecute(ArrayList<ParentOrgsDetails> result) {
		  ListView list;
		  list = (ListView)findViewById(R.id.lstParent);
	      GetParentOrgsAdapter adapter = new GetParentOrgsAdapter(ParentOrg.this,result);
	      list.setAdapter(adapter);
	      progressDialog.dismiss();
	     }
	
	  
  }
  
  
}
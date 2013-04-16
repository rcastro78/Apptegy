package info.kodelogic.apptegy;

import info.kodelogic.POJO.DistrictEventsDetails;
import info.kodelogic.POJO.MessageDetails;
import info.kodelogic.POJO.QuestionsDetails;
import info.kodelogic.adapters.GetDistrictEventsAdapter;
import info.kodelogic.adapters.GetMessageAdapter;
import info.kodelogic.apptegy.Events.MyAsyncTask;
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
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Inbox extends Activity{

	ListView lst;
	  private final static String TOPIC="topic";
	  private final static String ID="id";
	  private final static String DATE="message_date";
	  private final static String TIME_AGO="time_ago";
	  private final static String BODY="body";
	  private final static String TO="recipient";
	  private final static String SENDER="sender";
	  private final static String TOKEN = "ARTVKxWKSsPcVpyuwux8";
	  private final static String SITE_URL = "http://www.kodelogic.info/api/v1/messages/inbox.json";
	  private ArrayList<String> arrayItems = new ArrayList<String>();
	  private ProgressDialog progressDialog;
	  ListView list;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.inbox);
	    
	    list = (ListView)findViewById(R.id.lvInbox);
	      
	    new InboxTask().execute("");
	    
	    list.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	                Object o = list.getItemAtPosition(position);
	                MessageDetails m = (MessageDetails)o;
	                Intent idetails = new Intent(Inbox.this,InboxDetails.class);
	                idetails.putExtra("id", m.getId());
	                startActivity(idetails);
	        }
	    });
	    
	    
	  }
	  
	  
	  class InboxTask extends AsyncTask<String, Integer, ArrayList<MessageDetails>> {

		  
		  
		  
		    @Override
		    protected void onPreExecute() {
		      super.onPreExecute();
		      progressDialog = new ProgressDialog(Inbox.this);
		      progressDialog.setCancelable(true);
		      progressDialog.setMessage("Loading...");
		      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		      progressDialog.setProgress(0);
		      progressDialog.show();
		    }

			@Override
			protected ArrayList<MessageDetails> doInBackground(String... params) {
				JSONParser2 jParser = new JSONParser2();
		    	JSONObject json = jParser.getJSONFromUrl(SITE_URL+"?token="+TOKEN);
		    	JSONArray msgs=null;
		    	delMessage();
		    	  ArrayList<MessageDetails> arrayItems = new ArrayList<MessageDetails>();
		          try {
		        	  msgs = json.getJSONArray("messages");

		            for (int i = 0, j = msgs.length(); i < j; i++) {
		              JSONObject json_object = msgs.getJSONObject(i);
		              String id = json_object.getString(ID).toString();
		              String topic = json_object.getString(TOPIC).toString();
		              String body = json_object.getString(BODY).toString();
		              String mdate = json_object.getString(DATE).toString();
		              String ago = json_object.getString(TIME_AGO).toString();
		              String rec = json_object.getString(TO).toString();
		              String sender = json_object.getString(SENDER).toString();
		              arrayItems.add(new MessageDetails(id,topic,body,mdate,ago,rec,sender));
		              insMessage(Integer.parseInt(id),topic,body,mdate,rec,sender);
		            }
		          }catch(Exception e) {}
		              
		    	return arrayItems;
		    	 
			}

			
			@Override
		    protected void onPostExecute(ArrayList<MessageDetails> result) {
		      ListView list;
		      list = (ListView)findViewById(R.id.lvInbox);
		      GetMessageAdapter adapter = new GetMessageAdapter(Inbox.this,result);
		      list.setAdapter(adapter);
		      list.setTextFilterEnabled(true);
		      progressDialog.dismiss();
		    }
	  }
	  
	  private int delMessage()
	  {
		  int rows=-1;
			 ApptegyDBHelper dbHelper = new ApptegyDBHelper(Inbox.this,null,1);
			 SQLiteDatabase db = dbHelper.getWritableDatabase();
			 String sql="DELETE FROM Message";
			 try{
				 db.execSQL(sql);
				 rows=1;
			 }catch(Exception e)
			 {
				 rows=-1;
			 }
			 return rows;  
	  }
	  
	  private int insMessage(int id, String topic, String body, String date, 
			  String rec, String sender)
	  {
		 int rows=-1;
		 ApptegyDBHelper dbHelper = new ApptegyDBHelper(Inbox.this,null,1);
		 SQLiteDatabase db = dbHelper.getWritableDatabase();
		 body=body.replace("'", " ");
		 String sql="INSERT INTO Message(idMessage,topic,body,message_date,recipient," +
					"sender,type) VALUES("+id+",'"+topic+"','"+body+"','"+date+
					"','"+rec+"','"+sender+"',1)";
		 try{
			 db.execSQL(sql);
			 Log.i("sql",sql);
			 rows=1;
		 }catch(Exception e)
		 {
			 rows=-1;
		 }
		 return rows;
	  }
	  
	  
}

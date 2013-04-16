package info.kodelogic.apptegy;

import info.kodelogic.POJO.DistrictEventsDetails;
import info.kodelogic.POJO.SurveyDetails;
import info.kodelogic.adapters.GetDistrictEventsAdapter;
import info.kodelogic.adapters.GetSurveyAdapter;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Surveys extends Activity{
	ListView lst;
	private final static String ID = "id";
	private final static String NAME = "name";
	private final static String SURVEY_TYPE = "survey_type";
	private final static String PUBLISH = "publish";
	private final static String TOKEN = "ARTVKxWKSsPcVpyuwux8";
	private final static String SITE_URL = "http://www.kodelogic.info/api/v1/surveys/surveys.json";
	private ProgressDialog progressDialog;
	ListView list;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.surveys);
	    new SurveysAsyncTask().execute("");
	    
	    
	    list = (ListView)findViewById(R.id.lvSurvey);
	    list.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	                Object o = list.getItemAtPosition(position);
	                SurveyDetails s = (SurveyDetails)o;
	                Intent question = new Intent(Surveys.this,Questions.class);
	                question.putExtra("id", s.getId());
	                question.putExtra("name", s.getName());
	                startActivity(question);
	        }
	    });
	  }
	
	class SurveysAsyncTask extends AsyncTask<String, Integer, ArrayList<SurveyDetails>> {

		private int delSurveys()
		{
			int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(Surveys.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "DELETE FROM Survey";
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
	
		
		private int insSurveys(int id, String n, String st, String p)
		{
			int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(Surveys.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "INSERT INTO Survey(idSurvey,name,survey_type,publish) VALUES(" +id+
					",'"+n+"','"+st+"','"+p+"')";
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
	
		private int delQuestions()
		{
			int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(Surveys.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "DELETE FROM Question";
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
		
		private int insQuestions(int id, String c)
		{
			int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(Surveys.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "INSERT INTO question(idSurvey,content) VALUES(" +id+
					",'"+c+"')";
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
		
		private int delAnswer()
		{
			int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(Surveys.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "DELETE FROM answer";
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
		
		
		
		private int insAnswer(String qc, String c)
		{
			int rows=-1;
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(Surveys.this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			String sql = "INSERT INTO answer(qContent,content) VALUES('" +qc+
					"','"+c+"')";
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
		      progressDialog = new ProgressDialog(Surveys.this);
		      progressDialog.setCancelable(true);
		      progressDialog.setMessage("Loading...");
		      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		      progressDialog.setProgress(0);
		      progressDialog.show();
		    }
		
		@Override
		protected ArrayList<SurveyDetails> doInBackground(String... arg0) {
			delSurveys();
			delQuestions();
			delAnswer();
			JSONParser2 jParser = new JSONParser2();
	    	JSONObject json = jParser.getJSONFromUrl(SITE_URL+"?token="+TOKEN);
	    	JSONArray survey=null;
	    	JSONArray questions=null;
	    	ArrayList<SurveyDetails> arrayItems = new ArrayList<SurveyDetails>();
	    	try {
	            survey = json.getJSONArray("surveys");

	            for (int i = 0; i < survey.length(); i++) {
	              JSONObject json_object = survey.getJSONObject(i);
	              String id = json_object.getString(ID).toString();
	              String name = json_object.getString(NAME).toString();
	              String st = json_object.getString(SURVEY_TYPE).toString();
	              String pub = json_object.getString(PUBLISH).toString();
	              arrayItems.add(new SurveyDetails(Integer.parseInt(id),name,st,pub));
	              insSurveys(Integer.parseInt(id),name,st,pub); 
	              
	              String q =  json_object.getString("questions").toString();
	              
	              Log.i("q",q);
	              JSONArray jsonArray = new JSONArray(q);
	              Log.i("question length",String.valueOf(jsonArray.length()).toString());	
	              for(int k = 0; k <jsonArray.length(); k++){
	            	  JSONObject jObjQuestions = jsonArray.getJSONObject(k);
	            	  Log.i("question",jObjQuestions.getString("content"));	
	            	  
	            	  insQuestions(Integer.parseInt(id),jObjQuestions.getString("content"));
	            	  
	            	  String answers = jObjQuestions.getString("answers").toString();
	            	  JSONArray jsonArrayAnswers = new JSONArray(answers);
	            	  for(int m=0; m<jsonArrayAnswers.length(); m++)
	            	  {
	            		  JSONObject jObjAnswers = jsonArrayAnswers.getJSONObject(m);
	            		  Log.i("answer",jObjQuestions.getString("content")+":"+jObjAnswers.getString("content"));
	            		  insAnswer(jObjQuestions.getString("content"),jObjAnswers.getString("content"));
	            	  }
	            	  
	              }
	             
	             
	            }
	    	 
		}catch(Exception e) {Log.e("error", e.getMessage());}
	    	return arrayItems;
	}
	
		 @Override
		    protected void onPostExecute(ArrayList<SurveyDetails> result) {
		      ListView list;
		      list = (ListView)findViewById(R.id.lvSurvey);
		      GetSurveyAdapter adapter = new GetSurveyAdapter(Surveys.this,result);
		      list.setAdapter(adapter);
		      list.setTextFilterEnabled(true);
		      progressDialog.dismiss();
		    }	
		
		
  }

}



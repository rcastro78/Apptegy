package info.kodelogic.apptegy;

import info.kodelogic.POJO.AnswerDetails;
import info.kodelogic.adapters.GetAnswersAdapter;
import info.kodelogic.db.ApptegyDBHelper;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class PollAnswers extends Activity{
	 private ArrayList<AnswerDetails> arrAnswers = new ArrayList<AnswerDetails>();
	   TextView txtSurvey;
		@Override
		  protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.answers);
		    final Bundle extras = getIntent().getExtras();
			String question = extras.getString("q");
			txtSurvey = (TextView)findViewById(R.id.txtSurvey);
			txtSurvey.setText(question);
			ListView list;
			getAnsw(question);
		    list = (ListView)findViewById(R.id.lvQuestions);
		    GetAnswersAdapter adapter = new GetAnswersAdapter(PollAnswers.this,arrAnswers);
		    list.setAdapter(adapter);
		}
		
		
		private void getAnsw(String q)
		{
			
			ApptegyDBHelper dbHelper = new ApptegyDBHelper(this,null,1);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//String sql= "SELECT name || ' ' || lastname FROM Clients ORDER BY lastname";
			String sql= "SELECT qcontent,content FROM poll_answer where qcontent='"+q+"'";
			Cursor c = db.rawQuery(sql,null);
			c.moveToFirst();
			if (c.moveToFirst())
			{
				do{
					addList(c);
				}while(c.moveToNext());
			}
			c.close();
			db.close();		
		}
		
		private void addList(Cursor c)
	    {
			arrAnswers.add(new AnswerDetails(c.getString(0),c.getString(1)));
	    }
		
		
		
		
}

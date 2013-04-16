package info.kodelogic.apptegy;

import java.util.ArrayList;
import info.kodelogic.POJO.QuestionsDetails;
import info.kodelogic.POJO.SurveyDetails;
import info.kodelogic.adapters.GetQuestionsAdapter;
import info.kodelogic.db.ApptegyDBHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class Questions extends Activity{
	 private ArrayList<QuestionsDetails> arrQuestions = new ArrayList<QuestionsDetails>();
	 ListView list;  
	 TextView txtSurvey;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.questions);
	    final Bundle extras = getIntent().getExtras();
		int id=extras.getInt("id");
		String survey = extras.getString("name");
		txtSurvey = (TextView)findViewById(R.id.txtSurvey);
		txtSurvey.setText(survey);
		getQuestions(id);
	    
	    list = (ListView)findViewById(R.id.lvQuestions);
	    GetQuestionsAdapter adapter = new GetQuestionsAdapter(Questions.this,arrQuestions);
	    list.setAdapter(adapter);
	    list.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	                Object o = list.getItemAtPosition(position);
	                QuestionsDetails q = (QuestionsDetails)o;
	                Intent answer = new Intent(Questions.this,Answers.class);
	                answer.putExtra("q", q.getContent());
	                startActivity(answer);
	        }
	    });
	    
	}
	
	
	private void getQuestions(int idSurvey)
	{
		
		ApptegyDBHelper dbHelper = new ApptegyDBHelper(this,null,1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		//String sql= "SELECT name || ' ' || lastname FROM Clients ORDER BY lastname";
		String sql= "SELECT idSurvey,content FROM question where idSurvey="+idSurvey;
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
		arrQuestions.add(new QuestionsDetails(c.getInt(0),c.getString(1)));
    }
	
	
}

package info.kodelogic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class ApptegyDBHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "db_apptegy";
	private static final int DATABASE_VERSION = 1;
	/*Surveys*/
	private static final String SURVEY="CREATE TABLE survey(idSurvey integer," +
			"name text, survey_type text, publish text)";
	private static final String QUESTION="CREATE TABLE question(idSurvey integer," +
			"content text)";
	private static final String ANSWER="CREATE TABLE answer(qContent text," +
			"content text)";
	
	/*Polls*/
	private static final String POLL="CREATE TABLE poll(idPoll integer," +
			"name text, survey_type text, publish text)";
	private static final String POLL_QUESTION="CREATE TABLE poll_question(idPoll integer," +
			"content text)";
	private static final String POLL_ANSWER="CREATE TABLE poll_answer(qContent text," +
			"content text)";
	/*Messages*/
	private static final String MESSAGE="CREATE TABLE message(idMessage int, topic text," +
			"body text,message_date text,recipient text, sender text, type int)";
	
	private static final String SENT_MESSAGE="CREATE TABLE sent_message(idMessage int, topic text," +
			"body text,message_date text,recipient text, sender text, type int)";
	
	private static final String THRASH_MESSAGE="CREATE TABLE thrash_message(idMessage int, topic text," +
			"body text,message_date text,recipient text, sender text, type int)";

	/*Sports*/
	private static final String SPORT = "CREATE TABLE sport(id int, name text,counter int)";
	private static final String SPORT_EVENTS="CREATE TABLE sport_event(idSport int,away_score text" +
			",away_team text, home_score text,home_team text,place text, address text, city_state text" +
			",zip text, date text)";
	
	private static final String PARENT_ORG="create table parent_org(id int, name text)";
	private static final String SECOND_ORG="create table second_org(idParent int, name text)";
	
	
	public ApptegyDBHelper(Context context, CursorFactory factory,int version) {
		super(context, DATABASE_NAME, factory, version);
	}

	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	  db.execSQL(SURVEY);	
	  db.execSQL(QUESTION);	
	  db.execSQL(ANSWER);
	  db.execSQL(POLL);	
	  db.execSQL(POLL_QUESTION);	
	  db.execSQL(POLL_ANSWER);
	  db.execSQL(MESSAGE);
	  db.execSQL(SENT_MESSAGE);
	  db.execSQL(THRASH_MESSAGE);
	  db.execSQL(SPORT);
	  db.execSQL(SPORT_EVENTS);
	  db.execSQL(PARENT_ORG);
	  db.execSQL(SECOND_ORG);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}

package info.kodelogic.apptegy;

import info.kodelogic.POJO.MessageDetails;
import info.kodelogic.adapters.GetMessageDetailsAdapter;
import info.kodelogic.db.ApptegyDBHelper;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class SentboxDetails extends Activity{
	ListView lst;
	ArrayList<MessageDetails> array = new ArrayList<MessageDetails>();
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.sentbox);
	    final Bundle extras = getIntent().getExtras();
	    int id = Integer.parseInt(extras.getString("id"));
	    getMessageDetail(id);
	    lst = (ListView)findViewById(R.id.lvSentbox);
	    GetMessageDetailsAdapter adapter = new GetMessageDetailsAdapter(SentboxDetails.this,array);
	    lst.setAdapter(adapter);
	}
	
	
	private void getMessageDetail(int id)
	{
		
		ApptegyDBHelper dbHelper = new ApptegyDBHelper(this,null,1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		//String sql= "SELECT name || ' ' || lastname FROM Clients ORDER BY lastname";
		String sql= "SELECT sender,recipient,message_date,body FROM sent_message WHERE idMessage="+id;
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
		array.add(new MessageDetails(c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
    }
	
	
	
	
	
}

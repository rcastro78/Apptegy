package info.kodelogic.apptegy;

import info.kodelogic.POJO.AnswerDetails;
import info.kodelogic.POJO.SecondaryOrgsDetails;
import info.kodelogic.adapters.GetSecondaryOrgsAdapter;
import info.kodelogic.db.ApptegyDBHelper;
import android.app.Activity;
import android.database.Cursor;
import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
public class SeconOrg extends Activity{

	ArrayList<SecondaryOrgsDetails> array = new ArrayList<SecondaryOrgsDetails>(); 

	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.secondary_orgs);
	    final Bundle extras = getIntent().getExtras();
	    int id = Integer.parseInt(extras.getString("id"));
	    getSecondOrgs(id);
	    ListView list;
	    list = (ListView)findViewById(R.id.listView2);
	    GetSecondaryOrgsAdapter adapter = new GetSecondaryOrgsAdapter(this,array);
	    list.setAdapter(adapter);
	}
	
	
	private void getSecondOrgs(int id)
	{
		
		ApptegyDBHelper dbHelper = new ApptegyDBHelper(this,null,1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		//String sql= "SELECT name || ' ' || lastname FROM Clients ORDER BY lastname";
		String sql= "SELECT name FROM second_org where idParent="+id;
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
		array.add(new SecondaryOrgsDetails(c.getString(0)));
    }	
	
	
}

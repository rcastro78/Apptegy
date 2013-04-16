package info.kodelogic.apptegy;

import info.kodelogic.adapters.MsgSwitchBoardActivityAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class Messages extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.messages);
	    GridView g = (GridView) findViewById(R.id.gridMsj);
	    g.setAdapter(new MsgSwitchBoardActivityAdapter(this));
	    
	    g.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	          switch (position) {
	            case 0:
	                Intent inbox = new Intent(Messages.this,Inbox.class);
	                startActivity(inbox);
	                break;
	            case 1:
	                Intent sentbox = new Intent(Messages.this,Sentbox.class);
	                startActivity(sentbox);
	                break;
	            case 2:
	            	Intent thrash = new Intent(Messages.this,Thrash.class);
	                startActivity(thrash);
	                break;
	          	}
	          
	        	  
	        }
	      });
	    
	    
	    
	}
	    
	
}

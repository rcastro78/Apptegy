package info.kodelogic.apptegy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {

	Button btnParentOrg,btnPost,btnSportEvents,btnDir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		btnParentOrg = (Button)findViewById(R.id.btnParent);
		btnPost = (Button)findViewById(R.id.btnPosts);
		btnSportEvents = (Button)findViewById(R.id.btnSportsEv);
		btnDir = (Button)findViewById(R.id.btnDirectory);
		
		
		btnParentOrg.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
            	Intent intentParent = new Intent(Main.this,ParentOrg.class);
            	startActivity(intentParent);
            	
            }
		});
		
		
		btnSportEvents.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
            	Intent iSportEv = new Intent(Main.this,SportEvents.class);
            	startActivity(iSportEv);
            	
            }
		});
		
		
		btnPost.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
            	Intent iPosts = new Intent(Main.this,Posts.class);
            	startActivity(iPosts);
            	
            }
		});
		
		
		
		btnDir.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
            	Intent iDir = new Intent(Main.this,Directory.class);
            	startActivity(iDir);
            	
            }
		});
		
		
		
		
	}

	
}

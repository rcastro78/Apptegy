package info.kodelogic.apptegy;

import info.kodelogic.adapters.SwitchBoardActivityAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class Main extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.main);
    GridView g = (GridView) findViewById(R.id.grid);
    g.setAdapter(new SwitchBoardActivityAdapter(this));
    g.setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
          case 0:
              Intent iPosts = new Intent(Main.this,Events.class);
              startActivity(iPosts);
              break;
          case 1:
              Intent iDistrictPosts = new Intent(Main.this,DistrictPosts.class);
              startActivity(iDistrictPosts);
              break;
          case 2:
              Intent iPar = new Intent(Main.this,ParentOrg.class);
              startActivity(iPar);
              break;
          case 3:
              Intent iDir = new Intent(Main.this,Directory.class);
              startActivity(iDir);
              break;
          case 4:
              Intent iSport = new Intent(Main.this,Sports.class);
              startActivity(iSport);
              break;
          case 5:
              Intent iSportEv = new Intent(Main.this,SportEvents.class);
              startActivity(iSportEv);
              break;
          case 6:
              Intent logFacebook = new Intent(Main.this,FacebookFeeds.class);
              startActivity(logFacebook);
              break;
          case 7:
              Intent twitterFeeds = new Intent(Main.this,TwitterFeeds.class);
              startActivity(twitterFeeds);
              break;
          case 8:
        	  Intent msg = new Intent(Main.this,Messages.class);
              startActivity(msg);
          break;  
          case 9:
        	  Intent sv = new Intent(Main.this,Surveys.class);
              startActivity(sv);
          break; 
          case 10:
        	  Intent po = new Intent(Main.this,Polls.class);
              startActivity(po);
          break; 
          
        }
      }
    });


    /*
    btnParentOrg.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

              Intent intentParent = new Intent(Main.this,ParentOrg.class);
              startActivity(intentParent);

            }
    });

    btnSports.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

              Intent iSport = new Intent(Main.this,Sports.class);
              startActivity(iSport);

            }
    });


    btnSec.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

              Intent iSec = new Intent(Main.this,Secondary.class);
              startActivity(iSec);


            }
    });


    btnSportEvents.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

              Intent iSportEv = new Intent(Main.this,SportEvents.class);
              startActivity(iSportEv);

            }
    });


    btnNews.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

              Intent iDistrictPosts = new Intent(Main.this,DistrictPosts.class);
              startActivity(iDistrictPosts);

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


    */
  }
}
package info.kodelogic.apptegy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import info.kodelogic.facebook.FacebookConnector;
import info.kodelogic.POJO.FacebookDetails;
import info.kodelogic.POJO.SportsDetails;
import info.kodelogic.adapters.GetFacebookAdapter;
import info.kodelogic.adapters.GetSportsAdapter;
import info.kodelogic.apptegy.R;
import info.kodelogic.facebook.SessionEvents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CFacebook  extends Activity {

  private static final String FACEBOOK_APPID = "310496722363362";
  private static final String[] FACEBOOK_PERMISSION = {"publish_stream","read_stream"};
  private static final String TAG = "FacebookSample";
  private static final String MSG = "Message from FacebookSample";

  private final Handler mFacebookHandler = new Handler();
  private TextView loginStatus;
  private FacebookConnector facebookConnector;

  final Runnable mUpdateFacebookNotification = new Runnable() {
    public void run() {
      Toast.makeText(getBaseContext(), "Facebook feed added", Toast.LENGTH_LONG).show();
    }
  };

  ArrayList<FacebookDetails> arrayItems = new ArrayList<FacebookDetails>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_facebook);
    this.facebookConnector = new FacebookConnector(FACEBOOK_APPID, this, getApplicationContext(), FACEBOOK_PERMISSION);

    getNewsFeed();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    this.facebookConnector.getFacebook().authorizeCallback(requestCode, resultCode, data);
  }


  @Override
  protected void onResume() {
    super.onResume();
    GetFacebookAdapter adapter = new GetFacebookAdapter(this,arrayItems);
    adapter.notifyDataSetChanged();

    //updateLoginStatus();
  }

  /*public void updateLoginStatus() {
    loginStatus.setText("Logged into Twitter : " + facebookConnector.getFacebook().isSessionValid());
  }*/

  private String getFacebookMsg() {
    return  "";
  }

  public void getNewsFeed() {

    if (facebookConnector.getFacebook().isSessionValid()) {
      //postMessageInThread();
      String newsfeed = "";
      ListView list;
      list = (ListView)findViewById(R.id.listView2);
      GetFacebookAdapter adapter = new GetFacebookAdapter(this,arrayItems);
      list.setAdapter(adapter);

      try {
        newsfeed=facebookConnector.getFacebook().request("471018456285995/feed");
        JSONArray data = null;
        JSONObject obj = new JSONObject(newsfeed);
        data = obj.getJSONArray("data");

        for (int i=0; i<=data.length(); i++) {
          JSONObject c = data.getJSONObject(i);
          String msg = c.getString("message");
          if (msg.length()>0) {
            //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            arrayItems.add(new FacebookDetails(msg));

            Bundle extra;
          }
        }
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (JSONException e) {
      }

      //Toast.makeText(getApplicationContext(),newsfeed,Toast.LENGTH_LONG).show();

    } else {
      SessionEvents.AuthListener listener = new SessionEvents.AuthListener() {
        @Override
        public void onAuthSucceed() {
          //postMessageInThread();
        }

        @Override
        public void onAuthFail(String error) {

        }
      };
      SessionEvents.addAuthListener(listener);
      facebookConnector.login();
    }
  }

  private void postMessageInThread() {
    Thread t = new Thread() {
      public void run() {
        try {
          facebookConnector.postMessageOnWall(getFacebookMsg());
          mFacebookHandler.post(mUpdateFacebookNotification);
        } catch (Exception ex) {
          Log.e(TAG, "Error sending msg",ex);
        }
      }
    };
    t.start();
  }

  private void clearCredentials() {
    try {
      facebookConnector.getFacebook().logout(getApplicationContext());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

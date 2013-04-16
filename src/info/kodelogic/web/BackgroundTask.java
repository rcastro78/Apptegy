package info.kodelogic.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;


public class BackgroundTask extends AsyncTask<String, Void, String> {
  @Override
  protected String doInBackground(String... urls) {
    // Making HTTP request
    InputStream is=null;
    String result=null;

    try {
      // defaultHttpClient
      DefaultHttpClient httpClient = new DefaultHttpClient();
      HttpPost httpPost = new HttpPost("");

      HttpResponse httpResponse = httpClient.execute(httpPost);
      HttpEntity httpEntity = httpResponse.getEntity();
      is = httpEntity.getContent();

      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      } catch (ClientProtocolException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }

      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        result = sb.toString();
      } catch (Exception e) {
        Log.e("Buffer Error", "Error converting result " + e.toString());
      }
    return result;
  }

 @Override
  protected void onPostExecute(String result) {
    // try parse the string to a JSON object
    try {
      JSONObject obj=null;
      obj = new JSONObject(result);
    } catch (JSONException e) {
        Log.e("JSON Parser", "Error parsing data " + e.toString());
    }
  }
}
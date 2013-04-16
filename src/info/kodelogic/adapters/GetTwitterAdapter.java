package info.kodelogic.adapters;

import info.kodelogic.POJO.FacebookDetails;
import info.kodelogic.POJO.TwitterDetails;
import info.kodelogic.apptegy.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GetTwitterAdapter extends BaseAdapter {

  protected Activity activity;
  protected ArrayList<TwitterDetails> items;

  public GetTwitterAdapter(Activity activity, ArrayList<TwitterDetails> items) {
     this.activity = activity;
     this.items = items;
  }

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public Object getItem(int arg0) {
    return items.get(arg0);
  }

  @Override
  public long getItemId(int arg0) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;
    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      vi = inflater.inflate(R.layout.list_twitter_feed, null);
    }

    TwitterDetails item = items.get(position);
    TextView txtFeed = (TextView)vi.findViewById(R.id.txtFBFeeds);
    txtFeed.setText(item.getTweet());

    return vi;
  }
}
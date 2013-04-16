package info.kodelogic.adapters;

import info.kodelogic.apptegy.SwitchBoardActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import info.kodelogic.apptegy.R;

public class SwitchBoardActivityAdapter extends BaseAdapter {

  Context c;

  public SwitchBoardActivityAdapter(Context c) {
    this.c = c;
  }

  Context context;
  LayoutInflater layoutInflater;

  private Integer[] thumbsIds =
  {
    R.drawable.district_events, R.drawable.district_posts,
    R.drawable.secondary_orgs, R.drawable.directory,
    R.drawable.sports, R.drawable.sports_events,
    R.drawable.facebook, R.drawable.twitter_1,R.drawable.messages,
    R.drawable.poll,R.drawable.poll
  };

  private String[] thumbLabels =
  {
    "Events", "Posts", "Organizations", "Directory", "Sports",
    "Sport Events", "Facebook", "Twitter","Messages","Surveys",
    "Polls"
  };

  @Override
  public int getCount() {
    return thumbsIds.length;
  }

  @Override
  public Object getItem(int arg0) {
    return arg0;
  }

  @Override
  public long getItemId(int arg0) {
    return arg0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View v = convertView;

    if (convertView == null) {

      LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      v = li.inflate(R.layout.grid_item, null);
    }else{
    	 v = convertView;
    }
      TextView tv = (TextView)v.findViewById(R.id.grid_item_text);
      tv.setText(thumbLabels[position]);
      ImageView iv = (ImageView)v.findViewById(R.id.grid_item_image);

      switch (position) {
        case 0: iv.setImageResource(R.drawable.district_events); break;
        case 1: iv.setImageResource(R.drawable.district_posts); break;
        case 2: iv.setImageResource(R.drawable.secondary_orgs); break;
        case 3: iv.setImageResource(R.drawable.directory); break;
        case 4: iv.setImageResource(R.drawable.sports); break;
        case 5: iv.setImageResource(R.drawable.sports_events); break;
        case 6: iv.setImageResource(R.drawable.facebook); break;
        case 7: iv.setImageResource(R.drawable.twitter_1); break;
        case 8: iv.setImageResource(R.drawable.messages); break;
        case 9: iv.setImageResource(R.drawable.poll); break;
        case 10: iv.setImageResource(R.drawable.poll); break;
        
      }
    
    return v;
  }
}
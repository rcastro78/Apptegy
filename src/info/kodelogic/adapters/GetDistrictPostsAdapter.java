package info.kodelogic.adapters;

import info.kodelogic.POJO.DirectoryDetails;
import info.kodelogic.POJO.DistrictPostDetails;
import info.kodelogic.apptegy.R;
import info.kodelogic.apptegy.R.id;
import info.kodelogic.apptegy.R.layout;
import info.kodelogic.utils.Converters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GetDistrictPostsAdapter extends BaseAdapter {

  protected Activity activity;
  protected ArrayList<DistrictPostDetails> items;

  public GetDistrictPostsAdapter(Activity activity,ArrayList<DistrictPostDetails> items) {
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
    View vi=convertView;
    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      vi = inflater.inflate(R.layout.list_district_post, null);
    }

    DistrictPostDetails item = items.get(position);
    TextView txtTitle = (TextView)vi.findViewById(R.id.txtTitle);

    TextView txtData = (TextView)vi.findViewById(R.id.txtData);

    String date = item.getPublished_text();

    txtTitle.setText(item.getTitle());
    StringBuilder sb = new StringBuilder();
    sb.append("Published:");
    sb.append(date);
    sb.append("\n\nContent:");
    sb.append(item.getContent());
    sb.append("\n\nPostable Type:");
    sb.append(item.getPostable_type());
    txtData.setText(sb.toString());

    return vi;
  }
}
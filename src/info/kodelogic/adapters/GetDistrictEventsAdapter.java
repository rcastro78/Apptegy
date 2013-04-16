package info.kodelogic.adapters;

import info.kodelogic.POJO.DirectoryDetails;
import info.kodelogic.POJO.DistrictEventsDetails;
import info.kodelogic.apptegy.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GetDistrictEventsAdapter extends BaseAdapter {

  protected Activity activity;
  protected ArrayList<DistrictEventsDetails> items;

  public GetDistrictEventsAdapter(Activity activity, ArrayList<DistrictEventsDetails> items) {
     this.activity = activity;
     this.items = items;
  }

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public Object getItem(int position) {
    return items.get(position);
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
      vi = inflater.inflate(R.layout.list_district_events, null);
    }

    DistrictEventsDetails item = items.get(position);

    TextView txtTitle = (TextView)vi.findViewById(R.id.txtDistrictEvtTitle);
    TextView txtData = (TextView)vi.findViewById(R.id.txtData);

    txtTitle.setText(item.getTitle());

    StringBuilder sb = new StringBuilder();
    sb.append("Title: ");
    sb.append(item.getTitle());
    sb.append("\n\nDescription: ");
    sb.append(item.getDescription());
    sb.append("\n\nStart: ");
    sb.append(item.getStart());
    sb.append("\n\nEnd: ");
    sb.append(item.getEnd());
    sb.append("\n\nAll day: ");
    sb.append(item.getAllDay());
    sb.append("\n\nEventable Type: ");
    sb.append(item.getEventable_type());
    txtData.setText(sb.toString());

    return vi;
  }
}
package info.kodelogic.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import info.kodelogic.POJO.*;
import info.kodelogic.apptegy.R;
import info.kodelogic.apptegy.R.id;
import info.kodelogic.apptegy.R.layout;
import info.kodelogic.utils.Converters;

public class GetSportEventsAdapter extends BaseAdapter {

  protected Activity activity;
  protected ArrayList<SportEventsDetails> items;
  private ImageView imageView;

  public GetSportEventsAdapter(Activity activity, ArrayList<SportEventsDetails> items) {
    this.activity = activity;
    this.items = items;
  }

  public int getCount() {
    return items.size();
  }


  public Object getItem(int position) {
    return items.get(position);
  }


  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;

    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      vi = inflater.inflate(R.layout.list_image, null);
    }

    SportEventsDetails item = items.get(position);
    TextView txtTeamLocal=(TextView)vi.findViewById(R.id.txtTeamLocal);
    TextView txtScore=(TextView)vi.findViewById(R.id.txtScore);
    TextView txtTeamVisitor=(TextView)vi.findViewById(R.id.txtTeamVisitor);
    TextView txtAddress=(TextView)vi.findViewById(R.id.txtAddress);

    txtTeamLocal.setText(item.getHome_team()+"  "+item.getHome_score()+" - "+ item.getAway_score()+"  "+item.getAway_team());
    txtScore.setText("");
    txtTeamVisitor.setText("");

    String date = item.getDateText();

    StringBuilder sb = new StringBuilder();
    sb.append("GAME INFO\nLocation: ");
    sb.append(item.getAddress());
    sb.append(" ");
    sb.append(item.getPlace());
    sb.append(" ");
    sb.append(item.getCity_state());
    sb.append(" ");
    sb.append(item.getZip());
    sb.append("\nWhen: ");
    sb.append(date);
    txtAddress.setText(sb.toString());

    return vi;
  }
}
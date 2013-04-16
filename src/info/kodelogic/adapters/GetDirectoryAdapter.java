package info.kodelogic.adapters;

import info.kodelogic.POJO.DirectoryDetails;
import info.kodelogic.POJO.SportEventsDetails;
import info.kodelogic.apptegy.R;
import info.kodelogic.apptegy.R.id;
import info.kodelogic.apptegy.R.layout;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GetDirectoryAdapter extends BaseAdapter {

  protected Activity activity;
  protected ArrayList<DirectoryDetails> items;


  public GetDirectoryAdapter(Activity activity, ArrayList<DirectoryDetails> items) {
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
      vi = inflater.inflate(R.layout.list_directory, null);
    }
      DirectoryDetails item = items.get(position);
      TextView txtFirstName = (TextView)vi.findViewById(R.id.txtName);
      TextView txtData = (TextView)vi.findViewById(R.id.txtData);
      TextView txtTitle = (TextView)vi.findViewById(R.id.txtTitle);

      String title = "Title: ";
      txtTitle.setText(title.concat(item.getTitle()));

      txtFirstName.setText(item.getFullName());

      StringBuilder sb = new StringBuilder();
      sb.append("Address: ");
      sb.append(item.getAddress());
      sb.append("\n\nDepartment: ");
      sb.append(item.getDepartment());
      sb.append("\n\nEmail: ");
      sb.append(item.getEmail());
      sb.append("\n\nPhone Number: ");
      sb.append(item.getPhone());
      sb.append("\n\nFax Number: ");
      sb.append(item.getFaxNbr());
      txtData.setText(sb.toString());
    return vi;
  }
}
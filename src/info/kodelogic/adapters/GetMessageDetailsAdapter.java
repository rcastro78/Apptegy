package info.kodelogic.adapters;

import info.kodelogic.POJO.MessageDetails;
import info.kodelogic.apptegy.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GetMessageDetailsAdapter extends BaseAdapter{
	 protected Activity activity;
	  protected ArrayList<MessageDetails> items;
	
	  public GetMessageDetailsAdapter(Activity activity,ArrayList<MessageDetails> items)
	  {
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
	        vi = inflater.inflate(R.layout.inbox_details, null);
	      }
	    MessageDetails item = items.get(position);
	    //TextView txtTopic = (TextView)vi.findViewById(R.id.txtTopic);
	    TextView txtFrom = (TextView)vi.findViewById(R.id.txtFrom);
	    TextView txtTo = (TextView)vi.findViewById(R.id.txtTo);
	    TextView txtSent = (TextView)vi.findViewById(R.id.txtSent);
	    TextView txtBody = (TextView)vi.findViewById(R.id.txtBody);
	    
	    /*txtDate.setText(item.getMessage_date());
	    txtAgo.setText(item.getTime_ago());
	    */
	    txtFrom.setText(item.getSender());
	    txtTo.setText(item.getRecipient());
	    txtSent.setText(item.getMessage_date());
	    txtBody.setText(item.getBody());
	    
	    return vi;
	  }
}

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

public class GetMessageAdapter  extends BaseAdapter {

	  protected Activity activity;
	  protected ArrayList<MessageDetails> items;
	
	  public GetMessageAdapter(Activity activity,ArrayList<MessageDetails> items)
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
	        vi = inflater.inflate(R.layout.lst_inbox, null);
	      }
	    MessageDetails item = items.get(position);
	    TextView txtTopic = (TextView)vi.findViewById(R.id.txtTopic);
	    /*TextView txtDate = (TextView)vi.findViewById(R.id.txtDate);
	    TextView txtAgo = (TextView)vi.findViewById(R.id.txtAgo);
	    TextView txtTo = (TextView)vi.findViewById(R.id.txtTo);
	    TextView txtSender = (TextView)vi.findViewById(R.id.txtSender);
	    TextView txtBody = (TextView)vi.findViewById(R.id.txtBody);
	    */
	    txtTopic.setText(item.getTopic());
	    /*txtDate.setText(item.getMessage_date());
	    txtAgo.setText(item.getTime_ago());
	    txtTo.setText(item.getRecipient());
	    txtSender.setText(item.getSender());
	    txtBody.setText(item.getBody());
	    */
	    return vi;
	  }
}

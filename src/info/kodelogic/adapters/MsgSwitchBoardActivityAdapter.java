package info.kodelogic.adapters;

import info.kodelogic.apptegy.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MsgSwitchBoardActivityAdapter extends BaseAdapter{
	 Context c;
	 public MsgSwitchBoardActivityAdapter(Context c)
	 {
		 this.c=c;
	 }
	 
	 Context context;
	 LayoutInflater layoutInflater;

	 private Integer[] thumbsIds =
	 {
	   R.drawable.inbox,
	   R.drawable.outbox,
	   R.drawable.inbox
	 };
	  
	 private String[] thumbLabels =
	  {
	    "Inbox","Outbox","Thrash"
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
		      case 0: iv.setImageResource(R.drawable.inbox); break;
	          case 1: iv.setImageResource(R.drawable.outbox); break;
	          case 2: iv.setImageResource(R.drawable.inbox);
		    }
		      
		    return v;
	  }
}

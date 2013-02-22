package info.kodelogic.apptegy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import info.kodelogic.utils.*;
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
		
		 View vi=convertView;
         
		    if(convertView == null) {
		     
		    	 LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		         vi = inflater.inflate(R.layout.list_image, null);
		    	
		    }
		    
		    
		    SportEventsDetails item = items.get(position);
		    TextView txtTeamLocal=(TextView)vi.findViewById(R.id.txtTeamLocal);
        	TextView txtScore=(TextView)vi.findViewById(R.id.txtScore);
        	TextView txtTeamVisitor=(TextView)vi.findViewById(R.id.txtTeamVisitor);
        	
        	
        	txtTeamLocal.setText(item.getHome_team());
        	txtScore.setText(item.getHome_score()+" - "+ item.getAway_score());
        	txtTeamVisitor.setText(item.getAway_team());
		    
		    return vi;
		    
		    
		    
		
		
		
	}

	
	
	
}

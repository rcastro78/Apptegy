package info.kodelogic.utils;

import info.kodelogic.apptegy.R;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomAdapter extends ArrayAdapter<SportEventsDetails>{
private ArrayList<SportEventsDetails> items;

	public CustomAdapter(Context context, int textViewResourceId, ArrayList<SportEventsDetails> items) {
        super(context, textViewResourceId, items);
        this.items = items;
		}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            
            if (v == null) {
               
            }
            
            SportEventsDetails sportEventsDetails = items.get(position);
            if (sportEventsDetails!=null)
            {
            	TextView txtTeamLocal=(TextView)v.findViewById(R.id.txtTeamLocal);
            	TextView txtScore=(TextView)v.findViewById(R.id.txtScore);
            	TextView txtTeamVisitor=(TextView)v.findViewById(R.id.txtTeamVisitor);
            	
            	if (txtTeamLocal!=null) {txtTeamLocal.setText(sportEventsDetails.getHome_team());}
            	if (txtScore!=null) {txtScore.setText(sportEventsDetails.getHome_score()+" - "+ sportEventsDetails.getAway_score());}
            	if (txtTeamVisitor!=null) {txtTeamVisitor.setText(sportEventsDetails.getAway_team());}
            	
            }
            
	return v;
	
	}
	
}

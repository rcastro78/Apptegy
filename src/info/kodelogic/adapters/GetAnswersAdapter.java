package info.kodelogic.adapters;

import info.kodelogic.POJO.AnswerDetails;
import info.kodelogic.apptegy.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GetAnswersAdapter extends BaseAdapter{

  protected Activity activity;
  protected ArrayList<AnswerDetails> items;
  
  public GetAnswersAdapter(Activity activity, ArrayList<AnswerDetails> items) {
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
		 vi = inflater.inflate(R.layout.list_survey, null);
	}
	AnswerDetails item = items.get(position);
    TextView txtSurvey = (TextView)vi.findViewById(R.id.txtSurvey);
    txtSurvey.setText(item.getqContent());    
   return vi;	    
 }	    
}

package info.kodelogic.apptegy;

import info.kodelogic.utils.DirectoryDetails;
import info.kodelogic.utils.SportEventsDetails;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GetDirectoryAdapter  extends BaseAdapter{

	protected Activity activity;
	protected ArrayList<DirectoryDetails> items;
	

	public GetDirectoryAdapter(Activity activity,ArrayList<DirectoryDetails> items)
	{
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
        
	    if(convertView == null) {
	     
	    	 LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	         vi = inflater.inflate(R.layout.list_directory, null);
	    }
	         DirectoryDetails item = items.get(position);
	    	 TextView txtAddress = (TextView)vi.findViewById(R.id.txtAddress);
	    	 TextView txtContType = (TextView)vi.findViewById(R.id.txtContType);
	    	 TextView txtDepartment = (TextView)vi.findViewById(R.id.txtDepartment);
	    	 TextView txtEmail = (TextView)vi.findViewById(R.id.txtEmail);
	    	 TextView txtFaxNumber = (TextView)vi.findViewById(R.id.txtFaxNumber);
	    	 TextView txtFirstName = (TextView)vi.findViewById(R.id.txtFirstName);
	    	 TextView txtLastName = (TextView)vi.findViewById(R.id.txtLastName);
	    	 TextView txtNotes = (TextView)vi.findViewById(R.id.txtNotes);
	    	 TextView txtPhoneNumber = (TextView)vi.findViewById(R.id.txtPhoneNumber);
	    	 TextView txtTitle = (TextView)vi.findViewById(R.id.txtTitle);
	    	 	 
	    	txtAddress.setText(item.getAddress());
	    	txtContType.setText(item.getC_type());
	    	txtDepartment.setText(item.getDepartment());
	    	txtEmail.setText(item.getEmail());
	    	txtFaxNumber.setText(item.getFaxNbr());
	    	txtFirstName.setText(item.getfName());
	    	txtLastName.setText(item.getlName());
	    	txtNotes.setText(item.getNote());
	    	txtPhoneNumber.setText(item.getPhone());
	    	txtTitle.setText(item.getTitle());
	    	
	    
		return vi;
	}

}

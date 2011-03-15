package air.Repper;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.lang.Math;
import java.util.ArrayList;

public class RepsActivity extends ListActivity {
	static final Double[] PERCENTS = new Double[] {
		1.00, 0.94, 0.91, 0.88333, 0.86, 0.83333, 0.805, 0.77666, 0.7516666, 0.73333,
		0.7016666, 0.675, 0.655, 0.6375, 0.625
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  Bundle extras = getIntent().getExtras();
	  Double weight = Double.valueOf(extras.getString("weight").trim());
	  Integer reps = Integer.valueOf(extras.getString("reps").trim());
	  String unit = extras.getString("unit").trim();
	  unit = unit.substring(0, unit.indexOf(" "));
	  ArrayList<RepperItem> repperItems = new ArrayList<RepperItem>();
	  
	  for (int i = 0; i < 15; i++) {
		  RepperItem ri = new RepperItem();
		  ri.setReps(String.valueOf(i+1));
		  ri.setUnit(unit);
		  
		  if (unit.equals("st")) {
			  Double tmpWeight = weight * 10;
			  long value = Math.round(tmpWeight + ((PERCENTS[i] - PERCENTS[reps-1]) * tmpWeight));
			  ri.setWeight(String.valueOf(Double.valueOf(value)/10.0));
		  } else {
			  ri.setWeight(String.valueOf(Math.round(weight + ((PERCENTS[i] - PERCENTS[reps-1]) * weight))));
		  }
		  repperItems.add(ri);
	  }
	  RepperItemAdapter ria = new RepperItemAdapter(this, R.layout.list_item, repperItems);
	  ria.selectedRep = reps;
	  setListAdapter(ria);
	}
	
	private class RepperItemAdapter extends ArrayAdapter<RepperItem> {
		private ArrayList<RepperItem> items;
		public Integer selectedRep;
		
		public RepperItemAdapter(Context context, int textViewResourceId, ArrayList<RepperItem> items) {
			super(context, textViewResourceId, items);
			this.items = items;
			this.selectedRep = 1;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
	        if (v == null) {
	            LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.list_item, null);
	        }
	           
	        final RepperItem o = items.get(position);
	        if (o != null) {
                TextView tt = (TextView) v.findViewById(R.id.weight);
                TextView bt = (TextView) v.findViewById(R.id.reps);
                
                if (tt != null) {
                	tt.setText(o.getWeight() + " " + o.getUnit());                            
                }
                if(bt != null){
                    bt.setText(o.getReps());
                }
                if (this.selectedRep == Integer.valueOf(o.getReps())) { 	
                	v.setBackgroundResource(R.drawable.list_item_gradient_sel);
                } else {
                	v.setBackgroundResource(R.drawable.list_item_gradient);
                }
	        }
	        return v;
		}
	}
}

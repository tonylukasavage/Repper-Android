package air.Repper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Toast toast = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinnerUnit);
        Button button = (Button) findViewById(R.id.button1);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.reps_array, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.reps_item);
        spinner.setAdapter(adapter);
        
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.units_array, R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        
        button.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
    			EditText et = (EditText) findViewById(R.id.editText1);
    			Spinner spin = (Spinner) findViewById(R.id.spinner1);
    			Spinner spin2 = (Spinner) findViewById(R.id.spinnerUnit);
    			@SuppressWarnings("unused")
				Double dWeight;
    			
    			try {
    				dWeight = Double.valueOf(et.getText().toString().trim());
    			} catch (NumberFormatException exception) {
    				if (toast == null) {
    					toast = Toast.makeText(getApplicationContext(), "Invalid Weight!", Toast.LENGTH_SHORT);
    				}
    				toast.cancel();
    				toast.show();
    				return;
    			}
    			
    			Intent intent = new Intent(v.getContext(), RepsActivity.class);
    			intent.putExtra("weight", et.getText().toString());
    			intent.putExtra("reps", spin.getSelectedItem().toString());
    			intent.putExtra("unit", spin2.getSelectedItem().toString());
    			startActivity(intent);
    		}
        });
    }
}
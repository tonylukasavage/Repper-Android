package air.Repper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.Spinner;

public class RepperSpinner extends Spinner {
	public RepperSpinner(Context context) {
		super(context);
	}

	public RepperSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RepperSpinner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public boolean performClick() {
		InputMethodManager imm = (InputMethodManager)this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
		return super.performClick();
	}
	

}

package com.demo.crazytipcalc;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.widget.SeekBar.*;

public class CrazyTipCalc extends ActionBarActivity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String CURRENT_TIP = "CURRENT_TIP";
	private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";
	
	private double billBeforeTip;
	private double tipAmount;
	private double finalBill;
	
	
	EditText billBeforeTipET;
	EditText tipAmountET;
	EditText finalBillET;
	SeekBar tipSeekBar;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crazy_tip_calc);
		
		if(savedInstanceState == null){
			//just started
			billBeforeTip= 0.0;
			tipAmount = 0.15;
			finalBill= 0.0;
		}
		else{
			billBeforeTip= savedInstanceState.getDouble(BILL_WITHOUT_TIP);
			tipAmount = savedInstanceState.getDouble(CURRENT_TIP);
			finalBill= savedInstanceState.getDouble(TOTAL_BILL);
		}
		//initialize edit texts
		billBeforeTipET =((EditText)findViewById(R.id.billEditText));
		tipAmountET =((EditText)findViewById(R.id.tipEditText));
		finalBillET =((EditText)findViewById(R.id.finalBillEditText));
		
		//second part
		//init seekbar and add change listener
		tipSeekBar = (SeekBar) findViewById(R.id.changeTipSeekBar);
		tipSeekBar.setOnSeekBarChangeListener(tipSeekBarListener);
		
		billBeforeTipET.addTextChangedListener(billBeforeTipListener);
		
	}
	
	// bill changed
	
	private TextWatcher billBeforeTipListener = new TextWatcher(){
		@Override
		public void afterTextChanged(Editable arg0){
			
		}
		public void afterTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onTextChanged(CharSequence a0, int a2, int a3, int a4){
			try{
				billBeforeTip = Double.parseDouble(a0.toString());
				
			}catch(Exception e){
				billBeforeTip=0.0;
			}
			updateTipAndFinalBill();
			
		}
	
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			
		}
	
		
	};
	
	private void updateTipAndFinalBill(){
		
		double tipAmount= Double.parseDouble(tipAmountET.getText().toString());
		double finalBill = billBeforeTip + (billBeforeTip*tipAmount);
		finalBillET.setText(String.format("%.02f",finalBill));
	}

	protected void onSaveInstanceState(Bundle outstate)
	{
		super.onSaveInstanceState(outstate);
		
		outstate.putDouble(TOTAL_BILL, finalBill);
		outstate.putDouble(CURRENT_TIP, tipAmount);
		outstate.putDouble(TOTAL_BILL, finalBill);
		
	}
	
	private OnSeekBarChangeListener tipSeekBarListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tipAmount = tipSeekBar.getProgress()*.01;
				
				tipAmountET.setText(String.format("%.01f",tipAmount));
				updateTipAndFinalBill();
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crazy_tip_calc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

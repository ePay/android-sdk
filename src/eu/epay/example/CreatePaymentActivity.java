package eu.epay.example;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import eu.epay.library.*;

public class CreatePaymentActivity extends Activity {

	private WebView view;
	private Map<String, String> data = new HashMap<String, String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_payment);

		Spinner spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);
		FillData(spinnerCurrency);

		Spinner spinnerPaymentCollection = (Spinner) findViewById(R.id.spinnerPaymentCollection);
		FillData(spinnerPaymentCollection);

		Spinner spinnerLockPaymentCollection = (Spinner) findViewById(R.id.spinnerLockPaymentCollection);
		FillData(spinnerLockPaymentCollection);
		
		Spinner spinnerlanguage = (Spinner) findViewById(R.id.spinnerLanguage);
		FillData(spinnerlanguage);
		
		//Set dummy value for orderid and amount
		EditText edittextAmount = (EditText)findViewById(R.id.editTextAmount);
		EditText edittextOrderID = (EditText)findViewById(R.id.editTextOrderID);
		
		edittextAmount.setText("1000");
		edittextOrderID.setText("123abc");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_payment, menu);
		return true;
	}

	public void OpenPaymentWindow(View v) {
		
		//Setting data
		GetData();

		//Set payment view as main
		setContentView(R.layout.activity_payment);
		view = (WebView) findViewById(R.id.webView1);

		EpayWebView paymentView = new EpayWebView(new MyPaymentResultListener(this), view, false);
		view = paymentView.LoadPaymentWindow(data);
	}
	
	public Map<String, String> GetData()
	{
		//Finding all objects in view to get values
		Spinner spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);
		Spinner spinnerPaymentCollection = (Spinner) findViewById(R.id.spinnerPaymentCollection);
		Spinner spinnerLockPaymentCollection = (Spinner) findViewById(R.id.spinnerLockPaymentCollection);
		Spinner spinnerLanguage = (Spinner) findViewById(R.id.spinnerLanguage);
		EditText edittextAmount   = (EditText)findViewById(R.id.editTextAmount);
		EditText edittextOrderID   = (EditText)findViewById(R.id.editTextOrderID);
		
		//http://tech.epay.dk/en/specification#258
		data.put("merchantnumber", "99999999");
		
		//http://tech.epay.dk/en/specification#259
		data.put("currency", GetValueFromSpinner(spinnerCurrency));
		
		//http://tech.epay.dk/en/specification#260
		data.put("amount", edittextAmount.getText().toString());
		
		//http://tech.epay.dk/en/specification#261
		data.put("orderid", edittextOrderID.getText().toString());

		//http://tech.epay.dk/en/specification#262
		//data.put("windowid", "1");
		
		//http://tech.epay.dk/en/specification#263
		data.put("paymentcollection", GetIndexPositionFromSpinner(spinnerPaymentCollection));
		
		//http://tech.epay.dk/en/specification#264
		data.put("lockpaymentcollection", GetIndexPositionFromSpinner(spinnerLockPaymentCollection));

		//http://tech.epay.dk/en/specification#265
		//data.put("paymenttype", "1,2,3");
		
		//http://tech.epay.dk/en/specification#266
		data.put("language", GetIndexPositionFromSpinner(spinnerLanguage));
		
		//http://tech.epay.dk/en/specification#267
		data.put("encoding", "UTF-8");
		
		//http://tech.epay.dk/en/specification#269
		//data.put("mobilecssurl", "");

		//http://tech.epay.dk/en/specification#270
		data.put("instantcapture", "0");
		
		//http://tech.epay.dk/en/specification#272
		//data.put("splitpayment", "0");
		
		//http://tech.epay.dk/en/specification#275
		//data.put("callbackurl", "");

		//http://tech.epay.dk/en/specification#276
		data.put("instantcallback", "1");

		//http://tech.epay.dk/en/specification#278
		data.put("ordertext", "This is the order text");
		
		//http://tech.epay.dk/en/specification#279
		//data.put("group", "group");

		//http://tech.epay.dk/en/specification#280
		data.put("description", "This is the description text");
		
		//http://tech.epay.dk/en/specification#281
		//data.put("hash", "");

		//http://tech.epay.dk/en/specification#282
		//data.put("subscription", "0");
		
		//http://tech.epay.dk/en/specification#283
		//data.put("subscriptionname", "0");
		
		//http://tech.epay.dk/en/specification#284
		//data.put("mailreceipt", "");
		
		//http://tech.epay.dk/en/specification#286
		//data.put("googletracker", "0");
		
		//http://tech.epay.dk/en/specification#287
		//data.put("backgroundcolor", "");
		
		//http://tech.epay.dk/en/specification#288
		//data.put("opacity", "");
		
		//http://tech.epay.dk/en/specification#289
		data.put("declinetext", "This is the decline text");
		
		return data;
	}
	
	private String GetIndexPositionFromSpinner(Spinner spinner)
	{
		String result = "";
		
		if(spinner != null)
		{
			result = String.valueOf(spinner.getSelectedItemPosition());
		}
		
		return result;
	}
	
	private String GetValueFromSpinner(Spinner spinner)
	{
		String result = "";
		
		if(spinner != null)
		{
			result = String.valueOf(spinner.getSelectedItem().toString());
		}
		
		return result;
	}

	private void FillData(Spinner spinner) {
		ArrayAdapter<String> adapter = null;

		if (spinner.getTag().equals("spinnerCurrency")) {
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,
					DataFiller.FillCurrencies());
		}

		else if (spinner.getTag().equals("spinnerpaymentcollection")) {
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,
					DataFiller.FillPaymentCollection());
		}

		else if (spinner.getTag().equals("spinnerlockpaymentcollection")) {
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,
					DataFiller.FillLockPaymentCollection());
		}
		
		else if (spinner.getTag().equals("spinnerlanguage")) {
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1,
					DataFiller.FillLanguage());
		}

		spinner.setAdapter(adapter);

	}

}

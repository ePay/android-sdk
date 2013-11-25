package eu.epay.example;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import eu.epay.library.*;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private boolean haveNetworkConnection() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		this.getApplicationContext();
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	public void TestPayment(View v) {
		
		// Check for Internet connection
		if (haveNetworkConnection()) {
			Intent intent = new Intent(this, CreatePaymentActivity.class);
			startActivity(intent);
		}

		else {
			Toast.makeText(this, "No internet connection found", Toast.LENGTH_LONG).show();
		}

	}

	public void ShowPaymentWindow(View v) {
		
		// Check for Internet connection
		if (haveNetworkConnection()) {
			setContentView(R.layout.activity_payment);
			WebView view = (WebView) findViewById(R.id.webView1);

			EpayWebView paymentView = new EpayWebView(new MyPaymentResultListener(this), view, true, "ePayTest1234");

			view = paymentView.LoadPaymentWindow(PaymentDataConstructor.ConstructDummyData());
		}

		else {
			Toast.makeText(this, "No internet connection found", Toast.LENGTH_LONG).show();
		}

	}
}

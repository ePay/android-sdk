package eu.epay.example;

import java.util.HashMap;
import java.util.Map;


public class PaymentDataConstructor {

	public static Map<String, String> ConstructDummyData() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("merchantnumber", "8885334");
		data.put("currency", "DKK");
		data.put("amount", "1000");
		data.put("orderid", "1234567890");
		// data.put("windowid", "1");
		data.put("paymentcollection", "1");
		data.put("lockpaymentcollection", "1");
		// data.put("paymenttype", "1");
		// data.put("language", "1");
		// data.put("encoding", "UTF-8");
		// data.put("mobilecssurl", "");
		data.put("instantcapture", "0");
		// data.put("splitpayment", "0");
		data.put("instantcallback", "1");
		// data.put("callbackurl", "");
		data.put("ordertext", "Betaling");
		// data.put("group", "group");
		data.put("description", "description");
		// data.put("hash", "");
		// data.put("subscription", "0");
		// data.put("subscriptionname", "0");

		// data.put("mailreceipt", "");
		// data.put("googletracker", "0");
		// data.put("backgroundcolor", "");
		// data.put("opacity", "");
		data.put("declinetext", "declinetext");
		// data.put("googletracker", "0");

		return data;
	}
	
}

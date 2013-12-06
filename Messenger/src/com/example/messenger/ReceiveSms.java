package com.example.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * ReceiveSms class listens for an incoming Sms Text and displays it to the user as a Toast.
 * @author Lisa
 *
 */

public class ReceiveSms extends BroadcastReceiver {
	
	@Override	
	public void onReceive(Context context, Intent intent) {
		// This code was more or less copied from an example I found at
		// http://androidexample.com/Incomming_SMS_Broadcast_Receiver_-_Android_Example/index.php?view=article_discription&aid=62&aaid=87
		
		// TODO: createFromPdu(byte[]) is being deprecated. Replace call with createFromPdu(byte[], String)
		// passing in the additional format parameter 
		
		final Bundle bundle = intent.getExtras();
		
		try {
			if (bundle != null) {
				final Object[] pdusObject = (Object[]) bundle.get("pdus");
				
				for (int i = 0; i < pdusObject.length; i++) {
					
					SmsMessage incomingMessage = SmsMessage.createFromPdu((byte[]) pdusObject[i]);
					String phoneNumber = incomingMessage.getDisplayOriginatingAddress();
					String message = incomingMessage.getDisplayMessageBody();
					
					int duration = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(context, "Phone: " + phoneNumber + ", Message: " + message, duration);
					toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
					toast.show();
				}
			}
		} catch (Exception e) {
			Log.e("ReceiveSms", "Exception in ReceiveSms.onReceive():" +e);
		}

	}

}

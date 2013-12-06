package com.example.messenger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * MainActivity class takes Phone Number and Message input from the user and sends an Sms Text
 * @author Lisa
 *
 */

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
	
	/**
	 * Gets a user entered phone number and message from the current activity
	 * and passes it to a method which will send a text message.
	 * @param view
	 */
	
	public void sendMessage(View view) {
		// Get the phone number and message entered by the user
		final EditText phoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
		final EditText message = (EditText) findViewById(R.id.editTextMessage);
		
		// Process the message
		sendSms(phoneNumber.getText().toString(), message.getText().toString());
		
		// Clear the screen so the user can enter a new message
		refreshActivity();
	}
	
	/**
	 * Sends the message as an SMS Text to the given phone number
	 * @param phoneNumber
	 * @param message
	 */
	
	private void sendSms(String phoneNumber, String message) {
		// Send the text message user smsManager
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, 
								   null, 
								   message, 
								   null,
								   null);
		
		// Notify the user that the message was sent
		notifyUser(phoneNumber, message);
	}

	/**
	 * Displays an {@link AlertDialog} with the given phone number and message
	 * @param phoneNumber
	 * @param message
	 */
	
	private void notifyUser(String phoneNumber, String message) {
		// Display a message to the user indicating the message was sent.
		AlertDialog.Builder builder = new AlertDialog.Builder(this);	
		builder.setTitle("Congratulations!");
		builder.setMessage("Your message '" + message + "' was sent to " + phoneNumber + ".");
		
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// user clicked OK, close the dialog
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	/**
	 * Resets the values in the current Activity
	 */
	
	private void refreshActivity() {
		EditText phoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
		EditText message = (EditText) findViewById(R.id.editTextMessage);
		
		phoneNumber.setText("");
		message.setText("");
	}

}

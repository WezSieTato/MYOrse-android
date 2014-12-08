package com.siema.myorse;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MYOrseActivity extends Activity {

    private final int PICK_CONTACT = 666;
    private MYOrseListener listener;

    private TextView lblUsername;
    private Button btnStartStop;
    private Button btnPickContact;
    private CheckBox checkBoxSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorse);
        listener = new MYOrseListener();
        listener.setContex(getApplicationContext());
        registerReceiver(listener, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.myorse, menu);
        lblUsername = (TextView) findViewById(R.id.lblUsername);
        btnStartStop = (Button) findViewById(R.id.btnStartStop);
        btnPickContact = (Button) findViewById(R.id.btnPickContact);
        checkBoxSMS = (CheckBox) findViewById(R.id.checkBoxSMS);

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

    public void buttonPickContactOnClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    public void buttonStartStopContactOnClick(View v) {
        boolean enabled = listener.isEnabled();
        listener.setEnabledSMSSending(checkBoxSMS.isChecked());
        listener.setEnabled(!enabled);
        btnPickContact.setEnabled(enabled);
        checkBoxSMS.setEnabled(enabled);
        String txt = getString(enabled ? R.string.START_MYORSE_SERVICE : R.string.STOP_MYORSE_SERVICE);
        btnStartStop.setText(txt);

    }

    private void setContact(ContactInfo info) {
        listener.setUsername(info);
        lblUsername.setText(info.getName());
        btnStartStop.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PICK_CONTACT:
                if (resultCode == Activity.RESULT_OK) {
                    ContactInfo info = ContactInfoResolver.getContactFromIntentInOnActivityResolve(data, getContentResolver());
                    if (info != null) {
                        if (info.hasPhoneNumber()) {
                            setContact(info);
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.no_number_phone), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        }
    }
}

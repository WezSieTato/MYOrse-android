package com.siema.myorse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.Hub;
import com.thalmic.myo.scanner.ScanActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MYOrseActivity extends Activity {

    private final int PICK_CONTACT = 666;
    public static final String PreferencesKey = "MYOrsePreferencesKey";
    private final String PreferencesKeyName = "MYOrsePreferencesKeyName";
    private final String PreferencesKeyPhone = "MYOrsePreferencesKeyPhone";
    private final String PreferencesKeyBool = "MYOrsePreferencesKeyBool";

    private MYOrseListener listener;

    private TextView lblUsername;
    private Button btnStartStop;
    private Button btnPickContact;
    private CheckBox checkBoxSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorse);

        lblUsername = (TextView) findViewById(R.id.lblUsername);
        btnStartStop = (Button) findViewById(R.id.btnStartStop);
        btnPickContact = (Button) findViewById(R.id.btnPickContact);
        checkBoxSMS = (CheckBox) findViewById(R.id.checkBoxSMS);

        Context context = getApplicationContext();
        listener = new MYOrseListener(context);
        registerReceiver(listener, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));

        SharedPreferences preferences = context.getSharedPreferences(PreferencesKey, Context.MODE_PRIVATE);
        boolean isSaved = preferences.getBoolean(PreferencesKeyBool, false);

        if (isSaved) {
            String name = preferences.getString(PreferencesKeyName, "");
            Set<String> setPhones = preferences.getStringSet(PreferencesKeyPhone, null);
            List<String> listPhones = new ArrayList<String>(setPhones);

            ContactInfo info = new ContactInfo(name);
            info.setPhoneNumbers(listPhones);
            setContact(info, false);

        }

        Hub hub = Hub.getInstance();
        if (!hub.init(this)) {
//            finish();
//            return;
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.myorse, menu);
//
//        return true;
//    }

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

    public void buttonMYOOnClick(View v){
        Context context = getApplicationContext();
        Intent intent = new Intent(context, ScanActivity.class);
        context.startActivity(intent);
    }

    private void setContact(ContactInfo info, boolean save) {
        listener.setUsername(info);
        lblUsername.setText(info.getName());
        btnStartStop.setEnabled(true);

        if (save) {
            Context context = getApplicationContext();
            SharedPreferences preferences = context.getSharedPreferences(PreferencesKey, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(PreferencesKeyName, info.getName());

            Set<String> setPhones = new HashSet<String>(info.getPhoneNumbers());
            editor.putStringSet(PreferencesKeyPhone, setPhones);
            editor.putBoolean(PreferencesKeyBool, true);
            editor.commit();
        }
    }

    private void setContact(ContactInfo info) {
        setContact(info, true);
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

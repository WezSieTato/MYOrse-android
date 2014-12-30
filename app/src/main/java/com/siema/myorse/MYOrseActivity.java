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

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Arm;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.XDirection;
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
    private final String PreferencesKeyBoolSMS = "MYOrsePreferencesKeyBoolSMS";

    private MYOrseListener listener;

    private TextView lblUsername;
    private Button btnStartStop;
    private Button btnPickContact;
    private CheckBox checkBoxSMS;

    private int synced = 0;

    private DeviceListener myoSyncListener = new AbstractDeviceListener() {
        @Override
        public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection) {
            ++synced;
            updateUI();
        }

        @Override
        public void onArmUnsync(Myo myo, long timestamp) {
            --synced;
            updateUI();
        }

        private void updateUI(){
            btnStartStop.setEnabled(canStartReceiver());
            TextView txt = (TextView)findViewById(R.id.txtNumberSyn);
            txt.setText(String.valueOf(synced));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorse);

        Hub hub = Hub.getInstance();
        if (!hub.init(this, getPackageName())) {
        }
        hub.addListener(myoSyncListener);

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

        boolean isSMS = preferences.getBoolean(PreferencesKeyBoolSMS, false);
        checkBoxSMS.setChecked(isSMS);

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

    public void checkBoxSMSOnClick(View v){
        CheckBox checkBox = (CheckBox)v;
        Context context = getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(PreferencesKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PreferencesKeyBoolSMS, checkBox.isChecked());
        editor.commit();

    }

    public void buttonPickContactOnClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    public void buttonStartStopOnClick(View v) {
        boolean enabled = listener.isEnabled();
        listener.setEnabledSMSSending(checkBoxSMS.isChecked());
        if(!enabled)
            listener.start();
        else
            listener.stop();;
        btnPickContact.setEnabled(enabled);
        checkBoxSMS.setEnabled(enabled);
        String txt = getString(enabled ? R.string.START_MYORSE_SERVICE : R.string.STOP_MYORSE_SERVICE);
        btnStartStop.setText(txt);

        Button myo = (Button)findViewById(R.id.btnMYO);
        myo.setEnabled(enabled);

    }

    public void buttonMYOOnClick(View v){
        Context context = getApplicationContext();
        Intent intent = new Intent(MYOrseActivity.this, ScanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void setContact(ContactInfo info, boolean save) {
        listener.setUsername(info);
        lblUsername.setText(info.getName());
        btnStartStop.setEnabled(canStartReceiver());

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

    private boolean canStartReceiver(){
        return listener.getUsername() != null && synced > 0;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Hub.getInstance().removeListener(myoSyncListener);

        if (isFinishing()) {
            Hub.getInstance().shutdown();
        }
    }
}

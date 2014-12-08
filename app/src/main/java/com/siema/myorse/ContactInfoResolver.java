package com.siema.myorse;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 08/12/14.
 */
public class ContactInfoResolver {

    public static ContactInfo getContactFromIntentInOnActivityResolve(Intent data, ContentResolver contentResolver) {

        ContactInfo info;

        Uri contactData = data.getData();
        Cursor c = contentResolver.query(contactData, null, null, null, null);
        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            info = new ContactInfo(name);

        } else {
            return null;
        }


        Cursor cursor;
        String phoneNumber;
        int phoneIdx = 0;
        try {
            Uri result = data.getData();
            String id = result.getLastPathSegment();
            cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id}, null);
            phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
            if (cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {
                    phoneNumber = cursor.getString(phoneIdx);
                    info.addPhoneNumber(phoneNumber);
                    cursor.moveToNext();
                }
            } else {
                //no results actions
            }
        } catch (Exception e) {
            //error actions
        } finally {

        }

        return info;

    }
}

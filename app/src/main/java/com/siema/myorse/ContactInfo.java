package com.siema.myorse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 08/12/14.
 */
public class ContactInfo {
    public String getName() {
        return name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    private String name;
    private List< String > phoneNumbers;


    public ContactInfo(String name) {
        this.name = name;
        phoneNumbers = new ArrayList< String >();
    }

    public ContactInfo(List<String> phoneNumbers) {
        phoneNumbers.clear();
        for (String number : phoneNumbers){
            addPhoneNumber(number);
        }
    }

    public void addPhoneNumber(String phoneNumber){
        phoneNumber = phoneNumber.replaceAll("\\s+","");
        phoneNumbers.add(phoneNumber);
    }

    public boolean hasPhoneNumber(){
        return !phoneNumbers.isEmpty();
    }

    public boolean hasThisPhoneNumber(String phoneNumber){
        for(String number : phoneNumbers){
            if(phoneNumber.endsWith(number)){
                return true;
            }
        }
        return false;
    }
}

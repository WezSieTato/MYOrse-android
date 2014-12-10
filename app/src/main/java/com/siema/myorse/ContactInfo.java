package com.siema.myorse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 08/12/14.
 */
public class ContactInfo {
    private String name;
    private List< String > phoneNumbers;

    public ContactInfo(String name) {
        this.name = name;
        phoneNumbers = new ArrayList< String >();
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers.clear();
        for (String number : phoneNumbers){
            addPhoneNumber(number);
        }
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void addPhoneNumber(String phoneNumber){
        phoneNumber = phoneNumber.replaceAll("\\s+","");
        phoneNumbers.add(phoneNumber);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

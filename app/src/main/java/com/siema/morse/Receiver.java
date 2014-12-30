package com.siema.morse;

import com.siema.morse.model.Char;
import com.siema.morse.model.Dash;
import com.siema.morse.model.Dot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Stepnowski on 27/12/14.
 */
public class Receiver {
    private List<Char> code = new ArrayList<Char>();
    private List<Char> lastInvalidCode = null;
    private String stringCode = "";
    private Table morseTable;
    private Dot dot = new Dot();
    private Dash dash = new Dash();
    private int delay = 0;
    private Character lastCharacter = null;
    private String message = "";

    public Receiver(Table morseTable) {
        this.morseTable = morseTable;
    }

    public void clear(){
        delay = 0;
        lastCharacter = null;
        message = "";
        code.clear();
        lastInvalidCode = null;
        stringCode = "";

    }

    public void putDot(){
        delay = 0;
        code.add(dot);
        stringCode += ".";
    }

    public void putDash(){
        delay = 0;
        code.add(dash);
        stringCode += "-";
    }

    public boolean putDelay(){
        stringCode += "/";
        switch (delay){
            case 0:
            translateChar();
            putLast();
                break;

            case 1:
                lastCharacter = ' ';
                putLast();
                break;

            case 2:
                delay = 0;
                return true;
        }
        ++delay;
        return false;
    }

    public String getMessage() {
        if(!code.isEmpty()){
            translateChar();
            putLast();
        }

        if(message.equals(" "))
            return "";

        if(message.endsWith(" ")){
            return message.substring(0, message.length() - 1);
        }

        return message;
    }

    public String getStringCode() {
        return stringCode;
    }
    private void translateChar(){
        if(code.size() == 0){
            lastCharacter = ' ';
            return;
        }

        lastCharacter = morseTable.charForCode(code);
        if(lastCharacter == null){
            lastInvalidCode = new ArrayList<Char>();
            lastInvalidCode.addAll(code);
        } else
            lastInvalidCode = null;
        code.clear();
    }

    private void putLast(){
        if(lastCharacter != null)
            message = message + lastCharacter;
        else if(lastInvalidCode != null) {
            message += "<";

                for (Char ch : lastInvalidCode) {
                    if (ch instanceof Dot)
                        message += ".";
                    else
                        message += "-";
                }
            message += ">";
        }
        lastCharacter = null;
        lastInvalidCode = null;
    }

}

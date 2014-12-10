package com.siema.morse;

import com.siema.morse.model.Char;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Klasa odpowiedzialna za transmisj� informacji Morse'm
 * 
 * @author Quetz
 * 
 */
public class Broadcaster {
    private BroadcasterDelegate broadcasterDelegate;
    private Transmitter transmitter;
    private String message;
    private boolean transmitting;
    private Translator translator;

    private Iterator<Char> iterator;
    private List<Char> code;
    private ScheduledExecutorService worker;

    public Broadcaster(Translator translator){
		this.translator = translator;
        this.broadcasterDelegate = null;
        this.transmitter = null;
        transmitting = false;
	}

    public void sendMessage(String message){
        this.message = message;
        this.code = translator.translate(message);
        iterator = code.iterator();
        worker = Executors.newSingleThreadScheduledExecutor();
        transmitting = true;

        transmitNextSignal();
    }

    private void transmitNextSignal(){
        if(!iterator.hasNext()){
            this.transmitionEnded();
        }

        Char morse = iterator.next();

        if(morse.isEmitSound()){
            transmitter.transmit(morse.getTime());
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                transmitNextSignal();
            }
        };

        worker.schedule(task, morse.getTime(), TimeUnit.SECONDS);

    }

    private void transmitionEnded(){
        transmitting = false;
        if(broadcasterDelegate != null)
            broadcasterDelegate.broadcasterDidEndTransmition(this);
    }

    public BroadcasterDelegate getBroadcasterDelegate() {
        return broadcasterDelegate;
    }

    public void setBroadcasterDelegate(BroadcasterDelegate broadcasterDelegate) {
        this.broadcasterDelegate = broadcasterDelegate;
    }

    public Transmitter getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    public Translator getTranslator() {
        return translator;
    }

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

    public String getMessage() {
        return message;
    }

    public boolean isTransmitting() {
        return transmitting;
    }
}

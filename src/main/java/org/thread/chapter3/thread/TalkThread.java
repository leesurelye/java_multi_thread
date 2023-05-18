package org.thread.chapter3.thread;

import org.thread.chapter3.obj.Request;
import org.thread.chapter3.obj.RequestQueue;

public class TalkThread extends Thread {
    private final RequestQueue input;

    private final RequestQueue output;

    public TalkThread(RequestQueue input, RequestQueue output, String name)
    {
        super(name);
        this.input = input;
        this.output = output;
    }

    @Override
    public void run()
    {
        System.out.println(currentThread().getName() + " : BEGIN");
        for(int i = 0; i < 20; i++) {
            //response
            Request response = new Request(i + "!");
            System.out.println(currentThread().getName() + " puts " + response);
            output.putRequest(response);

            Request request = input.getRequest();
            System.out.println(currentThread().getName() + " gets " + request);
        }
    }
}

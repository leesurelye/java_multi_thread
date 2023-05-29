package org.thread.chapter6.thread;

import org.thread.chapter6.obj.Data;

public class WriteThread extends Thread
{
    private final Data data;
    private final String filler;

    private int index = 0;

    public WriteThread(String threadName, Data data, String filler)
    {
        super(threadName);
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run()
    {
        try{
            while (true) {
                char c = nextChar();
                data.write(c);
                System.out.println(getName() + " writes " + c);
            }
        }catch (InterruptedException e) {}
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index = (index + 1) % filler.length();
        return c;
    }
}

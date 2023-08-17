package org.thread.chapter12.activeobject;

public class MakerClientThread
        extends Thread
{
    private final ActiveObject activeObject;
    private final char fillChar;
    public MakerClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override
    public void run()
    {
        try {
            int i = 0;
            while (true) {
                Result<String> result = activeObject.makeString(i++, fillChar);
                sleep(1000);
                String resultValue = result.getResultValue();
                System.out.println(Thread.currentThread().getName() + " : value = " + resultValue);
            }
        } catch (InterruptedException e){}
    }
}

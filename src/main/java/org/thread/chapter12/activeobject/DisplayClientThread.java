package org.thread.chapter12.activeobject;

/**
 * 调用 ActiveObject 的 displayString 线程：
 */
public class DisplayClientThread
        extends Thread
{
    private final ActiveObject activeObject;
    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run()
    {
        try {
            for (int i = 0; i < 10; i++) {
                String name = Thread.currentThread().getName() + "i";
                activeObject.displayString(name);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){}
    }
}

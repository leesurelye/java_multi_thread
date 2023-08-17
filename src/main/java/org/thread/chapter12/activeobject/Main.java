package org.thread.chapter12.activeobject;

public class Main
{
    public static void main(String[] args)
    {
        ActiveObject activeObject = ActiveObjectiveFactory.createActiveObject();
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bob", activeObject).start();
        new DisplayClientThread("Chris", activeObject).start();
    }
}

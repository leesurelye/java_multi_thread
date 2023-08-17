package org.thread.chapter12.activeobject;

import java.util.Arrays;

public class Servant implements ActiveObject
{

    @Override
    public Result<String> makeString(int count, char fillChar)
    {
        char[] buffer = new char[count];
        Arrays.fill(buffer, fillChar);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        // new
        return new RealResult<>(new String(buffer));
    }

    @Override
    public void displayString(String str)
    {
        System.out.println("displayString : " + str);
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){}
    }
}

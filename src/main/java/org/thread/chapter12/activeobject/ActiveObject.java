package org.thread.chapter12.activeobject;

public interface ActiveObject
{
    Result<String> makeString(int count, char fillChar);
    void displayString(String str);
}

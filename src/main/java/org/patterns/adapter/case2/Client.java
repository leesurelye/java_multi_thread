package org.patterns.adapter.case2;

public class Client
{
    public static void main(String[] args)
    {
        Target target = new Adapter();
        target.Request();
    }
}

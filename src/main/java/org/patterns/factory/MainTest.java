package org.patterns.factory;

public class MainTest
{
    public static void main(String[] args)
    {
        IPayFactory factory = new AliPayFactory();
        factory.create().pay("1234", 100.0);
    }
}

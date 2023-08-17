package org.patterns.adapter.case2;

public class Adapter extends Target
{
    private Adaptee adaptee;

    public Adapter()
    {
        this.adaptee = new Adaptee();
    }

    @Override
    public void Request()
    {
        adaptee.advanceRequest();
    }
}

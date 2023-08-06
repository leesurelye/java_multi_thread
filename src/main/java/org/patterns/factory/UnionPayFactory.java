package org.patterns.factory;

public class UnionPayFactory implements IPayFactory
{

    @Override
    public IPay create()
    {
        return new UnionPay();
    }
}

package org.patterns.factory;

public class AliPayFactory implements IPayFactory
{
    @Override
    public IPay create()
    {
        return new AliPay();
    }
}

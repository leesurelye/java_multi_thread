package org.patterns.factory;

public class WeChatPayFactory implements IPayFactory
{

    @Override
    public IPay create()
    {
        return new WeChatPay();
    }
}

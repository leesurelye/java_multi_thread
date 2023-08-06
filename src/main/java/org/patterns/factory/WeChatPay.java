package org.patterns.factory;

public class WeChatPay implements IPay
{

    @Override
    public String getName()
    {
        return "微信支付";
    }

    @Override
    public Double queryBalance(String uid)
    {
        return 200.0;
    }
}

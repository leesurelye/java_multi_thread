package org.patterns.factory;

public class AliPay implements IPay
{

    @Override
    public String getName()
    {
        return "支付宝";
    }

    @Override
    public Double queryBalance(String uid)
    {
        return 900.0;
    }
}

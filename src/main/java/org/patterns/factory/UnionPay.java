package org.patterns.factory;

public class UnionPay implements IPay
{

    @Override
    public String getName()
    {
        return "银行卡";
    }

    @Override
    public Double queryBalance(String uid)
    {
        return 10000.0;
    }
}

package org.patterns.factory;

public interface IPay
{
    String getName();
    Double queryBalance(String uid);
    default void pay(String uid, Double price) {
        Double currentAmount = queryBalance(uid);
        if (currentAmount < price) {
            System.out.println(getName() + "余额不足");
        } else {
            System.out.println(getName() + "支付成功");
        }
    }
}

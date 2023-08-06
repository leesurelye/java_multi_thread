package org.patterns.factory;

import java.lang.reflect.InvocationTargetException;

/**
 * 工厂模式
 */
public class PayFactory
{
    public static IPay create(Class<? extends  IPay> clazz) {
//        switch (payMethod) {
//            case "WeChatPay":  return new WeChatPay();
//            case "UnionPay":   return new UnionPay();
//            default: return new AliPay();
//        }
        //反射优化
        try {
            return clazz.getDeclaredConstructor().newInstance();
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        IPay iPay = PayFactory.create(AliPay.class);
        iPay.pay("1234", 100.0);
    }
}

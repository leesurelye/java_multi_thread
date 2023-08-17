package org.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Example
{
    public static void main(String[] args)
    {
        try {
            MyClass clazz = new MyClass();
            Class<? extends MyClass> aClass = clazz.getClass();
            Method method = aClass.getMethod("add", int.class, int.class);
            Object result = method.invoke(clazz, 1, 2);
            System.out.println(result);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    static class MyClass {
        public int add(int a, int b)
        {
            return a + b;
        }
    }
}
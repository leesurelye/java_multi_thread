package org.thread.chapter2;

/**
 * Immutable 类： 需要将字段设置为final, 同时不能设置setter方法，
 * 但是这样并不能保证Immutable, 因为即使字段的值不会发生变化，但是
 * 字段引用的实例有可能会发生改变。
 * <br>
 * 如果：Person中有一个字段是 Address类，那么外面的其他方法可能会修改Address类内部的字段。
 */
final class Person { // 1. 类的声明设置为final
    private final String name; // 2. 类的属性设置为final,并且引用类型的类也是immutable

    private final String address;

    public Person(String name, String address) // 在构造函数中初始化属性
    {
        this.name = name;
        this.address = address;
    }
    // 3. 不允许设置setter函数
    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class PrintPersonThread extends Thread {
    private Person person;

    public PrintPersonThread(Person person)
    {
        this.person = person;
    }

    @Override
    public void run()
    {
        try{
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":" + person);
                Thread.sleep(2000);
            }
        }catch (InterruptedException e) {}
    }
}

/**
 * Immutable ：不需要使用sync就能确保线程安全<br>
 * - String <br>
 * - BigInteger <br>
 * - BigDecimal <br>
 * - Pattern <br>
 * - 基本类型的包装类，Integer, Short <br>
 * - awt.Color <br>
 * <br>
 * <br>
 *
 * Mutable :
 * - awt.Point <br>
 *
 * Immutable 模式中，实例的状态不会发生改变，所以多个线程可以共享一个实例。
 */
public class ImmutablePersonExample
{
    public static void main(String[] args)
    {
        Person alice = new Person("alice", "alice_address");
        // 启动3个线程打印这个对象
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
    }
}

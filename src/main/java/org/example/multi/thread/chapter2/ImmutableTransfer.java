package org.example.multi.thread.chapter2;

import com.sun.istack.internal.NotNull;

class MutablePerson{
    private String name;

    private String address;

    public MutablePerson(String name, String address)
    {
        this.name = name;
        this.address = address;
    }

    public MutablePerson(ImmutablePerson person){
        this.name = person.getName();
        this.address = person.getAddress();
    }
    public synchronized void setPerson(String name, String address){
        this.name = name;
        this.address = address;
    }

    public synchronized ImmutablePerson getImmutablePerson(){
        return new ImmutablePerson(this);
    }
    // TODO 读数据的时候不能被其他线程修改内容
    public String getName(){
        return name;
    }
    //  TODO 读数据的时候不能被其他线程修改内容
    public String getAddress(){
        return address;
    }

    @Override
    public synchronized String toString()
    {
        return "MutablePerson{" + name + "," + address + '}';
    }
}

final class ImmutablePerson {
    private final String name;

    private final String address;

    public ImmutablePerson(String name, String address)
    {
        this.name = name;
        this.address = address;
    }
    public ImmutablePerson(@NotNull MutablePerson person){
        /**
         * 这两个调用必须放在临界区中， 因为在getName()之后，其他线程可能会使用MutablePerson的setPerson
         * 来修改address字段
         */
        synchronized (person){
            this.name = person.getName();
            this.address = person.getAddress();
        }
    }

    public MutablePerson getMutablePerson(){
        return new MutablePerson(this);
    }

    // 因为是Immutable class, 不需要添加 sync
    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    @Override
    public String toString()
    {
        return "ImmutablePerson{" + name + ", " + address + '}';
    }
}

class CrackerThread extends Thread{
    private final MutablePerson mutable;

    public CrackerThread(MutablePerson mutablePerson)
    {
        this.mutable = mutablePerson;
    }

    @Override
    public void run()
    {
        while (true) {
            ImmutablePerson immutable = new ImmutablePerson(mutable);
            if(!immutable.getName().equals(immutable.getAddress())){
                System.out.println(currentThread().getName() + "*** BROKEN ***" + immutable);
            }
        }
    }
}

/**
 * Immutable Person ----> Mutable Person
 * <br>
 * Mutable Person <------Immutable Person
 */
public class ImmutableTransfer
{
    public static void main(String[] args)
    {
        MutablePerson mutablePerson = new MutablePerson("start", "start");
        new CrackerThread(mutablePerson).start();
        new CrackerThread(mutablePerson).start();
        new CrackerThread(mutablePerson).start();
        for(int i = 0; true; i++) {
            mutablePerson.setPerson("" + i, ""+i);
        }
    }
}

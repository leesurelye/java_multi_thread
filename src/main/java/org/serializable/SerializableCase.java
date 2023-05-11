package org.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

class StringList implements Serializable{
    private transient int size = 0;

    private transient Entry head = null;
    // No longer serializable
    private static class Entry{
        String data;
        Entry next;
        Entry pre;
    }

    public void add(String s) {
        // 向双向列表中添加元素
    }
    // 序列化调用的方法

    /**
     * Serialize this <tt>StringList</tt> instance.
     *
     * @serialData The size of the list (the number of strings
     * it contains) is emitted (<tt>int</tt>), followed by all of
     * its elements (each a <tt>String</tt>), in the proper
     * sequence.
     */
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.writeInt(size);
        for(Entry e = head; e != null; e = e.next){
            // 只是写数据，而不是整个对象
            out.writeObject(e.data);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        size = in.readInt();
        // read in all elements and insert them in list
        for(int i = 0; i < size; i ++) {
            add((String)in.readObject());
        }
    }
}

/**
 * Serializable case 1: Only implement Serializable, 不指定versionUIDs,系统自动生成一个
 */
class SerializableCase1 implements Serializable {
    private String name;

    private transient int id;

    private Date createDate;

    private Date modifyDate;

    public SerializableCase1(String name, int id, Date createDate)
    {
        this.name = name;
        this.id = id;
        this.createDate = createDate;
    }

    @Override
    public String toString()
    {
        return "SerializableCase1{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", createDate=" + createDate +
                '}';
    }
}

class SerializableCase2 implements Serializable {

    private void writeObject(ObjectOutputStream out) throws IOException {
        // 采用
        out.defaultWriteObject();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}

/**
 * Serializable case 3.
 */
class SerializableCase3 implements Externalizable{
    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {

    }
}

public class SerializableCase
{
    public static void main(String[] args)
    {
        // test for case 1
//        SerializableCase1 case1 = new SerializableCase1("lee",1, new Date());
//        serialized(case1);
        Object obj = deserialized();
        System.out.println(obj);
    }
    private static Object deserialized() {
        Object obj = null;
        try {
            // 对象输入流
            ObjectInputStream in =
                    new ObjectInputStream(Files.newInputStream(Paths.get("./serializable.obj")));
            obj = in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return obj;
    }
    private static void serialized(Object obj){
        try {
            // 创建一个对象输出流
            ObjectOutputStream out =
                    new ObjectOutputStream(Files.newOutputStream(Paths.get("./serializable.obj")));
            out.writeObject(obj);
            System.out.println("Serialized success.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

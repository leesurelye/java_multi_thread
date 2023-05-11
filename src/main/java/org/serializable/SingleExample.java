package org.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

class Elvis implements Serializable
{
    private static final long serialVersionUID = 2345612345L;
    private static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public static Elvis getInstance(){
        return INSTANCE;
    }

    private Object readResolve() throws ObjectStreamException{
        return INSTANCE;
    }
}

/**
 * 对单例模式的对象进行序列化
 */
public class SingleExample
{
    public static void main(String[] args)
    {
        Elvis elvis = Elvis.getInstance();
        // org.serializable.Elvis@7ea987ac
        System.out.println(elvis);
        try{
            // serializable
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(elvis);
            // deserializable
            ObjectInputStream in =
                    new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            Object object = in.readObject();
            // org.serializable.Elvis@7ea987ac
            System.out.println(object);
            // true
            System.out.println(object == elvis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

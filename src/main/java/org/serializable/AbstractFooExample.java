package org.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

abstract class AbstractFoo {
    protected int x, y; // The state
    private boolean init = false;
    public AbstractFoo(int x, int y) { initialize(x, y); }
    /**
     * This constructor and the following method allow subclass's
     * readObject method to initialize our internal state.
     */
    public AbstractFoo() { }
    protected final void initialize(int x, int y) {
        if (init)
            throw new IllegalStateException("Already initialized");
        this.x = x;
        this.y = y;
        init = true;
    }
    /**
     * These methods provide access to internal state so it can
     * be manually serialized by subclass's writeObject method.
     */
    protected final int getX() { return x; }
    protected final int getY() { return y; }
}

class Foo extends AbstractFoo implements Serializable{

    private static final long serialVersionUID = 964458008762323231L;
    public Foo(int x, int y)
    {
        super(x, y);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // 手动反序列化并初始化值
        int x = in.readInt();
        int y = in.readInt();
        initialize(x, y);
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        // 手动序列化父类
        out.writeInt(getX());
        out.writeInt(getY());
    }
}
public class AbstractFooExample
{
    public static void main(String[] args)
    {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // serializable
            ObjectOutputStream os =
                    new ObjectOutputStream(bos);
            os.writeObject(new Foo(1, 2));
            // deserializable
            ObjectInputStream in =
                    new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            Object object = in.readObject();
            System.out.println(object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

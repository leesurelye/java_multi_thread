package org.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Immutable 类
 */
final class Period implements Serializable
{
    private final static long serialVersionUID = 1234567890L;
    private Date start;

    private Date end;

    public Period(Date start, Date end)
    {
        // 防止外部的引用修改实例Date内部的字段，因为Date是Mutable类
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        // 做逻辑判断
        if (this.start.compareTo(this.end) > 0) {
            throw new IllegalArgumentException(start + " > " + end);
        }
    }

    public Date start() {
        // 若因为返回对象的实例，外部的引用可以通过引用修改Date的内容，就破坏类Period的Immutable性质
        return (Date)start.clone();
    }

    public Date end() {
        return (Date)end.clone();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        // 采用
        out.defaultWriteObject();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        //
        start = new Date(start.getTime());
        end = new Date(end.getTime());
        // check
        if (start.compareTo(end) > 0) {
            throw new InvalidObjectException(start + " > " + end);
        }
    }

    private Object readResolve() throws ObjectStreamException{
        return new Period(start, end);
    }

    @Override
    public String toString()
    {
        return "Period{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}


class MutablePeriod{
    public final Period period;

    public final Date start;

    public final Date end;

    public MutablePeriod()
    {
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            // serializable a valid Period instance
            out.writeObject(new Period(new Date(), new Date()));
            byte[] ref = { 0x71, 0, 0x7e, 0, 5 };
            bos.write(ref); //the start field
            ref[4] = 4;
            bos.write(ref); // the end field

            //deserializable Period
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            this.period = (Period)in.readObject();
            this.start = (Date) in.readObject();
            this.end = (Date) in.readObject();
        }catch (Exception e){
            throw new RuntimeException(e.toString());
        }
    }
}
public class PeriodExample
{
    //Byte stream could not have come from real Period instance
    public static void main(String[] args)
    {
        try{
            MutablePeriod mutablePeriod = new MutablePeriod();
            Period period = mutablePeriod.period;

            Date end = mutablePeriod.end;
            end.setYear(78);
            System.out.println(period);
            // 人为构建的二进制文件
//            InputStream inputStream = Files.newInputStream(Paths.get("./serializable.bin"));
//            ObjectInputStream stream = new ObjectInputStream(inputStream);
//            Period object = (Period)stream.readObject();
//            System.out.println(object);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    private static Object deserialize(byte[] sf){
        try{
            InputStream byteArrayInputStream = new ByteArrayInputStream(sf);
            ObjectInputStream ios = new ObjectInputStream(byteArrayInputStream);
            return ios.readObject();
        }catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    private static void serialized(Object obj){
        try {
            // 创建一个对象输出流
            ObjectOutputStream out =
                    new ObjectOutputStream(Files.newOutputStream(Paths.get("./serializable.bin")));
            out.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

package org.thread.chapter2;

class UserInfo{
    // ERROR： StringBuffer为Mutable类
    private final StringBuffer info;

    public UserInfo(String name, String address) {
        this.info = new StringBuffer("name: " + name +" , address: " + address);
    }
    // ERROR 将引用作为返回值
    public StringBuffer getInfo()
    {
        return info;
    }

    @Override
    public String toString()
    {
        return "UserInfo{" +
                "info=" + info +
                '}';
    }
}

// 测试引用类型并不能保证这个类为Immutable
public class UserInfoExample
{
    public static void main(String[] args)
    {
        UserInfo user = new UserInfo("Alice", "Alibaba");
        // UserInfo{info=name: Alice , address: Alibaba}
        System.out.println(user);
        // 修改状态
        StringBuffer info = user.getInfo();
        //UserInfo{info=Bobby Alice , address: Alibaba}
        info.replace(0, 5, "Bobby");
        // 再次查看
        System.out.println(user);
    }
}

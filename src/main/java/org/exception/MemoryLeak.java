package org.exception;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeak
{
    // 长期的生命周期对象，静态类型的root节点
    static List<Object> ROOT = new ArrayList<>();
    public static void main(String[] args)
    {
        int i = 0;
        for(;;)
        {
            // 不断创建新对象，使用后不手动将其从容器中移除
            Object obj = new Object();
            ROOT.add(obj);
            obj = i;
            i ++;
        }
    }
}

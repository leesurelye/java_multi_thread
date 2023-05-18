package org.thread.chapter4;

import org.thread.chapter4.obj.Data;
import org.thread.chapter4.thread.ChangeThread;
import org.thread.chapter4.thread.SaveThread;

/**
 * <h2>自动保存文档程序</h2>
 * <p>balking model</p>
 * 定期将当前数据写入文件中，当数据内容被写入时，会完全覆盖上次写入的内容，只有最新的内容才会被保存。
 * 如果写入数据的内容相同，则不执行写操作，直接返回.
 */
public class BaseCase
{
    public static void main(String[] args)
    {
        Data data = new Data("data.txt", "");
        new ChangeThread(data, "Changer").start();
        new SaveThread(data, "Saver").start();
    }
}

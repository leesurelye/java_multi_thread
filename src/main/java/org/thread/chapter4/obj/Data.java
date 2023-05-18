package org.thread.chapter4.obj;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 可以修改并保存数据的类
 */
public class Data
{
    private final String fileName;

    private String content;

    // 修改后的内容，若没有保存，则为true
    private boolean changed;

    public Data(String fileName, String content)
    {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    // 修改数据内容
    public synchronized void change(String newContent)
    {
        this.content = newContent;
        this.changed = true;
    }

    // 若修改过内容，则保存
    public synchronized void save() throws IOException
    {
        if(!changed) return ;
        doSave();
        changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls doSave(), content = " + content);
        Writer writer = new FileWriter(fileName);
        writer.write(content);
        writer.close();
    }
}

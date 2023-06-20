package org.thread.chapter7.obj;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public class Host1 implements Host
{
    private final Helper helper = new Helper();
    private final Executor executor;

    // 执行者
    public Host1(Executor executor)
    {
        this.executor = executor;
    }

    @Override
    public void request(final int count, final char c)
    {
        System.out.println("\t request (" + count + ", " + c + ") BEGIN");
        // 创建新的线程
        executor.execute(() -> helper.handle(count, c));
        System.out.println("\t request (" + count + ", " + c + ") END");
    }
}

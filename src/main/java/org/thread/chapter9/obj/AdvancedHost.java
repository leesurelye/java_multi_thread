package org.thread.chapter9.obj;

public class AdvancedHost
{
    public AdvanceFutureDate request(final int count, final char c) {
        System.out.println("\t request(" + count + ", " + c + " ) BEGIN");
        // 1. 创建FutureData实例
        AdvanceFutureDate futureData = new AdvanceFutureDate(() -> new RealData(count, c));
        // 2. 启动一个新线程
        new Thread(futureData).start();
        System.out.println("\t request(" + count + ", " + c + " ) END");
        // 3. 返回实例
        return futureData;
    }
}

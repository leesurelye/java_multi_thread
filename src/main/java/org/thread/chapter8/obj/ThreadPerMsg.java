package org.thread.chapter8.obj;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 类似于Channel, Channel中有五个 WorkThread， 但是在这里是每一个request新建一个线程
 */
public class ThreadPerMsg
{
    private final ThreadFactory threadFactory = Executors.defaultThreadFactory();

    /**
     * 每来一个request，创建一个线程
     * @param request request
     */
    public void handle(Request request) {
        System.out.println("request() " + request + " BEGIN");
        this.threadFactory.newThread(request).start();
        System.out.println("request() " + request + " END");
    }
}

package org.thread.chapter3;

import org.thread.chapter3.obj.RequestQueue;
import org.thread.chapter3.thread.TalkThread;

/**
 * 如果执行现在的操作会造成问题，就让执行的线程等待
 *<br>
 * 案例中参与的类：<br>
 * - request： 请求实体
 * - 守护对象：requestQueue: 存放请求的队列
 * - ClientThread: 向请求队列中放数据
 * - ServerThread: 向请求队列中拿数据，如果没有，则等待
 * Main
 */
public class GuardedSuspension
{
    public static void main(String[] args)
    {
        RequestQueue requestQueue1 = new RequestQueue();
        RequestQueue requestQueue2 = new RequestQueue();
//        requestQueue1.putRequest(new Request("Hello"));
        new TalkThread(requestQueue1, requestQueue2, "Alice").start();
        new TalkThread(requestQueue2, requestQueue1,"Bob").start();
    }
}

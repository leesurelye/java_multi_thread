package org.thread.chapter3.thread;

import org.thread.chapter3.obj.Request;
import org.thread.chapter3.obj.RequestQueue;

import java.util.Random;

/**
 * 守护对象<br>
 * <p>
 * 这个对象中有两种状态，即有元素和无元素
 * 线程需要根据对象的两种状态来决定执行还是等待
 * <br>
 * <br>
 * 守护对象中包被守护的方法(guardedMethod) 和改变状态的方法(stateChangingMethod).<br>
 * 1. 当线程执行guardedMethod时，需要判断守护条件，通过while和wait()来实现<br>
 * 2. stateChangingMethod则通过notify/notifyAll实现
 */
public class ClientThread extends Thread
{
    private final RequestQueue queue;
    private final Random random = new Random(123);

    public ClientThread(RequestQueue queue, String name)
    {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try {
            int i = 0;
            while (true) {
                // 每隔1秒发送一次请求
                Thread.sleep(random.nextInt(1000));
                Request request = new Request(i++ + " ");
                System.out.println(currentThread().getName() + " puts request : " + request);
                this.queue.putRequest(request);
            }
        } catch (InterruptedException e) {
        }
//        try {
//            while(true){
//                Request request = new Request(i++ + " ");
//                System.out.println(currentThread().getName() + " puts request : " + request);
//                this.queue.putRequest(request);
//                Thread.sleep(1000);
//            }
//        } catch (InterruptedException e) {
//        }
    }
}

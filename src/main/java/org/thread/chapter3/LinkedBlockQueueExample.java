package org.thread.chapter3;

import org.thread.chapter3.obj.BlockingRequestQueue;
import org.thread.chapter3.obj.Request;





/**
 * LinkedBlockQueue 是和RequestQueue类似的对了，其中的take和put方法已经做类互斥处理，因此不需要
 * 在取数据的时候添加sync
 */
public class LinkedBlockQueueExample
{
    public static void main(String[] args)
    {
        BlockingRequestQueue queue = new BlockingRequestQueue();
        // Client Thread, ()中的是Runnable的构造函数的参数
        new Thread(() -> {
            int i = 0;
            try{
                while (true){
                    Request request = new Request(i++ + " ");
                    queue.putRequest(request);
                    System.out.println("Client puts request: " + request);
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){}
        }).start();

        // Server Thread
        new Thread(() -> {
            try{
                while (true){
                    Request request = queue.getRequest();
                    System.out.println("Server gets request: " + request);
                    Thread.sleep(2000);
                }
            }catch (InterruptedException e){}
        }).start();
    }
}

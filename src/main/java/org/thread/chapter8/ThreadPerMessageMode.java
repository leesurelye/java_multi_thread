package org.thread.chapter8;

import org.thread.chapter8.obj.ThreadPerMsg;
import org.thread.chapter8.thread.ClientThread;
import org.thread.chapter8.thread.ThreadPerMsgClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 使用Thread per message来模拟这一过程
 */
public class ThreadPerMessageMode
{
    public static void main(String[] args)
    {
        ThreadPerMsg perMsg = new ThreadPerMsg();
        System.out.println("Main BEGIN");
        new ThreadPerMsgClient("Alice", perMsg).start();
        new ThreadPerMsgClient("Bob", perMsg).start();
        new ThreadPerMsgClient("Chris", perMsg).start();
        System.out.println("Main END");
    }
}

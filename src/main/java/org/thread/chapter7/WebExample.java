package org.thread.chapter7;

import org.thread.chapter7.obj.SocketServer;

import java.io.IOException;

public class WebExample
{
    public static void main(String[] args)
    {
        try {
            new SocketServer(9999).execute();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

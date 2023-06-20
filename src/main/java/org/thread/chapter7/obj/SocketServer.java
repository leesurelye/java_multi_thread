package org.thread.chapter7.obj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
    private final int port;
    public SocketServer(int port)
    {
        this.port = port;
    }
    public void execute() throws IOException{
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listening on " + serverSocket);
        try{
           while (true){
               System.out.println("Accepting...");
               Socket clientSocket = serverSocket.accept();
               System.out.println("Connected to " + clientSocket);
               Service.service(clientSocket);
           }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}

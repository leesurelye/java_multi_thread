package org.thread.chapter7.obj;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Service
{
    private static final Executor executor = Executors.newFixedThreadPool(5);
    private static final DateFormat dateFormat = new SimpleDateFormat("mm:ss");
    // Thread-Per-Message
    public static void service()
    {
        System.out.println("service doService() begin. " + dateFormat.format(System.currentTimeMillis()));
        executor.execute(Service::doService);
        System.out.println("service doService() begin. " + dateFormat.format(System.currentTimeMillis()));
    }

    public static void service(Socket clientSocket)
    {
        executor.execute(() -> {
            try {
                Service.doWebService(clientSocket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static void doWebService(Socket clientSocket) throws IOException {
        System.out.println(Thread.currentThread().getName() + ": Service.service(" + clientSocket + ") ENGIN.");
        try {
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeBytes("HTTP/1.0 200 OK\r\n");
            out.writeBytes("Content-type: text/html\t\n");
            out.writeBytes("\r\t");
            out.writeBytes("<html><head><title>Countdown</title></head><body>");
            out.writeBytes("<h1>Countdown start!</h1>");
            for (int i=100; i >= 0; i--) {
                System.out.println(Thread.currentThread().getName() + ": Countdown i=" + i);
                out.flush();
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {}
            }
            out.writeBytes("</body></html>");
        } finally {
            clientSocket.close();
        }
        System.out.println(Thread.currentThread().getName() + ": Service.service(" + clientSocket + ") END.");
    }
    public static void doService()
    {
        System.out.println("service begin. " + dateFormat.format(System.currentTimeMillis()));
        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("service end. " + dateFormat.format(System.currentTimeMillis()));
    }
}

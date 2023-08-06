package org.thread.chapter11.case2;

public class Log
{
    // all thread create TSLog store in thread local object.
    private static final ThreadLocal<TSLog> tsLogCollections = new ThreadLocal<>();
    // writer log
    public static void println(String s){
        getTSLog().println(s);
    }
    // close log
    public static void close(){
        getTSLog().close();
    }
    // get thread specific log object
    private static TSLog getTSLog() {
        // get thread specific TSLog instance.
        TSLog tsLog = tsLogCollections.get();
        // if this is the first time to get log, Create log object and get it.
        if (tsLog == null) {
            tsLog = new TSLog(Thread.currentThread().getName() + "-log.txt");
            tsLogCollections.set(tsLog);
//            startWatcher(tsLog);
        }
        return tsLog;
    }

    private static void startWatcher(TSLog log) {
        Thread thread = Thread.currentThread();
        new Thread(() -> {
            System.out.println("startWatcher begin for " + thread.getName());
            try {
               thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                log.close();
            }
            System.out.println("startWatcher end for " + thread.getName());
        }).start();
    }
}

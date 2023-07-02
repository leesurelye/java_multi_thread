package org.thread.chapter9.obj;

public class Host
{
    /**
     * request 会立即返回FutureData实例，RealData的实例由其他线程创建，
     * @param count
     * @param c
     * @return
     */
    public Data request(final int count, final char c) {
        System.out.println("\t request(" + count + ", " + c + " ) BEGIN");
        final FutureData futureData = new FutureData();
        // 使用来Thread-Per-Message模式启动新的线程
        new Thread( ()-> {
            try {
                RealData realData = new RealData(count, c);
                futureData.setRealData(realData);
            }catch (Exception e) {
                futureData.setExecution(e);
            }
        }).start();
        System.out.println("\t request(" + count + ", " + c + " ) END");
        return futureData;
    }
}

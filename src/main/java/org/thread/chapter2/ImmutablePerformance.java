package org.thread.chapter2;

class NotSync
{
    private final String name = "NotSync";

    @Override
    public String toString()
    {
        return "NotSync{" + name + '\'' +
                '}';
    }
}

class Sync
{
    private final String name = "Sync";

    @Override
    public synchronized String toString()
    {
        return "Sync{" + name + '\'' +
                '}';
    }
}

/**
 * 测试Immutable能提升多少性能
 */
public class ImmutablePerformance
{
    private static void trial(String msg, long count, Object obj)
    {
        System.out.println(msg + ": BEGIN");
        long start_time = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            obj.toString();
        }
        System.out.println(msg + ": END");
        System.out.println("Elapsed time : " + (System.currentTimeMillis() - start_time) + "m_sec.");
    }

    private static final long CALL_COUNT = 1000000000L;

    public static void main(String[] args)
    {
        // 350m_sec.
        trial("NotSync", CALL_COUNT, new NotSync());
        // 13432m_sec.
        trial("Sync", CALL_COUNT, new Sync());
        // 差这么多倍？？？
    }
}

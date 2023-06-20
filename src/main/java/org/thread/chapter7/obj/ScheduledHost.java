package org.thread.chapter7.obj;

import com.facebook.presto.jdbc.internal.jackson.databind.util.StdDateFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledHost implements Host
{
    private final Helper helper = new Helper();
    private final DateFormat dateFormat = new SimpleDateFormat("mm:ss");
    private final ScheduledExecutorService scheduledExecutorService;

    public ScheduledHost(ScheduledExecutorService scheduledExecutorService)
    {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public void request(final int count, final char c) {
        System.out.println("\t request (" + count + ", " + c + ") BEGIN. " + dateFormat.format(new Date(System.currentTimeMillis())));
        // 请求到达三秒后开始执行
        scheduledExecutorService
                .schedule(()->helper.handle(count, c),
                        5L,
                        TimeUnit.SECONDS);
        System.out.println("\t request (" + count + ", " + c + ") END. " + dateFormat.format(new Date(System.currentTimeMillis())));
    }
}

package org.thread.chapter9.obj;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class AdvanceFutureDate extends FutureTask<RealData> implements Data
{
    public AdvanceFutureDate(Callable<RealData> callable) {
        super(callable);
    }

    @Override
    public String getContent() throws ExecutionException
    {
        String string = null;
        try {
            string = get().getContent();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return string;
    }
}

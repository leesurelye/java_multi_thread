package org.thread.chapter9.obj;

import java.util.concurrent.ExecutionException;

public interface Data
{
    String getContent() throws ExecutionException;
}

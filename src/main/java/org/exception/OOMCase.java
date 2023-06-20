package org.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulate OOM code
 */
public class OOMCase
{
    public static class OOMObject{}
    public static void main(String[] args)
    {
        List<OOMObject> list = new ArrayList<>();
        while (true)
        {
            list.add(new OOMObject());
        }
    }
}

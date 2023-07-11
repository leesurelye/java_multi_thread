package org.mock.obj;

import java.util.HashMap;
import java.util.Map;

public class TestA
{
    public String a = "abc";

    public Map<String, String> test()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        return map;
    }
}

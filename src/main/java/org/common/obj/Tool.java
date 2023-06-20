package org.common.obj;

public class Tool
{
    private final String name;
    public Tool(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "Tool{" +
                "name='" + name + '\'' +
                '}';
    }
}

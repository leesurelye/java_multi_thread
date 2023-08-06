package org.constant;

public enum SeasonEnum
{
    SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);
    private int value;

    SeasonEnum(int value)
    {
        this.value = value;
    }
    public int getSeq()
    {
        return value;
    }
}

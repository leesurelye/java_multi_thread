package org.common.obj;

public class AnyType
{
    String typeName;

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof AnyType)) {
            return false;
        }
        AnyType other = (AnyType) obj;

        return super.equals(other) || other.typeName.equals("any") || this.typeName.equals("any");
    }
}

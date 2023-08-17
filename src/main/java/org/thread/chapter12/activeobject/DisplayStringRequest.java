package org.thread.chapter12.activeobject;

public class DisplayStringRequest extends MethodRequest<Object>
{
    private final String str;

    public DisplayStringRequest(Servant servant, String str)
    {
        super(servant, null);
        this.str = str;
    }

    @Override
    public void execute()
    {
        this.servant.displayString(str);
    }
}

package org.apo;

public class SubjectImpl implements Subject
{

    @Override
    public void login()
    {
        System.out.println("login....");
    }

    @Override
    public void download()
    {
        System.out.println("download....");
    }
}

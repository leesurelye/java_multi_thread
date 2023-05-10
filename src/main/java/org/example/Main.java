package org.example;

import java.io.Serializable;

class Name implements Serializable {
    private String lastName;

    private String firstName;

    private char middleInitial;
}

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
    }
}
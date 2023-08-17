package org.patterns.adapter.case1;

public class MainTest
{
    public static void main(String[] args)
    {
        // 低级接口调用
        BasicPlayer player =  new BasicPlayer();
        player.play();
    }
}

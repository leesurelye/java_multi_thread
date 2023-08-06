package org.patterns.adapter;

public class MP4Player implements AdvanceMediaPlayer
{
    @Override
    public void advancePlay()
    {
        System.out.println("MP4Player: advancePlay()");
    }
}

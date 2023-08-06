package org.patterns.adapter;

public class VideoPlayer implements AdvanceMediaPlayer
{
    @Override
    public void advancePlay()
    {
        System.out.println("VideoPlayer: advancePlay()");
    }
}

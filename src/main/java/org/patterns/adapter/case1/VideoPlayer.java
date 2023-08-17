package org.patterns.adapter.case1;

public class VideoPlayer implements AdvanceMediaPlayer
{
    @Override
    public void advancePlay()
    {
        System.out.println("VideoPlayer: advancePlay()");
    }
}

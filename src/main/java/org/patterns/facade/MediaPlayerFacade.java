package org.patterns.facade;

public class MediaPlayerFacade
{
    private Audio audio;
    private Video video;
    private UserInterface ui;
    public MediaPlayerFacade()
    {
        this.audio = new Audio();
        this.video = new Video();
        this.ui = new UserInterface();
    }
    public void play()
    {
        this.audio.turnOn();
        this.video.turnOn();
        this.ui.turnOn();
    }
    public void stop()
    {
        this.audio.turnOff();
        this.video.turnOff();
        this.ui.turnOff();
    }
}

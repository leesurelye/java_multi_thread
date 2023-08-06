package org.patterns.adapter;

public class BasicPlayer implements MediaPlayer
{

    @Override
    public void play()
    {
        PlayerAdapter adapter = new PlayerAdapter();
        adapter.adapter();
    }

    static class PlayerAdapter
    {
        private  AdvanceMediaPlayer mp4Player = new MP4Player();
        private AdvanceMediaPlayer videoPlayer = new VideoPlayer();

        public void adapter()
        {
            // 调用高级的接口
            mp4Player.advancePlay();
            videoPlayer.advancePlay();
        }
    }
}

package top.dreamcenter.hw.audio;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;

public class AudioCenter implements Runnable{
    private boolean fullAudio = true;

    private void basic(String src){
        try {
            if(fullAudio){
                AudioStream audioStream = new AudioStream(new FileInputStream(src));
                AudioPlayer audioPlayer = AudioPlayer.player;
                AudioPlayer.player.start(audioStream);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println(src + " Not Found");
        }
    }

    public void footStep(){
        basic("sources\\mp3\\footstep.wav");
    }

    @Override
    public void run() {
        basic("sources\\mp3\\bgm.wav");
    }
}

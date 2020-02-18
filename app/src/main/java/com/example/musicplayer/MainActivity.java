package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView playPauseIcon;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);
        playPauseIcon = findViewById(R.id.play);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b)
                    mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 500);
    }

    public void previousSong(View view) {
        mediaPlayer.seekTo(0);
        playPauseIcon.setImageResource(R.drawable.ic_play_24dp);
    }

    public void pauseSong(View view) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.ic_pause_24dp);
        } else {
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.ic_play_24dp);
        }
    }

    public void nextSong(View view) {
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        playPauseIcon.setImageResource(R.drawable.ic_play_24dp);
    }
}

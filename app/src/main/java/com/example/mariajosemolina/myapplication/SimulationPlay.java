package com.example.mariajosemolina.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.SeekBar;
import android.view.View;
import android.os.Handler;
import android.view.MotionEvent;
import android.content.Context;

public class SimulationPlay extends AppCompatActivity {

    Button playPauseButton, stopButton;
    SeekBar seekBar;
    TextView timerText, albumSongName;
    ImageView coverImageView;

    // For the fake play timer
    boolean isPaused = true;
    long startTime;
    long timePaused;
    long timeTotal;
    double TEST_TIMER_SONG = 10.0;

    // Handler and Runnable to manipulate a timer to mimic a song being played
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            // Get total time since the beginning
            timeTotal = System.currentTimeMillis() - startTime;
            // Adding paused time to the total to keep track of the total time
            double timeSeconds = (timeTotal+timePaused) / 1000.0;
            // Converting to integer
            int totalSeconds = (int)timeSeconds;

            // Getting the minutes and seconds for display
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            timerText.setText(String.format("%02d:%02d", minutes, seconds));

            // Timer step, to advance every 1 second.
            timerHandler.postDelayed(this, 500);

            // Getting the percentage, considering a fake lenght of 10 seconds per song
            int percentage = (int) ((timeSeconds/TEST_TIMER_SONG) * 100);
            seekBar.setProgress(percentage);

            // Once we go over the total percentage, we stop.
            if (percentage >= 100)
                stopTimer();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simulation_play);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Player");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle params = getIntent().getExtras();
        String songName = params.getString("songName");
        int idAlbum = params.getInt("idAlbum");


        playPauseButton = (Button) findViewById(R.id.playPauseButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        timerText = (TextView) findViewById(R.id.timerText);
        albumSongName = (TextView) findViewById(R.id.albumSongName);
        coverImageView = (ImageView) findViewById(R.id.coverImageView);

        // Starting the timer objects and variables
        seekBar.setMax(100);
        seekBar.setProgress(0);
        timerText.setText("00:00");
        timePaused = 0;
        timeTotal = 0;

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPaused = !isPaused;

                if (isPaused){
                    // When we click the Pause button, we add the pause-time to the total time
                    // we change the icon/label of the Button, and we remove the function
                    // that execute the timer from our Handler
                    timePaused += timeTotal;
                    playPauseButton.setBackgroundResource(R.drawable.play);
                    timerHandler.removeCallbacks(timerRunnable);
                }
                else {
                    // When we click the Play button, we initialize the timer
                    // we change the icon/label of the button to Pause, and we go to our timer
                    // with no delay
                    startTime = System.currentTimeMillis();
                    playPauseButton.setBackgroundResource(R.drawable.pause);
                    timerHandler.postDelayed(timerRunnable, 0);
                }
            }
        });

        // When clicking the stop button
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });

        // Disabling touch event on the SeekBar
        // TODO: Implementing this in the future...seems difficult.
        seekBar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        Context context = getApplicationContext();
        String imageName = Start.db.get(idAlbum-1).get(3);
        String albumName = Start.db.get(idAlbum-1).get(1);



        int id = context.getResources().getIdentifier("drawable/"+imageName,
                null, context.getPackageName());
        coverImageView.setImageResource(id);

        albumSongName.setText(albumName+" - "+songName);
    }

    // General method to stop the timer
    private void stopTimer() {
        timerHandler.removeCallbacks(timerRunnable);

        seekBar.setProgress(0);
        timerText.setText("00:00");
        playPauseButton.setBackgroundResource(R.drawable.play);

        timePaused = 0;
        timeTotal = 0;
        isPaused = true;
    }

}

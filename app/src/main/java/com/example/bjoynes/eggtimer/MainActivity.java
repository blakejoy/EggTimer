package com.example.bjoynes.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerTextView;
    Boolean counterIsActive = false;
    Button timerButton;
    CountDownTimer countDownTimer;

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondsString = Integer.toString(seconds);

        if (seconds == 0){
            secondsString = "00";
        }else if (seconds <= 9){
            secondsString = "0" + seconds;
        }


        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);
    }

    public void buttonPressed(View view){
        if (counterIsActive == false) {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            timerButton.setText("Stop");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                    Log.i("Finished", "The timer is finished!");
                    resetTimer();
                }
            }.start();
        }else{
            resetTimer();
        }
    }

    public void resetTimer(){
        timerTextView.setText("0:30");
        timerSeekBar.setEnabled(true);
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        timerButton.setText("Go");
        counterIsActive = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerLabel);
        timerButton = (Button) findViewById(R.id.timerButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

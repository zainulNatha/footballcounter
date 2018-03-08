package com.example.pasystemsltd.footballcounter;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    // Tracks the score for Team A
    int scoreTeamA = 0;

    // Tracks the score for Team B
    int scoreTeamB = 0;

    // Track the yellowCards for Team A
    int yellowCard_TeamA = 0;

    // Track the yellowCards for Team B
    int yellowCard_TeamB = 0;

    // Track the redCards for Team A
    int redCard_TeamA = 0;

    // Track the redCards for Team B
    int redCard_TeamB = 0;

    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimpSlifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * Increase the score for Team A by 1 point.
     */
    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }
    /**
     * Increase the score for Team B by 1 point.
     */
    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }
    /**
     * Increase the score for Team A yellow card by 1 point.
     */
    public void addOneYellowForTeamA(View v) {
        yellowCard_TeamA = yellowCard_TeamA + 1;
        displayYellowTeamA(yellowCard_TeamA);
    }
    /**
     * Increase the score for Team B yellow card by 1 point.
     */
    public void addOneYellowForTeamB(View v) {
        yellowCard_TeamB = yellowCard_TeamB + 1;
        displayYellowTeamB(yellowCard_TeamB);
    }
    /**
     * Increase the score for Team A red card by 1 point.
     */
    public void addOneRedForTeamA(View v) {
        redCard_TeamA = redCard_TeamA + 1;
        displayRedTeamA(redCard_TeamA);
    }
    /**
     * Increase the score for Team B red card by 1 point.
     */
    public void addOneRedForTeamB(View v) {
        redCard_TeamB = redCard_TeamB + 1;
        displayRedTeamB(redCard_TeamB);
    }
    /**
     * Resets the score for both teams back to 0.
     */
    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        yellowCard_TeamA=0;
        yellowCard_TeamB=0;
        redCard_TeamA=0;
        redCard_TeamB=0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayYellowTeamA(yellowCard_TeamA);
        displayYellowTeamB(yellowCard_TeamB);
        displayRedTeamA(redCard_TeamA);
        displayRedTeamB(redCard_TeamB);

    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the yellow cards for Team A.
     */
    public void displayYellowTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_yellow_card);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the yellow cards for Team B.
     */
    public void displayYellowTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_yellow_card);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the red cards for Team A.
     */
    public void displayRedTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_red_card);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the red cards for Team B.
     */
    public void displayRedTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_red_card);
        scoreView.setText(String.valueOf(score));
    }
}





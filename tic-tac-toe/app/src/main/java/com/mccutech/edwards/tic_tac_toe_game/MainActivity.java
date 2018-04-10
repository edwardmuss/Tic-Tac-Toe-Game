package com.mccutech.edwards.tic_tac_toe_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Edwards on 1/9/2018.
 */


public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, highScore;
    SharedPreferences sharedPrefs;
   private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  setVolumeControlStream(AudioManager.STREAM_MUSIC);
       // mp = MediaPlayer.create(this, R.raw.outstanding);

        sharedPrefs = getSharedPreferences("NAMES", MODE_PRIVATE);
        Two_player_names_entry.p1Name = sharedPrefs.getString("PLAYER1", Two_player_names_entry.p1Name);
        Two_player_names_entry.p2Name = sharedPrefs.getString("PLAYER2", Two_player_names_entry.p2Name);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        highScore = findViewById(R.id.highScores);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        intent = new Intent(MainActivity.this, One_player.class);
                        startActivity(intent);
                    }
                }
        );
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highScore.setVisibility(View.VISIBLE);
                        Intent intent;
                        intent = new Intent(MainActivity.this, Two_player_names_entry.class);
                        startActivity(intent);
                    }
                }
        );
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        intent = new Intent(MainActivity.this, about.class);
                        startActivity(intent);
                    }
                }
        );
        highScore.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, HighScore.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor edit = sharedPrefs.edit();
        edit.putString("PLAYER1", Two_player_names_entry.p1Name);
        edit.putString("PLAYER2", Two_player_names_entry.p2Name);
        edit.commit();
    }
}

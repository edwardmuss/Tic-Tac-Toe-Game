package com.mccutech.edwards.tic_tac_toe_game;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
/**
 * Created by Edwards on 3/9/2018.
 */


public class Two_players extends AppCompatActivity {

    public static Activity act_2players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        act_2players = this;
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_two_player);
        TextView txt=findViewById(R.id.plyerturn);
        txt.setText(Two_player_names_entry.p1Name+":"+""+"playX,"+""+ Two_player_names_entry.p2Name +":play O");
    }
    // Restart activity onClicking button reset
    public  void reset(View v){
        Intent intent;
        intent = new Intent(Two_players.this, Two_players.class);
        startActivity(intent);
    }
    public void back(View v){
      Intent  intent = new Intent(Two_players.this, MainActivity.class);
        startActivity(intent);
    }
}

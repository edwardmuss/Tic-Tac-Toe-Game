package com.mccutech.edwards.tic_tac_toe_game;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Edwards on 1/9/2018.
 */

public class One_player extends AppCompatActivity {

    public static Activity act_1e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        act_1e = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
    }
// Restart activity onClicking button reset
        public  void reset(View v){
            Intent intent;
            intent = new Intent(One_player.this, One_player.class);
            startActivity(intent);
    }
    public void back(View v){
        Intent  intent = new Intent(One_player.this, MainActivity.class);
        startActivity(intent);
    }
}

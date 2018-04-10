package com.mccutech.edwards.tic_tac_toe_game;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Edwards on 4/9/2018.
 */

public class HighScore extends AppCompatActivity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHandler DataBaH = new DatabaseHandler(HighScore.this);
                        DataBaH.deleteAll();
                        finish();
                    }
                }
        );
        TextView list1 = findViewById(R.id.list1);
        TextView list2 = findViewById(R.id.list2);
        DatabaseHandler DataBaH = new DatabaseHandler(this);
        List<ScoreBoard> res = DataBaH.getAllContacts();
        for (ScoreBoard sb : res) {
            i++;
            list1.setText(list1.getText() + "\n" + i + ". " + sb.get_name());
            list2.setText(list2.getText() + "\n" + sb.get_score());
        }
    }
}

package com.mccutech.edwards.tic_tac_toe_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;
/**
 * Created by Edwards on 4/9/2018.
 */


public class Two_player_names_entry extends AppCompatActivity {

    Button start2p;
    EditText p1, p2;
    static String p1Name = "Player 1";
    static String p2Name = "Player 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2player_names_entry);
        p1 = findViewById(R.id.p1name);
        p2 = findViewById(R.id.p2name);
        p1.setText(p1Name);
        p2.setText(p2Name);
        start2p = findViewById(R.id.start2p);
        start2p.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Two_player_names_entry.this, Two_players.class);
                        if ((!Objects.equals(p1.getText().toString(), "")) && (!Objects.equals(p2.getText().toString(), "")) && (!Objects.equals(p1.getText().toString(), p2.getText().toString())) && (p1.getText().toString().length() <= 10) && (p2.getText().toString().length() <= 10)) {
                            p1Name = p1.getText().toString().trim().toUpperCase();
                            p2Name = p2.getText().toString().trim().toUpperCase();
                            DatabaseHandler dbh = new DatabaseHandler(Two_player_names_entry.this);
                            String tmp = dbh.checkUser(Two_player_names_entry.p1Name);
                            if (tmp.equals("FOUND")) {
                                Toast.makeText(Two_player_names_entry.this,"found 1",Toast.LENGTH_SHORT).show();
                            } else {
                                ScoreBoard sb = new ScoreBoard(dbh.getPlayerCount(), Two_player_names_entry.p1Name, 0);
                                Toast.makeText(Two_player_names_entry.this,"adding  1",Toast.LENGTH_SHORT).show();
                                dbh.addScore(sb);
                            }

                            String tmp2 = dbh.checkUser(Two_player_names_entry.p2Name);
                            if (tmp2.equals("FOUND")) {
                                Toast.makeText(Two_player_names_entry.this,"found 2",Toast.LENGTH_SHORT).show();

                            } else {
                                ScoreBoard sb = new ScoreBoard(dbh.getPlayerCount(), Two_player_names_entry.p2Name, 0);
                                Toast.makeText(Two_player_names_entry.this,"adding 2",Toast.LENGTH_SHORT).show();
                                dbh.addScore(sb);
                            }
                            startActivity(intent);
                        } else if (Objects.equals(p1.getText().toString(), "")) {
                            p1.setError("Enter Player 1 Name");
                        } else if (Objects.equals(p2.getText().toString(), "")) {
                            p2.setError("Enter Player 2 Name");
                        } else if (Objects.equals(p1.getText().toString(), p2.getText().toString())) {
                            Toast.makeText(Two_player_names_entry.this, "Enter different names", Toast.LENGTH_SHORT).show();
                        } else if (p1.getText().toString().length() > 10) {
                            Toast.makeText(Two_player_names_entry.this, "Shorten player 1 name", Toast.LENGTH_SHORT).show();
                        } else if (p2.getText().toString().length() > 10) {
                            Toast.makeText(Two_player_names_entry.this, "Shorten player 2 name", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}

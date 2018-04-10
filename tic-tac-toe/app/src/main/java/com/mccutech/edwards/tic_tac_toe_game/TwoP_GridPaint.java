package com.mccutech.edwards.tic_tac_toe_game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Edwards on 1/9/2018.
 */

public class TwoP_GridPaint extends View { //you have to create a new java file and then insert the same file in the xml of the page in which you want the canvas
    Paint paint = new Paint();
    Paint paintx = new Paint();
    Paint painto = new Paint();
    Paint painto1 = new Paint();
    boolean oncewin = false;
    boolean oncedrawen = false;
    int[][] a = new int[3][3];
    float[][] midx = new float[3][3];
    float[][] midy = new float[3][3];
    int turn = 0;
    Context ctx;
    float canvasSide, cellSide;
    DatabaseHandler dbh;


    public void init() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Resources r = getResources();
                float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, r.getDisplayMetrics());
                a[row][col] = 0;
                midx[row][col] = ((px / 6) + (col * (px / 3)));
                midy[row][col] = ((px / 6) + (row * (px / 3)));
            }
        }

        oncedrawen = false;
        oncewin = false;
        turn = 0;
    }

    public TwoP_GridPaint(Context context, AttributeSet attrs) {
        super(context, attrs);
        //Draw Lines
        ctx = context;
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1f);
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        //Draw Letter X
        paintx.setStrokeWidth(25f);
        paintx.setColor(Color.rgb(253, 231, 124));
        paintx.setStyle(Paint.Style.STROKE);
        paintx.setStrokeJoin(Paint.Join.ROUND);

        //Draw Letter O
        painto.setStrokeWidth(25f);
        painto.setColor(Color.rgb(139, 192, 236));
        painto.setStyle(Paint.Style.FILL_AND_STROKE);

        painto1.setColor(Color.rgb(88, 81, 76));
        painto1.setStyle(Paint.Style.FILL);

        init();
        dbh = new DatabaseHandler(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Resources r = getResources();
        float pxi = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, r.getDisplayMetrics());
        canvasSide = pxi;
        cellSide = canvasSide / 3;
        canvas.drawLine(cellSide, 0, cellSide, canvasSide, paint);
        canvas.drawLine(2 * cellSide, 0, 2 * cellSide, canvasSide, paint);
        canvas.drawLine(0, cellSide, canvasSide, cellSide, paint);
        canvas.drawLine(0, 2 * cellSide, canvasSide, 2 * cellSide, paint);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (a[row][col] == 1) {
                    canvas.drawLine((midx[row][col] - ((4 * cellSide) / 11)), (midy[row][col] - ((4 * cellSide) / 11)), (midx[row][col] + ((4 * cellSide) / 11)), (midy[row][col] + ((4 * cellSide) / 11)), paintx);
                    canvas.drawLine((midx[row][col] + ((4 * cellSide) / 11)), (midy[row][col] - ((4 * cellSide) / 11)), (midx[row][col] - ((4 * cellSide) / 11)), (midy[row][col] + ((4 * cellSide) / 11)), paintx);
                   // Toast.makeText(getContext(),Two_player_names_entry.p2Name + " It's Your Turn!",Toast.LENGTH_SHORT).show();
                    //TextView txt = (TextView) findViewById(R.id.plyerturn);
                 //   txt.setText("player 2");
                } else if (a[row][col] == 2) {
                    canvas.drawCircle(midx[row][col], midy[row][col], (4 * cellSide) / 11, painto);
                    canvas.drawCircle(midx[row][col], midy[row][col], (13 * cellSide) / 44, painto1);
                    //Toast.makeText(getContext(),Two_player_names_entry.p1Name + " It's Your Turn!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();
            if (touchX < canvasSide && touchX > 0 && touchY < canvasSide && touchX > 0) {
                int col = (int) (touchX / cellSide);
                int row = (int) (touchY / cellSide);
                if (a[row][col] == 0) {
                    a[row][col]++;
                    turn++;
                   // Toast.makeText(getContext(),Two_player_names_entry.p2Name + " It's Your Turn!",Toast.LENGTH_SHORT).show();

                    if (turn % 2 == 0) {
                        a[row][col]++;
                    }
                    if (!oncewin && !oncedrawen) {
                        invalidate();
                        check();
                    } else {
                        Two_players.act_2players.finish();
                    }
                }
            }
        }
        return true;
    }


    public void updateWin(int i) {
        if (i == 1) {
            String tmp = "FOUND";
            try {
                tmp = dbh.checkUser(Two_player_names_entry.p1Name);
            } catch (Exception e) {
                Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if (tmp.equals("FOUND")) {
                ScoreBoard sb = dbh.getPlayer(Two_player_names_entry.p1Name);
                ScoreBoard sb2 = new ScoreBoard(sb.get_id(), sb.get_name(), (sb.get_score()) + 1);
                dbh.updatePlayer(sb2);
            } else {
                ScoreBoard sb = new ScoreBoard(dbh.getPlayerCount(), Two_player_names_entry.p1Name, 1);
                dbh.addScore(sb);
            }
        } else if (i == 2) {
            String tmp = "FOUND";
            try {
                tmp = dbh.checkUser(Two_player_names_entry.p1Name);
            } catch (Exception e) {
                Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if (tmp.equals("FOUND")) {
                ScoreBoard sb = dbh.getPlayer(Two_player_names_entry.p2Name);
                ScoreBoard sb2 = new ScoreBoard(sb.get_id(), sb.get_name(), (sb.get_score()) + 1);
                dbh.updatePlayer(sb2);
            } else {
                ScoreBoard sb = new ScoreBoard(dbh.getPlayerCount(), Two_player_names_entry.p2Name, 1);
                dbh.addScore(sb);
            }
        }
    }

    public void check() {
        if (!oncewin) {
            if (a[0][0] == a[0][1] && a[0][1] == a[0][2]) {
                if (a[0][0] == 1) {
                    Toast.makeText(getContext(), Two_player_names_entry.p1Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(1);
                    oncewin = true;
                } else if (a[0][0] == 2) {
                    Toast.makeText(getContext(), Two_player_names_entry.p2Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(2);
                    oncewin = true;
                }
            }

            if (a[1][0] == a[1][1] && a[1][1] == a[1][2]) {
                if (a[1][0] == 1) {
                    Toast.makeText(getContext(), Two_player_names_entry.p1Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(1);
                    oncewin = true;
                } else if (a[1][0] == 2) {
                    Toast.makeText(getContext(), Two_player_names_entry.p2Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(2);
                    oncewin = true;
                }
            }

            if (a[2][0] == a[2][1] && a[2][1] == a[2][2]) {
                if (a[2][0] == 1) {
                    Toast.makeText(getContext(), Two_player_names_entry.p1Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(1);
                    oncewin = true;
                } else if (a[2][0] == 2) {
                    Toast.makeText(getContext(), Two_player_names_entry.p2Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(2);
                    oncewin = true;
                }
            }

            if (a[0][0] == a[1][0] && a[1][0] == a[2][0]) {
                if (a[0][0] == 1) {
                    Toast.makeText(getContext(), Two_player_names_entry.p1Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(1);
                    oncewin = true;
                } else if (a[0][0] == 2) {
                    Toast.makeText(getContext(), Two_player_names_entry.p2Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(2);
                    oncewin = true;
                }
            }

            if (a[0][1] == a[1][1] && a[1][1] == a[2][1]) {
                if (a[0][1] == 1) {
                    Toast.makeText(getContext(), Two_player_names_entry.p1Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(1);
                    oncewin = true;
                } else if (a[0][1] == 2) {
                    Toast.makeText(getContext(), Two_player_names_entry.p2Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(2);
                    oncewin = true;
                }
            }

            if (a[0][2] == a[1][2] && a[1][2] == a[2][2]) {
                if (a[0][2] == 1) {
                    Toast.makeText(getContext(), Two_player_names_entry.p1Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(1);
                    oncewin = true;
                } else if (a[0][2] == 2) {
                    Toast.makeText(getContext(), Two_player_names_entry.p2Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(2);
                    oncewin = true;
                }
            }

            if (a[0][0] == a[1][1] && a[1][1] == a[2][2]) {
                if (a[0][0] == 1) {
                    Toast.makeText(getContext(), Two_player_names_entry.p1Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(1);
                    oncewin = true;
                } else if (a[0][0] == 2) {
                    Toast.makeText(getContext(), Two_player_names_entry.p2Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(2);
                    oncewin = true;
                }
            }

            if (a[0][2] == a[1][1] && a[1][1] == a[2][0]) {
                if (a[0][2] == 1) {
                    Toast.makeText(getContext(), Two_player_names_entry.p1Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(1);
                    oncewin = true;
                } else if (a[0][2] == 2) {
                    Toast.makeText(getContext(), Two_player_names_entry.p2Name + " wins!",Toast.LENGTH_SHORT).show();
                    updateWin(2);
                    oncewin = true;
                }
            }

            if (turn == 9 && !oncewin) {
                Toast.makeText(getContext(),"Match results in a draw!",Toast.LENGTH_SHORT).show();
                oncedrawen = true;
                updateWin(0);
            }
        }
    }
}
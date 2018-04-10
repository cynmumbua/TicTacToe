package com.example.cynthia.tic_tac_toe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView[] mBlocks = new ImageView[9];
    private TextView mDisplay,mplayer1,mplayer2;
    private ImageView mExit;
    private enum TURN {CIRCLE, CROSS}
    private Button mReset;
    private TURN mTurn;
    private int mExitCounter = 0;
    private int mStatusCounter = 0;
    private int player1Points;
    private int player2Points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReset = (Button) findViewById(R.id.reset) ;
        mDisplay = (TextView) findViewById(R.id.player_turn);
        mExit = (ImageView) findViewById(R.id.exit);
        mplayer1 = (TextView) findViewById(R.id.p1);
        mplayer2 = (TextView) findViewById(R.id.p2);
        makeScreen();
        initialize();
    }

    private void makeScreen() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
    }

    private void initialize() {
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mExitCounter == 1) {
                    finish();
                    System.exit(0);
                } else {
                    mExitCounter++;
                    Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starter = getIntent();
                finish();
                starter.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(starter);
            }
        });
        for (int position = 0; position < 9; position++) {
            int resId = getResources().getIdentifier("block_" + (position + 1), "id", getPackageName());
            mBlocks[position] = (ImageView) findViewById(resId);
            final int finalPosition = position;
            mBlocks[position].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchTurn(finalPosition);
                }
            });
        }
    }

    private void switchTurn(int position) {
        if (mTurn == TURN.CIRCLE) {
            mBlocks[position].setImageResource(R.drawable.circle);
            mBlocks[position].setId(Game_Details.CIRCLE);
            mTurn = TURN.CROSS;
            mDisplay.setText("CROSS's turn");
        } else {
            mBlocks[position].setImageResource(R.drawable.cross);
            mBlocks[position].setId(Game_Details.CROSS);
            mTurn = TURN.CIRCLE;
            mDisplay.setText("CIRCLE's turn");
        }
        mBlocks[position].setEnabled(false);
        mStatusCounter++;
        if (Game_Details.isCompleted(position + 1, mBlocks)) {
            mDisplay.setText(Game_Details.sWinner + " won");
            displayStick(Game_Details.sSet);
            disableAll();
        }else if (mStatusCounter==9){
            mDisplay.setText("DRAW. Try again");
        }
    }

    private void displayStick(int stick) {
        View view;
        switch (stick) {
            case 1:
                view = findViewById(R.id.top_horizontal);
                break;
            case 2:
                view = findViewById(R.id.center_horizontal);
                break;
            case 3:
                view = findViewById(R.id.bottom_horizontal);
                break;
            case 4:
                view = findViewById(R.id.left_vertical);
                break;
            case 5:
                view = findViewById(R.id.center_vertical);
                break;
            case 6:
                view = findViewById(R.id.right_vertical);
                break;
            case 7:
                view = findViewById(R.id.left_right_diagonal);
                break;
            case 8:
                view = findViewById(R.id.right_left_diagonal);
                break;
            default://which will never happen
                view = findViewById(R.id.top_horizontal);
        }
        view.setVisibility(View.VISIBLE);
    }

    private void disableAll() {
        for (int i = 0; i < 9; i++)
            mBlocks[i].setEnabled(false);
    }

    @Override
    public void onBackPressed() {

    }

}

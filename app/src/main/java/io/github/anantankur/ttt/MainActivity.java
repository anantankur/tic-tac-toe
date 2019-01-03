package io.github.anantankur.ttt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {6, 4, 2}};

    boolean gameActive = true;

    boolean isTie = false;

    // 0: for red ...... 1: for yellow
    int activePlayer = 0;

    public void dropIn(View view) {

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameActive) {

            if (gameState[tappedCounter] == 2) {

                gameState[tappedCounter] = activePlayer;

                if (activePlayer == 0) {

                    counter.setImageResource(R.drawable.red);
                    counter.setTranslationY(-1500);
                    counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
                    activePlayer = 1;
                    Log.i("info", gameState.length+" not working");
                } else {

                    counter.setImageResource(R.drawable.yellow);
                    counter.setTranslationX(-1500);
                    counter.animate().translationXBy(1500).rotation(3600).setDuration(300);
                    activePlayer = 0;
                }

                if (gameState[0]==2 || gameState[1]==2 || gameState[2]==2 || gameState[3]==2 || gameState[4]==2 || gameState[5]==2 || gameState[6]==2 || gameState[7]==2 || gameState[8]==2) {

                    isTie = false;
                } else{

                    isTie = true;
                }

                //this is for tie

                if (isTie) {
                    String tie = "Match tied";
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setText(tie);
                }
            }

            String message;

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    //someone has won
                    if (activePlayer == 0) {

                        message = "Mr Yellow has won";
                    } else {

                        message = "Mr Red has won";
                    }

                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setText(message);

                    gameActive = false;
                }
            }
        }
    }

    public  void playAgain(View view) {

        gameActive = true;

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int j = 0; j<gameState.length; j++) {

            gameState[j] = 2;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayGame extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b11,b12;
    TextView x, y;
    boolean isPlayerXTurn;
    String currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b11 = findViewById(R.id.button11);
        b12 = findViewById(R.id.button12);
        x = findViewById(R.id.scoreX);
        y = findViewById(R.id.scoreY);

        // Get the selected player from the intent
        Intent intent = getIntent();
        currentPlayer = intent.getStringExtra("selectedPlayer");

        // Set initial turn based on the selected player
        isPlayerXTurn = "Player X".equals(currentPlayer);

        // Set up button click listeners
        setupButtonClickListener(b1);
        setupButtonClickListener(b2);
        setupButtonClickListener(b3);
        setupButtonClickListener(b4);
        setupButtonClickListener(b5);
        setupButtonClickListener(b6);
        setupButtonClickListener(b7);
        setupButtonClickListener(b8);
        setupButtonClickListener(b9);

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame(); // Reset the game
                x.setText("0");
                y.setText("0");
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Exit the game
            }
        });
    }

    private void setupButtonClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMove((Button) view);
            }
        });
    }

    private void setMove(Button button) {
        if (button.getText().toString().equals("")) {
            if (isPlayerXTurn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            if (checkForWinner()) {
                if (isPlayerXTurn) {
                    Toast.makeText(PlayGame.this, "Player X Win ! Now Player O turns", Toast.LENGTH_LONG).show();
                    updateScore(x);
                } else {
                    Toast.makeText(PlayGame.this, "Player O Win ! Now Player X turns", Toast.LENGTH_LONG).show();
                    updateScore(y);
                }
                disableAllButtons();
                // Switch turn after win
                isPlayerXTurn = !isPlayerXTurn;
                // Start new game for the next player
                resetGame();
            } else {
                isPlayerXTurn = !isPlayerXTurn; // Toggle turn
            }
        }
    }

    private boolean checkForWinner() {
        String[] board = new String[]{
                b1.getText().toString(),
                b2.getText().toString(),
                b3.getText().toString(),
                b4.getText().toString(),
                b5.getText().toString(),
                b6.getText().toString(),
                b7.getText().toString(),
                b8.getText().toString(),
                b9.getText().toString()
        };

        // Winning combinations
        return (checkWin(board[0], board[1], board[2]) ||
                checkWin(board[3], board[4], board[5]) ||
                checkWin(board[6], board[7], board[8]) ||
                checkWin(board[0], board[3], board[6]) ||
                checkWin(board[1], board[4], board[7]) ||
                checkWin(board[2], board[5], board[8]) ||
                checkWin(board[0], board[4], board[8]) ||
                checkWin(board[2], board[4], board[6]));
    }

    private boolean checkWin(String s1, String s2, String s3) {
        return s1.equals(s2) && s2.equals(s3) && !s1.equals("");
    }

    private void updateScore(TextView scoreView) {
        int score = Integer.parseInt(scoreView.getText().toString());
        scoreView.setText(String.valueOf(score + 1));
    }

    private void disableAllButtons() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);
    }

    private void resetGame() {
        // Re-enable buttons
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b9.setEnabled(true);
        // Clear button texts
        btn_Clear();

    }

    private void btn_Clear() {
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");
    }
}









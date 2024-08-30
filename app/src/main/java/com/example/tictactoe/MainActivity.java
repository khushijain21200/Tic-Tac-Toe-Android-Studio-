package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    RadioGroup playerGroup;
    RadioButton selectedPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.redirect);
        playerGroup = findViewById(R.id.player_selection_group);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = playerGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this, "Please select a player", Toast.LENGTH_SHORT).show();
                } else {
                    selectedPlayer = findViewById(selectedId);
                    String player = selectedPlayer.getText().toString();

                    Intent intent = new Intent(MainActivity.this, PlayGame.class);
                    intent.putExtra("selectedPlayer", player);
                    startActivity(intent);
                }
            }
        });
    }
}

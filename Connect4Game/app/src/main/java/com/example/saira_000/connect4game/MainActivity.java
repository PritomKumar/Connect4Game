package com.example.saira_000.connect4game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button aiPlayer;
    private Button twoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aiPlayer = (Button) findViewById(R.id.aiPlayerButton);
        twoPlayer = (Button) findViewById(R.id.twoPlayerButton);

        aiPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AIPlayerLevel.class);
                startActivity(intent);
            }
        });

        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , TwoPlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}

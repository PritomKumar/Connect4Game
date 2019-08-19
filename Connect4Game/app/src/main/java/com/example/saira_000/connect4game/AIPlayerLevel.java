package com.example.saira_000.connect4game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AIPlayerLevel extends AppCompatActivity {

    private Button babyLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiplayer_level);

        babyLevel = (Button) findViewById(R.id.babyLevel);

        babyLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIPlayerLevel.this , AIPlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}

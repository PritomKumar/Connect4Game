package com.example.saira_000.connect4game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AIPlayerLevel extends AppCompatActivity {

    private Button babyLevel;
    private Button easyLevel;
    private Button mediumLevel;
    private Button hardLevel;
    private Button veryHardLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiplayer_level);

        babyLevel = (Button) findViewById(R.id.babyLevel);

        babyLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIPlayerLevel.this , AIPlayerActivity.class);
                intent.putExtra("level" , 0);

                startActivity(intent);
            }
        });

        easyLevel = (Button) findViewById(R.id.easyLevel);

        easyLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIPlayerLevel.this , AIPlayerActivity.class);
                intent.putExtra("level" , 1);
                startActivity(intent);
            }
        });

        mediumLevel = (Button) findViewById(R.id.mediumLevel);

        mediumLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIPlayerLevel.this , AIPlayerActivity.class);
                intent.putExtra("level" , 3);
                startActivity(intent);
            }
        });

        hardLevel = (Button) findViewById(R.id.hardLevel);

        hardLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIPlayerLevel.this , AIPlayerActivity.class);
                intent.putExtra("level" , 4);
                startActivity(intent);
            }
        });

        veryHardLevel = (Button) findViewById(R.id.veryHardLevel);

        veryHardLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIPlayerLevel.this , AIPlayerActivity.class);
                intent.putExtra("level" , 5);
                startActivity(intent);
            }
        });
    }
}

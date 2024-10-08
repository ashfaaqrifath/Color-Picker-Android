package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button playButton;

    EditText playerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton=findViewById(R.id.btn_play);
        playerName=findViewById(R.id.et_player_name);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayButtonTapped(v);
            }
        });
    }
    private void onPlayButtonTapped(View v){
        String player=playerName.getText().toString();

        if(player.isEmpty()){
            playerName.setError("Enter Player Name");
            return;
        }
        else {
            Intent intent = new Intent(this, GameUI.class);
            intent.putExtra("playerName", player);

            startActivity(intent);
        }


    }
}
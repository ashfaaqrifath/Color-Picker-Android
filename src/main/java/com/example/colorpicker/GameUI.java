package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameUI extends AppCompatActivity {

    List<ColorDetail> colorDetails=new ArrayList<>();
    private int index;
    private int points = 0;
    Button firstButton, secondButton;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ui);

        firstButton = findViewById(R.id.btn_first);
        secondButton = findViewById(R.id.btn_second);
        welcome = findViewById(R.id.tv_welcome);

        Intent received=getIntent();
        String name=received.getStringExtra("playerName");
        welcome.setText("Welcome " + name);
        populateColors();
        setColor();
    }

    private void populateColors(){
        colorDetails.add(new ColorDetail("Blue", "#ff33b5e5", "#ff669900"));
        colorDetails.add(new ColorDetail("Green", "#ff669900", "#FFA500"));
        colorDetails.add(new ColorDetail("Orange", "#FFA500", "#ffaa66cc"));
        colorDetails.add(new ColorDetail("Purple", "#ffaa66cc", "#ffcc0000"));
        colorDetails.add(new ColorDetail("Red", "#ffcc0000", "#A9A9A9"));
        colorDetails.add(new ColorDetail("Gray", "#A9A9A9", "#fff757f7"));
        colorDetails.add(new ColorDetail("Pink", "#fff757f7", "#ff33b5e5"));
    }

    private void setColor(){
        if (index < colorDetails.size()) {
            Random random = new Random();
            int number = random.nextInt(10);

            ColorDetail colorDetail=colorDetails.get(index);

            if (number % 2 == 0) {
                configureButtons(colorDetail, secondButton, firstButton);
            }
            else {
                configureButtons(colorDetail, firstButton, secondButton);
            }

            index++;
        }
        else  {
            int score = (int)((double)points / (double)colorDetails.size() * 100);
            Toast.makeText(this, "Your score is " + score, Toast.LENGTH_LONG).show();
        }
    }

    private void configureButtons(ColorDetail colorDetail, Button correctButton, Button wrongButton){
        correctButton.setBackgroundColor(Color.parseColor(colorDetail.getHex()));
        correctButton.setTag(1);
        correctButton.setText(colorDetail.getName());

        wrongButton.setBackgroundColor(Color.parseColor(colorDetail.getWrongHex()));
        wrongButton.setTag(0);
        wrongButton.setText(colorDetail.getName());
    }


    public void onButtonTapped(View view) {
        Button button = (Button) view;
        int tag=(int)button.getTag();

        if(tag == 1){
            points++;
        }
        else{
            points--;
        }
        setColor();
    }
}

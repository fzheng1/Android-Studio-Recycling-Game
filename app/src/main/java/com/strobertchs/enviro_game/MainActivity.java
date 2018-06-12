package com.strobertchs.enviro_game;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Screen Size
    private int screenWidth;
    private int screenHeight;

    //Images
    private ImageView waterUp;
    private ImageView boxDown;
    private ImageView juiceRight;
    private ImageView appleLeft;

    //Position
    private float waterUpX;
    private float waterUpY;
    private float boxDownX;
    private float boxDownY;
    private float juiceRightX;
    private float juiceRightY;
    private float appleLeftX;
    private float appleLeftY;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button instructionBtn = (Button) findViewById(R.id.instructionBtn);

        // make the play button take user to another screen (Game)
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Game.class);

                startActivity(startIntent);
            }
        });

        // make the instruction button take user to another screen (Activity 3)
        instructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Instructions.class);

                startActivity(startIntent);
            }
        });


        waterUp = (ImageView) findViewById(R.id.waterUp);
        boxDown = (ImageView) findViewById(R.id.boxDown);
        juiceRight = (ImageView) findViewById(R.id.juiceRight);
        appleLeft = (ImageView) findViewById(R.id.appleLeft);

        // Get Screen Size

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Move to out of screen

        waterUp.setX(-80.0f);
        waterUp.setY(-80.0f);
        boxDown.setX(-80.0f);
        boxDown.setY(screenHeight + 80.0f);
        juiceRight.setX(screenWidth + 80.0f);
        juiceRight.setY(-80.0f);
        appleLeft.setX(-80.0f);
        appleLeft.setY(-80.0f);

        //Start the timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                changePos();

            }
        }, 0, 20);


    }

        public void changePos(){
            //Up
            waterUpY -= 10;
            if (waterUp.getY() + waterUp.getHeight() < 0) {
                waterUpX = (float)Math.floor(Math.random() * (screenWidth - waterUp.getWidth()));
                waterUpY = screenHeight + 100.0f;
            }
            waterUp.setX(waterUpX);
            waterUp.setY(waterUpY);

            //Down
            boxDownY += 10;
            if (boxDown.getY() > screenHeight){
                boxDownX = (float)Math.floor(Math.random() * (screenWidth - boxDown.getWidth()));
                boxDownY = -100.0f;
            }
            boxDown.setX(boxDownX);
            boxDown.setY(boxDownY);

            //Right
            juiceRightX += 10;
            if (juiceRight.getX() > screenWidth){
                juiceRightX = -100.0f;
                juiceRightY = (float)Math.floor(Math.random() * (screenHeight - juiceRight.getWidth()));
            }

            juiceRight.setX(juiceRightX);
            juiceRight.setY(juiceRightY);

            //Left
            appleLeftX -= 10;
            if(appleLeft.getX() + appleLeft.getWidth() < 0){
                appleLeftX = screenWidth + 100.0f;
                appleLeftY = (float)Math.floor(Math.random() * (screenHeight - appleLeft.getWidth()));
            }
            appleLeft.setX(appleLeftX);
            appleLeft.setY(appleLeftY);

        }


    }




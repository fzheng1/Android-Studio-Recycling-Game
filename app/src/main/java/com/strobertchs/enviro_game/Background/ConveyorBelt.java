package com.strobertchs.enviro_game.Background;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ConveyorBelt {

    private Bitmap image;
    private int x, y, dx;

    public ConveyorBelt(Bitmap res){
        image = res;
//        dx = GamePanel.MOVESPEED;
    }

    public void update(){
        x += dx;
        if (x < -com.strobertchs.enviro_game.GamePanel.WIDTH){
            x = 0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, 1100, null);
        if(x < 0){
            canvas.drawBitmap(image, x+ com.strobertchs.enviro_game.GamePanel.WIDTH, 1100, null);
        }
    }

    public void setVector(int vector){
        this.dx = vector;
    }
}

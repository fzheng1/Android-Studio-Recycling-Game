package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Juice extends GameObject {

    private Bitmap image;
//    private int originalPosition;
//    private Rect rect;

    public Juice(int x, int y, int velX, ID id, Bitmap res){
        super(x, y, id);
        image = res;
        this.velX = velX;
        originalPosition = x;
//        rect = new Rect(x, y, x+50, y+150);
    }

    public void update(){
        x += velX;
        originalPosition += velX;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }

//    public void setVector(int vector){
//        this.dx = vector;
//    }
//    @Override
//    public void update() {
//        x += velX;
//        if (x < -com.strobertchs.enviro_game.GamePanel.WIDTH){
//             // TODO remove the object
//        }
//    }
//
//    // TODO update override if the object is touched by player
//
//    @Override
//    public void draw(Canvas canvas) {
//        canvas.drawBitmap(image, x, 0, null);
//        if(x<0){
//            // TODO Object is destroyed
//        }
//
//    }


    public void setVector(int vector){
        this.velX = vector;
    }

    public int getOriginalPosition(){
        return this.originalPosition;
    }

}

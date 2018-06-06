package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.MotionEvent;

import com.strobertchs.enviro_game.Handler;
import static com.strobertchs.enviro_game.MainThread.canvas;


public class Apple extends GameObject{

    private Bitmap image;
//    private int originalPosition;

    public Apple(int x, int y, int velX, ID id, Bitmap res){
        super(x, y, id);
        image = res;
        this.velX = velX;
        originalPosition = x;
    }

    public void update(){
        x += velX;
        originalPosition += velX;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);

    }

    public void dispose(){
        image.recycle();
    }


    public void setVector(int vector){
        this.velX = vector;
    }

//    public int getOriginalPosition(){
//        return this.originalPosition;
//    }

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

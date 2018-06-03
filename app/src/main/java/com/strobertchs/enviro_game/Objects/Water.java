package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.Rectangle;
import com.strobertchs.enviro_game.Handler;
import static com.strobertchs.enviro_game.MainThread.canvas;

public class Water extends GameObject {

    private Bitmap image;
    private int x, y, dx;

    public Water(int x, int y, ID id, Bitmap res){
        super(x, y, id);
        image = res;
    }

    public void update(){
        x += dx;
        if (x < -com.strobertchs.enviro_game.GamePanel.WIDTH){
            x = 0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, 1150, null);
        if(x < 0){
            canvas.drawBitmap(image, x+ com.strobertchs.enviro_game.GamePanel.WIDTH, 1150, null);
        }
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

    @Override
    public Rectangle getBounds() {
        this.rectangle.setBounds(x, y, 200, 200);
        return this.rectangle;
    }

    public void setVector(int vector){
        this.dx = vector;
    }
}

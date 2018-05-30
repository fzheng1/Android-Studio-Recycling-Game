package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.support.constraint.solver.widgets.Rectangle;

import com.strobertchs.enviro_game.Handler;

import static com.strobertchs.enviro_game.MainThread.canvas;

public class Recyclable extends GameObject {

    Handler handler;
    private Bitmap image;
    private int x,y,dx;

    public Recyclable(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }


    @Override
    public void update() {
        x += dx;
        if (x < -com.strobertchs.enviro_game.GamePanel.WIDTH){
             // TODO remove the object
        }
    }

    // TODO update override if the object is touched by player

    @Override
    public void draw() {
        canvas.drawBitmap(image, x, 0, null);
        if(x<0){
            canvas.drawBitmap(image, x + com.strobertchs.enviro_game.GamePanel.WIDTH, 950, null);
        }

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

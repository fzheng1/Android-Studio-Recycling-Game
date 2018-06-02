package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.Rectangle;

import com.strobertchs.enviro_game.Handler;

import static com.strobertchs.enviro_game.MainThread.canvas;

public class Recyclable extends GameObject {

    Handler handler;
    private Bitmap image;

    public Recyclable(int x, int y, ID id, Bitmap res) {
        super(x, y, id);
        image = res;
    }


    @Override
    public void update() {
        x += velX;
        if (x < -com.strobertchs.enviro_game.GamePanel.WIDTH){
            // TODO remove the object
        }
    }

    // TODO update override if the object is touched by player

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, 0, null);
        if(x<0){
            // TODO Object is destroyed
        }

    }

    @Override
    public Rectangle getBounds() {
        this.rectangle.setBounds(x, y, 200, 200);
        return this.rectangle;
    }
}

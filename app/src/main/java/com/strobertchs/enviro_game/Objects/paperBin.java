package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.support.constraint.solver.widgets.Rectangle;

import static com.strobertchs.enviro_game.MainThread.canvas;

public class paperBin extends GameObject {
    private Bitmap image;
    private int x, y;

    public paperBin(int x, int y, ID id, Bitmap res) {
        super(x, y, id);
        image = res;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        canvas.drawBitmap(image, x, 0, null);
        if(x<0){
            canvas.drawBitmap(image, 300, 100, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import static com.strobertchs.enviro_game.MainThread.canvas;

public class RecyclingBin extends GameObject {

    private Bitmap image;
    private int x, y;

    public RecyclingBin(int x, int y, ID id, Bitmap res) {
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
            canvas.drawBitmap(image, 300, 300, null);
        }
    }

    @Override
    public Rectangle getBounds() {

        return null;
    }
}

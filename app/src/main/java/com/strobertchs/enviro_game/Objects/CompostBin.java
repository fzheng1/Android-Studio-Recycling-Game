package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class CompostBin extends GameObject {
    private Bitmap image;
//    private Rect rect;

    public CompostBin(int x, int y, ID id, Bitmap res) {
        super(x, y, id);
        image = res;
//        rect = new Rect(x, y, x+200, y+200);
    }

    @Override
    public void update() {

    }

//    public Rect getRect() {
//        return rect;
//    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }
}

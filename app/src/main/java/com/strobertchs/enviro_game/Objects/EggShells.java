package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class EggShells extends GameObject {
    private Bitmap image;

    public EggShells(int x, int y, int velX, ID id, Bitmap res) {
        super(x, y, id);
        image = res;
        this.velX = velX;
        originalPosition = x;
    }

    public void update() {
        x += velX;
        originalPosition += velX;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);

    }

    public void dispose() {
        image.recycle();
    }


    public void setVector(int vector) {
        this.velX = vector;
    }

}


package com.strobertchs.enviro_game.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.Rectangle;

import static com.strobertchs.enviro_game.MainThread.canvas;

public class CompostBin extends GameObject {
    private Bitmap image;

    public CompostBin(int x, int y, ID id, Bitmap res) {
        super(x, y, id);
        image = res;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        this.rectangle.setBounds(x, y, 200, 200);
        return this.rectangle;
    }
}
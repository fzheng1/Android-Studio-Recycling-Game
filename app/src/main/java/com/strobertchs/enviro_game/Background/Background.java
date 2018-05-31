package com.strobertchs.enviro_game.Background;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
    private Bitmap image;


    public Background(Bitmap res){
        image = res;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, 0, 0, null);

    }



}

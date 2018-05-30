package com.strobertchs.enviro_game.Background;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
    private Bitmap image;
    private int x,y;

    public Background(Bitmap res){
        image = res;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, 0, null);
        if(x<0){
            canvas.drawBitmap(image, x + com.strobertchs.enviro_game.GamePanel.WIDTH, 1000, null);
        }
    }



}

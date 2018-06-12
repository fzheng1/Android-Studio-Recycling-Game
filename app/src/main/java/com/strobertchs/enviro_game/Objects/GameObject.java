package com.strobertchs.enviro_game.Objects;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class GameObject {

    protected int x, y, originalPosition;

    protected double velX, velY;

    protected ID id;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.originalPosition = x;
        this.y = y;
        this.id = id;
    }


    // actions of the object each refresh
    public abstract void update();

    // graphics of the object
    public abstract void draw(Canvas canvas);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getOriginalPosition(){
        return this.originalPosition;
    }



}

package com.strobertchs.enviro_game.Objects;

import android.support.constraint.solver.widgets.Rectangle;

public abstract class GameObject {

    protected int x, y;

    protected double velX, velY;

    protected ID id;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    // actions of the object each refresh
    public abstract void tick();

    // graphics of the object
    public abstract void render();

    // get outline of object's size to test for overlap
    public abstract Rectangle getBounds();

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
}

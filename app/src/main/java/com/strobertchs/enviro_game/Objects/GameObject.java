package com.strobertchs.enviro_game.Objects;

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





}

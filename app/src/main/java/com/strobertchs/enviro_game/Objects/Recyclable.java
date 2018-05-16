package com.strobertchs.enviro_game.Objects;

import android.support.constraint.solver.widgets.Rectangle;

import com.strobertchs.enviro_game.Handler;

public class Recyclable extends GameObject {

    Handler handler;

    public Recyclable(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render() {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

package com.strobertchs.enviro_game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

import com.strobertchs.enviro_game.Objects.GameObject;
import com.strobertchs.enviro_game.Objects.ID;

import java.util.LinkedList;

// this class will decide what the game does with the gameobjects
public class Handler {

    // where the objects that are created are going to be stored
    //linked list is an expandable array
    // <E> indicates what the list is storing
    public static LinkedList<GameObject> gameObjects = new LinkedList<>();

    private boolean collision = false;

    // run the tick methods of the gameobjects
    public void update(){
        for (GameObject tempobject: gameObjects) {
            tempobject.update();
        }
    }

    public void setVelx(int velx){
        for (GameObject tempObject: gameObjects){
            tempObject.setVelX(velx);
        }
    }

    public void remove(){
        for (GameObject tempObject: gameObjects){
            if (tempObject.getX() < -200 && tempObject.getId() != ID.compostBin && tempObject.getId() != ID.recycleBin && tempObject.getId() != ID.paperBin && tempObject.getId() != ID.trashBin){
                gameObjects.remove(tempObject);
                GamePanel.MOVESPEED ++;
            }
        }
    }

    public void collision(){
        for (GameObject tempObject: gameObjects){
            ID id = tempObject.getId();
            int xLoc = tempObject.getX();
            int yLoc = tempObject.getY();

            //recyclable and recycling bin collision
            if (xLoc > 800 && yLoc > 830 && xLoc < 1000 && yLoc < 1030 && id == ID.recyclable){
                gameObjects.remove(tempObject);
                collision = true;
            }
            //trash and trash bin collision
            if (xLoc > 50 && yLoc > 830 && xLoc < 250 && yLoc < 1030 && id == ID.garbage){
                gameObjects.remove(tempObject);
                collision = true;
            }
            //compost and compost bin collision
            if (xLoc > 50 && yLoc > 50 && xLoc < 250 && yLoc < 250 && id == ID.compost){
                gameObjects.remove(tempObject);
                collision = true;
            }
            //paper and paper bin collision
            if (xLoc > 800 && yLoc > 50 && xLoc < 1000 && yLoc < 250 && id == ID.paper){
                gameObjects.remove(tempObject);
                collision = true;
            }
        }

    }

    public void draw(Canvas canvas){
        for (GameObject tempobject: gameObjects){
            tempobject.draw(canvas);
        }
    }

    // add an object to the list eg. want a new player
    public void addObject(GameObject object){
        this.gameObjects.add(object);
    }

    // remove object from list eg. When enemy dies
    public void removeObject(GameObject object){
        this.gameObjects.remove(object);
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

}

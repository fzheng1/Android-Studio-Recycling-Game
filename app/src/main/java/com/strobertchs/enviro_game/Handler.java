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

    // run the update methods of the gameobjects
    public void update(){
        for (GameObject tempobject: gameObjects) {
            tempobject.update();
        }
    }

    // sets the movespeed of every object
    public void setVelx(int velx){
        for (GameObject tempObject: gameObjects){
            tempObject.setVelX(velx);
        }
    }

    // automatically remove the object if it goes off screen
    public void remove(){
        for (GameObject tempObject: gameObjects){
            if (tempObject.getX() < -200 && tempObject.getId() != ID.compostBin && tempObject.getId() != ID.recycleBin && tempObject.getId() != ID.paperBin && tempObject.getId() != ID.trashBin){
                gameObjects.remove(tempObject);
                // decrease Movespeed after an object is lost to make game easier
                GamePanel.MOVESPEED ++;
            }
        }
    }

    // check if the ids for collision line up
    public void collision(){
        for (GameObject tempObject: gameObjects){
            ID id = tempObject.getId();
            int xLoc = tempObject.getX();
            int yLoc = tempObject.getY();

            //recyclable and recycling bin collision
            if (xLoc > gameObjects.get(0).getX() && yLoc > gameObjects.get(0).getY() && xLoc < gameObjects.get(0).getX() + 200 && yLoc < gameObjects.get(0).getY() + 200 && id == ID.recyclable){
                gameObjects.remove(tempObject);
                collision = true;
            }

            //trash and trash bin collision
            if (xLoc > gameObjects.get(1).getX() && yLoc > gameObjects.get(1).getY() && xLoc < gameObjects.get(1).getX() + 200 && yLoc < gameObjects.get(1).getY() + 200 && id == ID.garbage){
                gameObjects.remove(tempObject);
                collision = true;
            }
            //compost and compost bin collision
            if (xLoc > gameObjects.get(2).getX() && yLoc > gameObjects.get(2).getY() && xLoc < gameObjects.get(2).getX() + 200 && yLoc < gameObjects.get(2).getY() + 200 && id == ID.compost){
                gameObjects.remove(tempObject);
                collision = true;
            }
            //paper and paper bin collision
            if (xLoc > gameObjects.get(3).getX() && yLoc > gameObjects.get(3).getY() && xLoc < gameObjects.get(3).getX() + 200 && yLoc < gameObjects.get(3).getY() + 200 && id == ID.paper){
                gameObjects.remove(tempObject);
                collision = true;
            }
        }
    }

    // method to draw every game object
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

    // check if a valid collision occured b/w the first item and a bin
    public boolean isCollision() {
        return collision;
    }

    // set collision to false after collision occurs
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

}

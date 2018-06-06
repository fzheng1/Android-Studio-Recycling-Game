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
    LinkedList<GameObject> gameObjects = new LinkedList<>();

    // run the tick methods of the gameobjects
    public void update(){
        for (GameObject tempobject: gameObjects) {
            tempobject.update();
        }
    }

    public void remove(){
        for (GameObject tempObject: gameObjects){
            if (tempObject.getX() < -200 && tempObject.getId() != ID.compostBin && tempObject.getId() != ID.recycleBin && tempObject.getId() != ID.paperBin && tempObject.getId() != ID.trashBin){
                gameObjects.remove(tempObject);
            }
        }
    }

    public void collision(){
        for (GameObject tempObject: gameObjects){
            ID id = tempObject.getId();
            int xLoc = tempObject.getX();
            int yLoc = tempObject.getY();

            //recyclable and recycling bin collision
            if (xLoc > 590 && yLoc > 750 && xLoc < 790 && yLoc < 950 && id == ID.recyclable){
                gameObjects.remove(tempObject);
            }
            //trash and trash bin collision
            if (xLoc > 330 && yLoc > 750 && xLoc < 530 && yLoc < 950 && id == ID.trashBin){
                gameObjects.remove(tempObject);
            }
            //compost and compost bin collision
            if (xLoc > 320 && yLoc > 500 && xLoc < 520 && yLoc < 700 && id == ID.compost){
                gameObjects.remove(tempObject);
            }
            //paper and paper bin collision
            if (xLoc > 590 && yLoc > 500 && xLoc < 790 && yLoc < 700 && id == ID.paper){
                gameObjects.remove(tempObject);
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



}

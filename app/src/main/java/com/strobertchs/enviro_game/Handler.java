package com.strobertchs.enviro_game;

import android.graphics.Canvas;

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

    public void removeObjectOffScreen(){
        for (GameObject tempobject: gameObjects) {
            if(tempobject.getX() < 100){
                gameObjects.remove(tempobject);
            }
        }
    }

}

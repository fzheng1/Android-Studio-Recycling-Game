package com.strobertchs.enviro_game;

import com.strobertchs.enviro_game.Objects.GameObject;

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
}

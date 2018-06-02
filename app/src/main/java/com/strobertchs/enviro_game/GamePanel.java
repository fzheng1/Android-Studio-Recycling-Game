package com.strobertchs.enviro_game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.strobertchs.enviro_game.Background.*;
import com.strobertchs.enviro_game.Objects.*;

import java.util.Random;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    public static final int WIDTH = 1499;
    public static final int HEIGHT = 67;
    public static final int MOVESPEED = -5;
    private Handler handler = new Handler();

    private MainThread thread;
    private ConveyorBelt cb;
    private Background bg;
    private RecyclingBin recyclingBin;
    private CompostBin compostBin;
    private PaperBin paperBin;
    private TrashBin trashBin;
    private Recyclable recyclable;
    private Random random;

    public GamePanel(Context context){
        super(context);

        // add callback to surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        // make the game panel interactable
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        // pass image from drawable into background

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        cb = new ConveyorBelt(BitmapFactory.decodeResource(getResources(), R.drawable.conveyer_belt));
        cb.setVector(-10);

//        // Horizontal
//        recyclingBin = new RecyclingBin(200, 900, ID.recycleBin, BitmapFactory.decodeResource(getResources(), R.drawable.recycle_bin));
//        trashBin = new TrashBin(0, 900, ID.trashBin, BitmapFactory.decodeResource(getResources(), R.drawable.trash_bin));
//        compostBin = new CompostBin(450, 900, ID.compostBin, BitmapFactory.decodeResource(getResources(), R.drawable.compost_bin));
//        paperBin = new PaperBin(700, 900, ID.paperBin, BitmapFactory.decodeResource(getResources(), R.drawable.paper_bin));

        // square
        recyclingBin = new RecyclingBin(590, 750, ID.recycleBin, BitmapFactory.decodeResource(getResources(), R.drawable.recycle_bin));
        trashBin = new TrashBin(330, 750, ID.trashBin, BitmapFactory.decodeResource(getResources(), R.drawable.trash_bin));
        compostBin = new CompostBin(320, 500, ID.compostBin, BitmapFactory.decodeResource(getResources(), R.drawable.compost_bin));
        paperBin = new PaperBin(590, 500, ID.paperBin, BitmapFactory.decodeResource(getResources(), R.drawable.paper_bin));


        handler.addObject(recyclingBin);
        handler.addObject(trashBin);
        handler.addObject(compostBin);
        handler.addObject(paperBin);


//        for (int i = 0; i < 5; i++) {
//            int rand = random.nextInt(6);
//
//        }

        //safely start game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while (retry){
            try {
//                thread.join();
                thread.interrupt();
            } catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }

        //stop thread
        thread.interrupt();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){return super.onTouchEvent(event);}


    public void update(){
        cb.update();

        // update game objects
        handler.update();

        // remove the object from handler if it goes off screen
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getX() < -com.strobertchs.enviro_game.GamePanel.WIDTH){
                handler.removeObject(tempObject);
                // TODO Condition if object is lost
            }
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        final float scaleFactorX = (float) getWidth()/WIDTH;
        final float scaleFactorY = (float) getHeight()/HEIGHT;

        if (canvas != null){
            final int savedState = canvas.save();

            bg.draw(canvas);
            cb.draw(canvas);

            // draw the game objects
            handler.draw(canvas);

            canvas.restoreToCount(savedState);
        }

    }



}

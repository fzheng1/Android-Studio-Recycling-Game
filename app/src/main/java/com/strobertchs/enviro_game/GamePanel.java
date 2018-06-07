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

// creates area where game is run and
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    // size of surface game is run on
    public static final int WIDTH = 1499;
    public static final int HEIGHT = 67;

    // speed objects move at
    public static int MOVESPEED = -10;

    // initialize handler so objects can be created
    private Handler handler = new Handler();

    //
    private MainThread thread;
    private ConveyorBelt cb;
    private Background bg;
    private RecyclingBin recyclingBin;
    private CompostBin compostBin;
    private PaperBin paperBin;
    private TrashBin trashBin;
    private Random random = new Random();



    public GamePanel(Context context){
        super(context);

        // add callback to surfaceholder to intercept events
        getHolder().addCallback(this);

        // start the thread
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
        cb.setVector(MOVESPEED);

//        // Horizontal
//        recyclingBin = new RecyclingBin(200, 900, ID.recycleBin, BitmapFactory.decodeResource(getResources(), R.drawable.recycle_bin));
//        trashBin = new TrashBin(0, 900, ID.trashBin, BitmapFactory.decodeResource(getResources(), R.drawable.trash_bin));
//        compostBin = new CompostBin(450, 900, ID.compostBin, BitmapFactory.decodeResource(getResources(), R.drawable.compost_bin));
//        paperBin = new PaperBin(700, 900, ID.paperBin, BitmapFactory.decodeResource(getResources(), R.drawable.paper_bin));

//         square
        recyclingBin = new RecyclingBin(675, 830, ID.recycleBin, BitmapFactory.decodeResource(getResources(), R.drawable.recycle_bin));
        trashBin = new TrashBin(245, 830, ID.trashBin, BitmapFactory.decodeResource(getResources(), R.drawable.trash_bin));
        compostBin = new CompostBin(235, 370, ID.compostBin, BitmapFactory.decodeResource(getResources(), R.drawable.compost_bin));
        paperBin = new PaperBin(675, 370, ID.paperBin, BitmapFactory.decodeResource(getResources(), R.drawable.paper_bin));

        // add objects to handler
        handler.addObject(recyclingBin);
        handler.addObject(trashBin);
        handler.addObject(compostBin);
        handler.addObject(paperBin);



        for (int i = 0; i < 30; i++) {
            int rnd = random.nextInt(5);
            if (rnd == 0){
                // water
                handler.addObject(new Water(WIDTH + 500 * i, 1150, MOVESPEED, ID.recyclable, BitmapFactory.decodeResource(getResources(), R.drawable.water)));
            }
            else if (rnd == 1){
                //juice box
                handler.addObject( new Juice(WIDTH + 500 * i, 1150, MOVESPEED, ID.recyclable, BitmapFactory.decodeResource(getResources(), R.drawable.juice)));
            }
            else if (rnd == 2){
                handler.addObject( new Apple(WIDTH + 500 * i, 1150, MOVESPEED, ID.compost, BitmapFactory.decodeResource(getResources(), R.drawable.apple)));
            }
            else if (rnd == 3){
                handler.addObject( new Box(WIDTH + 500 * i, 1150, MOVESPEED, ID.paper, BitmapFactory.decodeResource(getResources(), R.drawable.box)));
            }
            else if (rnd == 4){
                handler.addObject( new Styrofoam(WIDTH + 500 * i, 1150, MOVESPEED, ID.garbage, BitmapFactory.decodeResource(getResources(), R.drawable.styrofoam)));
            }
        }

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
    public boolean onTouchEvent(MotionEvent event) {
        if (handler.gameObjects.size() > 4) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {

                    handler.gameObjects.get(4).setVelX(0);
                }
                break;

                case MotionEvent.ACTION_MOVE: {
                    handler.gameObjects.get(4).setX((int) event.getX());
                    handler.gameObjects.get(4).setY((int) event.getY());

                    invalidate();
                }
                break;
                case MotionEvent.ACTION_UP: {
                    int origin = handler.gameObjects.get(4).getOriginalPosition();
                    handler.gameObjects.get(4).setX(origin);
                    handler.gameObjects.get(4).setY(1150);
                    handler.gameObjects.get(4).setVelX(MOVESPEED);

                    invalidate();
                }
                break;
            }
        }
        return true;
    }


    public void update(){
        cb.update();

        // update game objects
        handler.update();

    }

    public void remove(){
        handler.remove();

    }

    public void collision(){
        handler.collision();
        if (handler.isCollision()){
            MOVESPEED --;
            handler.setCollision(false);
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

//            // draw the game objects
            handler.draw(canvas);


            canvas.restoreToCount(savedState);
        }

    }

    public void render(Canvas canvas){


    }


}

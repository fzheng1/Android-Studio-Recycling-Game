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
    public static final int MOVESPEED = -5;

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
    private Water recyclable;
    private Water wb;
    private Juice jb;
    private Random random;


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
        recyclingBin = new RecyclingBin(590, 750, ID.recycleBin, BitmapFactory.decodeResource(getResources(), R.drawable.recycle_bin));
        trashBin = new TrashBin(330, 750, ID.trashBin, BitmapFactory.decodeResource(getResources(), R.drawable.trash_bin));
        compostBin = new CompostBin(320, 500, ID.compostBin, BitmapFactory.decodeResource(getResources(), R.drawable.compost_bin));
        paperBin = new PaperBin(590, 500, ID.paperBin, BitmapFactory.decodeResource(getResources(), R.drawable.paper_bin));

        // add objects to handler
        handler.addObject(recyclingBin);
        handler.addObject(trashBin);
        handler.addObject(compostBin);
        handler.addObject(paperBin);



        // Initialize interactable objects under here
        // water bottle
        wb = new Water(WIDTH, 1150, null, BitmapFactory.decodeResource(getResources(), R.drawable.water));
        wb.setVector(MOVESPEED);

        handler.addObject(wb);

        //juice box
        jb = new Juice(WIDTH + 500, 1150, null, BitmapFactory.decodeResource(getResources(), R.drawable.juice));
        jb.setVector(MOVESPEED);

        handler.addObject(jb);


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
    }

    public void remove(){
        handler.remove();
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

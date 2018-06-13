package com.strobertchs.enviro_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

// this class is the thread on which the game is run
// sets up game clock
public class MainThread extends Thread
{
    // sets the frames/second
    private int FPS = 30;

    private double averageFPS;

    // gets a box to draw in
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    // game loop
    @Override
    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        long targetTime = 1000/FPS;
        int loops = 0;

        // what the game does while running
        while(running) {

            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.remove();
                    this.gamePanel.collision();

                    // update the objects
                    this.gamePanel.update();

                    this.gamePanel.draw(canvas);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // check if there is a canvas to draw on
            finally{
                if(canvas!=null)
                {
                    try {
                        // displays old contents before update and then removes them
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            // time passed in milliseconds
            timeMillis = (System.nanoTime() - startTime) / 1000000;

            // difference between target time for a loop and actual time passed for this loop
            waitTime = targetTime-timeMillis;

            // make game wait until target time is reached
            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            // calculate time elapsed for the loop
            totalTime += System.nanoTime()-startTime;

            // each update makes a new frame
            frameCount++;

            // print out the avg framecount
            if(frameCount == FPS)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                System.out.println("FPS: " + averageFPS);
            }
            loops++;
        }
    }

    // sets whether the game is running or not
    public void setRunning(boolean b)
    {
        running = b;
    }

}

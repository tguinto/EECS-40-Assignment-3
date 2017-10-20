package tguinto.eecs40assingment3;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import tguinto.eecs40assingment3.GameView;
import android.view.KeyEvent;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import static android.view.KeyEvent.ACTION_DOWN;


/**
 * Created by peizhaoo on 5/17/17.
 */
public class GameThread extends Thread {
    private GameController controller;
    private GameView gameView;

    public GameThread(GameController ct, GameView gv) {
        controller = ct;
        gameView = gv;

    }

    public void run() {
        System.out.println("GameThread run start!");
        SurfaceHolder sh;
        Canvas canvas;

        while (true) {
            sh = gameView.getHolder();
            canvas = sh.lockCanvas();
            if (canvas != null) {
               // controller.update();
                if(gameView.GameOverUpdate() ==false) {
                    gameView.draw(canvas); //draws game if DigDug is alive
                }else if(gameView.GameOverUpdate()){
                    gameView.drawGameOver(canvas); //draws gameover screen if DigDug is dead
                }
                if(gameView.GameWinUpdate()){
                    gameView.drawWin(canvas); //draws win screen if all monsters are dead
                }
                sh.unlockCanvasAndPost(canvas);
                gameView.RockUpdate(); //updates rock location on canvas
                gameView.attackingUpdate(); //updates attaching location on canvas




                try{
                    sleep(250); //time lag
                } catch(InterruptedException e){
                    System.out.println("Error occured");
                }
            }
        }
    }
}

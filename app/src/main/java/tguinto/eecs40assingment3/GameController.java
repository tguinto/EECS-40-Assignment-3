package tguinto.eecs40assingment3;

import android.view.KeyEvent;

/**
 * Created by Tristan on 5/19/2017.
 */

public class GameController {
    private GameView gameView;
    private DigDug digDug;
    private Monster[] monsters;
    private Rock[] rocks;
    private GameMap map;

    private GameThread gameThread;

    public GameController() {
        gameThread = new GameThread(this, gameView);
        gameThread.start();
    }

    public void processInput(KeyEvent event) {



        if (gameView.UserInputEventRight(event)) {
            digDug.moveRight();
        }
        else if (gameView.UserInputEventLeft(event)) {
            digDug.moveLeft();
        }
        else if (gameView.UserInputEventUp(event)) {
            digDug.moveUp();
        }
        else if (gameView.UserInputEventDown(event)) {
            digDug.moveDown();
        }
        // if (attack) {
        //    digDug.attack();
        // }
    }

    public void update() {
        //for (int i = 0; i < monsters.length; i++)
            //monsters[i].attack();
            System.out.println("Monster attack");
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i].shouldFall(map.map[rocks[i].rowPos + 1][rocks[i].colPos])){
                rocks[i].fall();
                map.map[rocks[i].rowPos][rocks[i].colPos] = 4;
            }
            System.out.println("Rock Fall");
        }
    }
}

package tguinto.eecs40assingment3;

/**
 * Created by Tristan on 5/23/2017.
 */

public class GameThread2 extends Thread {
    private GameView gameView;
    private Monster[] monsters;
    private GameMap gameMap;

    public GameThread2(GameView v,GameMap m, Monster[] mon){
        gameView = v;
        gameMap = m;
        monsters = mon;
    }
    public void run(){
        System.out.println("Game thread 2 start!");
        while(true){
            gameView.MonsterUpdate();//updates monsters on convas
            try{
                sleep(1000); //time lag
            } catch(InterruptedException e){
                System.out.println("Error occured");
            }
            gameView.FireBallErase(); //Checks for any fire to erase

        }
    }
}

package tguinto.eecs40assingment3;

/**
 * Created by Tristan on 5/19/2017.
 */
public class Monster extends MovingGameObject {
    protected boolean alive;
    protected boolean aggression;
    protected int direction;
    protected int type;
    //Moves monsters
    public void moveUp(){
        rowPos--;
    }
    public void moveDown(){
        rowPos++;
    }
    public void moveRight(){
        colPos++;
    }
    public void moveLeft(){
        colPos--;
    }
    public void attack() {

    }
}
package tguinto.eecs40assingment3;

import android.graphics.Bitmap;

/**
 * Created by Tristan on 5/19/2017.
 */
public class DigDug extends MovingGameObject {
    protected boolean alive;
    protected int direction;
    protected int attacking;

    public DigDug(){
        alive = true;
        direction = 0;
        attacking = 0;



    }
    //Moves the position of digdug and changes his direction of attack
    public void moveLeft() {
        colPos--;
        direction = 3;
    }
    public void moveRight() {
        colPos++;
        direction = 1;
    }
    public void moveUp(){
        rowPos--;
        direction = 0; }
    public void moveDown(){
        rowPos++;
        direction = 2;
    }
    // ...

    public void attack(GameMap map) {
        //enables attack if possible
        if(direction == 0 && map.map[rowPos-1][colPos] != 1 && map.map[rowPos-2][colPos]!=1){
            System.out.println("Attacking Up");
            attacking = 1;

        } else if (direction == 1 && map.map[rowPos][colPos+1] != 1&& map.map[rowPos][colPos+2]!=1){
            System.out.println("Attacking Right");
            attacking = 1;

        } else if (direction == 2 && map.map[rowPos+1][colPos] != 1&& map.map[rowPos+2][colPos]!=1){
            System.out.println("Attacking Down");
            attacking = 1;

        } else if (direction == 3 && map.map[rowPos][colPos-1] != 1&& map.map[rowPos][colPos-2]!=1){
            System.out.println("Attacking Left");
            attacking = 1;

        } else {
            attacking = 0;
        }
    }

    public void stopAttack(GameMap map) {
        //enables erasing of attack
        attacking = 2;

    }
}
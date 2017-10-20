package tguinto.eecs40assingment3;

/**
 * Created by Tristan on 5/19/2017.
 */
public class Rock extends MovingGameObject {
    public boolean shouldFall(int check){
        if(check == 0){
            return true;
        }
        return false;
    }
    //moves Rock
    public void fall() {
        rowPos++;
    }
}
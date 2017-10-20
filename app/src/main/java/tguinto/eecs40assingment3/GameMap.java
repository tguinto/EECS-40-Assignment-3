package tguinto.eecs40assingment3;
import java.util.*;
/**
 * Created by Tristan on 5/19/2017.
 */
public class GameMap {
    //creates array
    int[][] map = new int[17][17];


    public GameMap(){
        //sets black space on top
        for (int i = 0 ; i <= 16; ++i){
            for( int j = 0; j <= 3; ++j){
                map[j][i] = 0;
            }
        }
        //sets sand
        for (int i = 0 ; i <= 16; ++i){
            for( int j = 4; j <= 16; ++j){
                map[j][i] = 1;
            }
        }
        //sets DigDug
        map[10][8] = 2;
        //sets Monsters and their space
        map[8   ][3] = 3; //monster[0]
        map[8][2] = 0;
        map[8][4] = 0;
        map[7][11] = 3; //monster[1]
        map[6][11] = 0;
        map[8][11] = 0;
        map[14][3] = 3; //monster[2]
        map[13][3] = 0;
        map[12][3] = 0;
        map[13][13] = 5;//sets Fire Monster
        map[13][12] = 0;
        map[13][14] = 0;
        map[5][4] = 4; //Sets rocks
        map[6][13] = 4;
        map[4][9] = 4;
    }

}
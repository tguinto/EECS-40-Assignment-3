package tguinto.eecs40assingment3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.inputmethodservice.Keyboard;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.InputEvent;

import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.ACTION_UP;
import static android.view.KeyEvent.KEYCODE_DPAD_DOWN;
import static android.view.KeyEvent.KEYCODE_DPAD_LEFT;
import static android.view.KeyEvent.KEYCODE_DPAD_RIGHT;
import static android.view.KeyEvent.KEYCODE_DPAD_UP;
import static android.view.KeyEvent.KEYCODE_SPACE;


/**
 * Created by Tristan on 5/19/2017.
 */

public class GameView extends SurfaceView  implements SurfaceHolder.Callback {
    private DigDug digDug;
    private Paint paint;
    private Monster[] monsters = new Monster[4];
    private Rock rocks[] = new Rock[3];
    private GameMap gameMap;
    private Bitmap Icons[];
    private GameController controller;


    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        setFocusable(true);
        paint = new Paint();
        gameMap = new GameMap();
        paint.setColor(Color.WHITE);
        paint.setTextSize(225);
        paint.setTextAlign(Paint.Align.CENTER);
        Icons =  new Bitmap[13];
        for (int i = 0 ; i <= 16; ++i) {
            for (int j = 0; j <= 16; ++j) {
                System.out.print(gameMap.map[i][j] + " ");
            }
            System.out.print("\n");
        }

        //initializes DigDug
        digDug = new DigDug();
        digDug.rowPos = 10;
        digDug.colPos = 8;
        digDug.direction = 0;
        digDug.attacking = 0;
        digDug.alive = true;
        //initializes rocks
        rocks[0] = new Rock();
        rocks[0].colPos = 4;
        rocks[0].rowPos = 5;
        rocks[1] = new Rock();
        rocks[1].colPos = 13;
        rocks[1].rowPos = 6;
        rocks[2] = new Rock();
        rocks[2].colPos = 9;
        rocks[2].rowPos = 4;
        //initializes monsters
        monsters[0] = new Monster();
        monsters[0].colPos = 3;
        monsters[0].rowPos = 8;
        monsters[0].type = 3;
        monsters[0].alive = true;
        monsters[0].direction = 3;
        monsters[0].aggression = false;
        monsters[1] = new Monster();
        monsters[1].colPos = 11;
        monsters[1].rowPos = 7;
        monsters[1].type = 3;
        monsters[1].alive = true;
        monsters[1].direction = 0;
        monsters[1].aggression = false;
        monsters[2] = new Monster();
        monsters[2].colPos = 3;
        monsters[2].rowPos = 14;
        monsters[2].type = 3;
        monsters[2].alive = true;
        monsters[2].direction = 2;
        monsters[2].aggression = false;
        monsters[3] = new FireMonster();
        monsters[3].colPos = 13;
        monsters[3].rowPos = 13;
        monsters[3].type = 5;
        monsters[3].alive = true;
        monsters[3].direction = 1;

        //controller = new GameController();



    }

    private void drawDigDug(Canvas canvas) {
        // Draw DigDug
        //System.out.println("Draw Dig Dug Called!");
        int width = getWidth();
        int height = getHeight();
        int pixelw = width/16;
        int pixelh = height/16;
        Rect rect2 = new Rect();
        //Draws DigDug on canvas where his column position and row position are
        rect2.set(digDug.colPos*pixelw, digDug.rowPos*pixelh, (digDug.colPos+1)*pixelw, (digDug.rowPos+1)*pixelh);
        canvas.drawBitmap(Icons[2],null,rect2,null);
        if(digDug.direction == 0){
            canvas.drawBitmap(Icons[2],null,rect2,null);
        } else if (digDug.direction == 1) {
            canvas.drawBitmap(Icons[6], null, rect2, null);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        //System.out.println("Draw Called!");
        super.draw(canvas);
        // Draw according to the game objects
        canvas.drawColor(Color.BLACK);
        Rect rect1 = new Rect();
        int width = getWidth();
        int height = getHeight();
        //creates the width and height of each box that holds an icon
        int pixelw = width/16;
        int pixelh = height/16;

        //Scans game map array and matches the values with the correct bitmap icons
        for (int i = 0 ; i <= 16; ++i) {
            for (int j = 0; j <= 16; ++j) {
                rect1.set(j*pixelw, i*pixelh,(j+1)*pixelw , (i+1)*pixelh);
                if (gameMap.map[i][j] == 0){
                    canvas.drawBitmap(Icons[4],null, rect1,null);
                }
                else if (gameMap.map[i][j] == 1){
                    canvas.drawBitmap(Icons[0],null, rect1,null);
                }
                else if (gameMap.map[i][j] == 2){
                    drawDigDug(canvas);
                }
                else if (gameMap.map[i][j] == 3){
                    canvas.drawBitmap(Icons[1],null, rect1,null);
                }
                else if (gameMap.map[i][j] == 4){
                    canvas.drawBitmap(Icons[3],null, rect1,null);
                }
                else if (gameMap.map[i][j] == 5){
                    canvas.drawBitmap(Icons[7],null, rect1,null);
                }
                else if (gameMap.map[i][j] == 6){
                    canvas.drawBitmap(Icons[5],null,rect1,null);
                }
                else if (gameMap.map[i][j] == 7){
                    canvas.drawBitmap(Icons[8],null,rect1,null);
                }
                else if (gameMap.map[i][j] == 8){
                    canvas.drawBitmap(Icons[9],null,rect1,null);
                }
                else if (gameMap.map[i][j] == 9){
                    canvas.drawBitmap(Icons[10],null,rect1,null);
                }

            }
            //titles game on top
            canvas.drawText("GIT GUD",width/2,190, paint);
        }
    }
    public void drawGameOver(Canvas canvas) { //Draws Game Over Screen when prompted to
            System.out.println("Game over Screen called!");
            super.draw(canvas);
            canvas.drawColor(Color.BLACK);
            Rect rect = new Rect();
            rect.set(0,0,getWidth(),getHeight());
            canvas.drawBitmap(Icons[11],null,rect,null);


    }
    public void drawWin(Canvas canvas) { //Draws Win Screen when prompted to
        System.out.println("Win Screen called!");
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);
        Rect rect = new Rect();
        rect.set(0,0,getWidth(),getHeight());
        canvas.drawBitmap(Icons[12],null,rect,null);


    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("Surface Created!");


        Bitmap Sand = BitmapFactory.decodeResource(getResources(),R.drawable.sand); //Sand image
        Bitmap Gengar = BitmapFactory.decodeResource(getResources(),R.drawable.gengar); //Monster image
        Bitmap MCl = BitmapFactory.decodeResource(getResources(),R.drawable.mcl); //DigDug facing left
        Bitmap MCr = BitmapFactory.decodeResource(getResources(),R.drawable.mcr); //DigDug facing right
        Bitmap Rock = BitmapFactory.decodeResource(getResources(),R.drawable.rock); //Rock image
        Bitmap Black = BitmapFactory.decodeResource(getResources(),R.drawable.black); //Blank Spot image
        Bitmap Ghastly = BitmapFactory.decodeResource(getResources(),R.drawable.ghastly); //Monster traveling through sand
        Bitmap Dragon =  BitmapFactory.decodeResource(getResources(),R.drawable.dragon); //Monster that can shoot fire
        Bitmap Fire = BitmapFactory.decodeResource(getResources(),R.drawable.fireball); //Fire projectile
        Bitmap LineH = BitmapFactory.decodeResource(getResources(),R.drawable.lineh); //Horizontal line
        Bitmap LineV = BitmapFactory.decodeResource(getResources(),R.drawable.linev); //Veritical line
        Bitmap GameOver = BitmapFactory.decodeResource(getResources(),R.drawable.gameover); //Gameover Scren
        Bitmap Win = BitmapFactory.decodeResource(getResources(),R.drawable.win); //Win Screen
        Icons[0] = Sand;
        Icons[1] = Gengar;
        Icons[2] = MCl;
        Icons[3] = Rock;
        Icons[4] = Black;
        Icons[5] = Ghastly;
        Icons[6] = MCr;
        Icons[7] = Dragon;
        Icons[8] = Fire;
        Icons[9] = LineH;
        Icons[10] = LineV;
        Icons[11] = GameOver;
        Icons[12] = Win;
        //starts threads on creation of surface
        GameThread2 gameThread2 = new GameThread2(this,gameMap,monsters);
        GameThread gameThread = new GameThread(controller, this);

        gameThread.start();
        gameThread2.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        //recognizes space key for attack
        //press once to activate
        //press again to deactivate
        if(keyCode == KEYCODE_SPACE && (digDug.attacking == 2||digDug.attacking == 0)) {
            System.out.println("Attacking now");
            digDug.attack(gameMap);
            return true;
        } else if( keyCode == KEYCODE_SPACE && digDug.attacking ==1){
            System.out.println("Stopping Attack");
            digDug.stopAttack(gameMap);
            return true;
        }



        return super.onKeyDown(keyCode,event);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        System.out.println("onKeyUp Called!");
        //recongnizes movement for DigDug
        int up = KEYCODE_DPAD_UP;
        int down = KEYCODE_DPAD_DOWN;
        int right = KEYCODE_DPAD_RIGHT;
        int left  = KEYCODE_DPAD_LEFT;
        int space =  KEYCODE_SPACE;
        //Prints number array of map to check that what is shown is what matches the map
        for (int i = 0 ; i <= 16; ++i) {
            for (int j = 0; j <= 16; ++j) {
                System.out.print(gameMap.map[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("digdug.rowPos: " + digDug.rowPos + " digdug.colPos: " + digDug.colPos);
        if(digDug.alive&& digDug.attacking !=1) { //can't move if DigDug is dead or attacking
            //Checks the keycode of the event and matches it to the correct direction function
            if (keyCode == up) {
                UserInputEventUp(event);
                return true;
            } else if (keyCode == down) {
                UserInputEventDown(event);
                return true;
            } else if (keyCode == left) {
                UserInputEventLeft(event);
                return true;
            } else if (keyCode == right) {
                UserInputEventRight(event);
                return true;
            }
        }

        //else returns to the same function if a wrong input is made
        return super.onKeyUp(keyCode,event);
    }

    public boolean UserInputEventUp(KeyEvent event) {
        int up = KEYCODE_DPAD_UP;
        int prevX, prevY;
            if (event.getAction() == ACTION_UP){

                    prevX = digDug.rowPos;
                    prevY = digDug.colPos;

                    if(gameMap.map[digDug.rowPos-1][digDug.colPos] != 4 && gameMap.map[digDug.rowPos-1][digDug.colPos] != 3 && gameMap.map[digDug.rowPos-1][digDug.colPos] != 5 && gameMap.map[digDug.rowPos-1][digDug.colPos] != 6) {
                        digDug.moveUp();
                        //Changes the value in the game map array to the value of DigDug for draw function
                        gameMap.map[digDug.rowPos][digDug.colPos] = 2;
                        System.out.println("Up Inputted!");
                        //Changes the value in the game map array to the value of a blank space
                        gameMap.map[prevX][prevY] = 0;
                        System.out.println("digDug.rowPos is: " + digDug.rowPos + " digDug.colPos is: " + digDug.colPos);
                        System.out.println("prevX is: " + prevX + " prevY is: " + prevY);
                        return true;
                    } else {
                        return false;
                    }
            }

        return false;
    }

    public boolean UserInputEventDown(KeyEvent event) {
        int down = KEYCODE_DPAD_DOWN;
        int prevX, prevY;

        if (event.getAction() == ACTION_UP){

            prevX = digDug.rowPos;
            prevY = digDug.colPos;
            if(gameMap.map[digDug.rowPos+1][digDug.colPos] != 4 && gameMap.map[digDug.rowPos+1][digDug.colPos] != 3 && gameMap.map[digDug.rowPos+1][digDug.colPos] != 5 && gameMap.map[digDug.rowPos+1][digDug.colPos] != 6) {
                digDug.moveDown();
                gameMap.map[digDug.rowPos][digDug.colPos] = 2;
                System.out.println("Down Inputted!");
                gameMap.map[prevX][prevY] = 0;
                System.out.println("digDug.rowPos is: " + digDug.rowPos + " digDug.colPos is: " + digDug.colPos);
                System.out.println("prevX is: " + prevX + " prevY is: " + prevY);
                return true;
            } else{
                return false;
            }
        }

        return false;
    }

    public boolean UserInputEventRight(KeyEvent event) {
        int right = KEYCODE_DPAD_RIGHT;
        int prevX, prevY;
        if (event.getAction() == ACTION_UP){

            prevX = digDug.rowPos;
            prevY = digDug.colPos;
            if(gameMap.map[digDug.rowPos][digDug.colPos+1] != 4 && gameMap.map[digDug.rowPos][digDug.colPos+1] != 3 && gameMap.map[digDug.rowPos][digDug.colPos+1] != 5 && gameMap.map[digDug.rowPos][digDug.colPos+1] != 6) {
                digDug.moveRight();
                gameMap.map[digDug.rowPos][digDug.colPos] = 2;
                System.out.println("Right Inputted!");
                gameMap.map[prevX][prevY] = 0;
                System.out.println("digDug.rowPos is: " + digDug.rowPos + " digDug.colPos is: " + digDug.colPos);
                System.out.println("prevX is: " + prevX + " prevY is: " + prevY);
                return true;
            } else{
                return false;
            }
        }

        return false;
    }

    public boolean UserInputEventLeft(KeyEvent event) {
        int left = KEYCODE_DPAD_LEFT;
        int prevX, prevY;
        if (event.getAction() == ACTION_UP){

            prevX = digDug.rowPos;
            prevY = digDug.colPos;
            if(gameMap.map[digDug.rowPos][digDug.colPos-1] != 4 && gameMap.map[digDug.rowPos][digDug.colPos-1] != 3 && gameMap.map[digDug.rowPos][digDug.colPos-1] != 5 && gameMap.map[digDug.rowPos][digDug.colPos-1] != 6) {
                digDug.moveLeft();
                gameMap.map[digDug.rowPos][digDug.colPos] = 2;
                System.out.println("Left Inputted!");
                gameMap.map[prevX][prevY] = 0;
                System.out.println("digDug.rowPos is: " + digDug.rowPos + " digDug.colPos is: " + digDug.colPos);
                System.out.println("prevX is: " + prevX + " prevY is: " + prevY);
                return true;
            } else{
                return false;
            }
        }

        return false;
    }

    public boolean  UserInputEventSpace(KeyEvent event) {
        int space = KEYCODE_SPACE;

            System.out.println("Key pressed!");
            int uInput = event.getKeyCode();
            //digDug.attack(gameMap);
            if (event.getAction() == ACTION_UP){
                if(uInput == space){
                    System.out.println("Space Inputted!");
                    digDug.stopAttack(gameMap);
                    return true;
                }
            }

        return false;
    }

    public void attackingUpdate(){ //Checks if player is attacking
        if(digDug.attacking == 1) {
            System.out.println("Attacking!");
            if( digDug.direction == 0){ //up direction
                if(gameMap.map[digDug.rowPos-1][digDug.colPos] == 3||gameMap.map[digDug.rowPos-2][digDug.colPos] == 3||gameMap.map[digDug.rowPos-1][digDug.colPos] == 5||gameMap.map[digDug.rowPos-2][digDug.colPos] == 5){
                    for(int i = 0; i< monsters.length;i++){
                        if((monsters[i].rowPos == digDug.rowPos-1 && monsters[i].colPos == digDug.colPos)||(monsters[i].rowPos == digDug.rowPos-2 && monsters[i].colPos == digDug.colPos)){
                            monsters[i].alive = false;
                        }
                    }
                }
                gameMap.map[digDug.rowPos-1][digDug.colPos] = 9; //creates vertical line in gameMap array
                gameMap.map[digDug.rowPos-2][digDug.colPos] = 9;
            } else if( digDug.direction == 1){ //right direction
                if(gameMap.map[digDug.rowPos][digDug.colPos+1] == 3||gameMap.map[digDug.rowPos][digDug.colPos+2] == 3||gameMap.map[digDug.rowPos][digDug.colPos+1] == 5||gameMap.map[digDug.rowPos][digDug.colPos+2] == 5){
                    for(int i = 0; i< monsters.length;i++){
                        if((monsters[i].rowPos == digDug.rowPos && monsters[i].colPos == digDug.colPos+1)||(monsters[i].rowPos == digDug.rowPos && monsters[i].colPos == digDug.colPos+2)){
                            monsters[i].alive = false;
                        }
                    }
                }
                gameMap.map[digDug.rowPos][digDug.colPos+1] = 8; //creates horizontal line in gameMap array
                gameMap.map[digDug.rowPos][digDug.colPos+2] = 8;
            } else if( digDug.direction == 2){ //down direction
                if(gameMap.map[digDug.rowPos+1][digDug.colPos] == 3||gameMap.map[digDug.rowPos+2][digDug.colPos] == 3||gameMap.map[digDug.rowPos+1][digDug.colPos] == 5||gameMap.map[digDug.rowPos+2][digDug.colPos] == 5){
                    for(int i = 0; i< monsters.length;i++){
                        if((monsters[i].rowPos == digDug.rowPos+1 && monsters[i].colPos == digDug.colPos)||(monsters[i].rowPos == digDug.rowPos+2 && monsters[i].colPos == digDug.colPos)){
                            monsters[i].alive = false;
                        }
                    }
                }
                gameMap.map[digDug.rowPos+1][digDug.colPos] = 9;
                gameMap.map[digDug.rowPos+2][digDug.colPos] = 9;
            } else if( digDug.direction == 3){ //left direction
                if(gameMap.map[digDug.rowPos][digDug.colPos-1] == 3||gameMap.map[digDug.rowPos][digDug.colPos-2] == 3||gameMap.map[digDug.rowPos][digDug.colPos-1] == 5||gameMap.map[digDug.rowPos][digDug.colPos-2] == 5){
                    for(int i = 0; i< monsters.length;i++){
                        if((monsters[i].rowPos == digDug.rowPos && monsters[i].colPos == digDug.colPos-1)||(monsters[i].rowPos == digDug.rowPos && monsters[i].colPos == digDug.colPos-2)){
                            monsters[i].alive = false;
                        }
                    }
                }
                gameMap.map[digDug.rowPos][digDug.colPos-2] = 8;
                gameMap.map[digDug.rowPos][digDug.colPos-1] = 8;
            }


        } else if((digDug.attacking) == 2) {//checks if attack is finished
            if (digDug.direction == 0) {
                gameMap.map[digDug.rowPos - 1][digDug.colPos] = 0; //recreates black space where line was
                gameMap.map[digDug.rowPos - 2][digDug.colPos] = 0;
                digDug.attacking = 0;
            } else if (digDug.direction == 1) {
                gameMap.map[digDug.rowPos][digDug.colPos + 1] = 0;
                gameMap.map[digDug.rowPos][digDug.colPos + 2] = 0;
                digDug.attacking = 0;
            } else if (digDug.direction == 2) {
                gameMap.map[digDug.rowPos + 1][digDug.colPos] = 0;
                gameMap.map[digDug.rowPos + 2][digDug.colPos] = 0;
                digDug.attacking = 0;
            } else if (digDug.direction == 3) {
                gameMap.map[digDug.rowPos][digDug.colPos - 2] = 0;
                gameMap.map[digDug.rowPos][digDug.colPos - 1] = 0;
                digDug.attacking = 0;
            }
        }


    }
    public boolean GameOverUpdate(){
        if (digDug.alive == false){//checks if DigDug is alive
            return true;
        }

        return false;
    }
    public boolean GameWinUpdate(){
        int check = 0;
        for(int i = 0; i < monsters.length; i++){
            if(monsters[i].alive == false){ //checks if any monsters are alive
                check++;
            }
        }
        if(check == 4){
            return true;
        }
        return false;
    }
    public void RockUpdate(){
        int rockx, rocky;
        if(gameMap.map[digDug.rowPos-1][digDug.colPos] == 4){
            System.out.println("Rock above");
        }
        for(int i = 0; i < rocks.length; i++){
            rocky = rocks[i].rowPos;
            rockx = rocks[i].colPos;
            if (gameMap.map[rocky+1][rockx] != 1){ //checks below rock if there is sand
                System.out.println("Rock should fall");
                rocks[i].fall();
                gameMap.map[rocky][rockx] = 0; //leaves trail of rock as black
                if(gameMap.map[rocks[i].rowPos][rocks[i].colPos] == 2){
                    digDug.alive = false;
                }
                gameMap.map[rocks[i].rowPos][rocks[i].colPos] = 4; //moves rock through gameMap array
            }

        }
    }
    public void GhostMove(Monster monster){ //moves monsters without creating black trail
        int oldtype = monster.type;
        monster.icon = Icons[5];
        monster.type = 6;
        int monX, monY;
        monX = monster.colPos;
        monY = monster.rowPos;
        int targetx, targety;
        targetx = digDug.colPos;
        targety = digDug.rowPos;
        if(monX != targetx || monY != targety) {
            System.out.println("In Ghost form");
            System.out.println("ghost row: " + monster.rowPos + " ghost col: " + monster.colPos);
            if (monY > targety) {
                monster.direction = 0;
                if (monster.direction == 0) { //if monster is facing up
                    if (gameMap.map[monY - 1][monX] == 1) {
                        monster.moveUp();
                        if (gameMap.map[monY][monX] == 4 || gameMap.map[monY][monX] == 5) {
                            gameMap.map[monY][monX] = 0;
                        } else {
                            gameMap.map[monY][monX] = 1;
                        }
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                    }
                }
            } else if (monY < targety) {
                monster.direction = 2;
                if (monster.direction == 2) { //if monster is facing down
                    if (gameMap.map[monY + 1][monX] == 1) {
                        monster.moveDown();
                        if (gameMap.map[monY][monX] == 4 || gameMap.map[monY][monX] == 5) {
                            gameMap.map[monY][monX] = 0;
                        } else {
                            gameMap.map[monY][monX] = 1;
                        }
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                    }
                }
            } else if (monX < targetx) {
                monster.direction = 1;
                if (monster.direction == 1) { //if monster is facing right
                    if (gameMap.map[monY][monX + 1] == 1) {
                        monster.moveRight();
                        if (gameMap.map[monY][monX] == 4 || gameMap.map[monY][monX] == 5) {
                            gameMap.map[monY][monX] = 0;
                        } else {
                            gameMap.map[monY][monX] = 1;
                        }
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                    }
                }

            } else if (monX > targetx) {
                monster.direction = 3;
                if (monster.direction == 3) { //if monster is facing left
                    if (gameMap.map[monY][monX - 1] == 1) {
                        monster.moveLeft();
                        if (gameMap.map[monY][monX] == 4 || gameMap.map[monY][monX] == 5) {
                            gameMap.map[monY][monX] = 0;
                        } else {
                            gameMap.map[monY][monX] = 1;
                        }
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                    }
                }

            }
        }
    }

    public void MonsterUpdate(){ //moves monsters according to aggression
        int monX, monY;

        int targetx = digDug.colPos;
        int targety = digDug.rowPos;
        //rowCheck(monsters[0]);
        //columnCheck(monsters[0]);
        for (int i = 0; i < monsters.length; i++){
            if(monsters[i].alive) {
                monX = monsters[i].colPos;
                monY = monsters[i].rowPos;
                if(monsters[i].aggression){
                //System.out.println("monster " + i + " is agressive");
                MonsterAggresive(monsters[i]); //aggresive movement
                if(targetDigDugL(monsters[3])||targetDigDugR(monsters[3])){
                    System.out.println("DigDug targeted");
                    MonsterHold(monsters[3]);
                    FireBallShot();


                }

                } else {
                //System.out.println("monster " + i + " is passive");
                MonsterPassive(monsters[i]); //Passive movement
                monsters[i].aggression = rowCheck(monsters[i]); //checks if DigDug is near to activate aggresion
                monsters[i].aggression = columnCheck(monsters[i]);
                }
            }
        }

    }
    public boolean targetDigDugR(Monster monster) { //checks if DigDug is to the right of monster
        if ((gameMap.map[monster.rowPos][monster.colPos+1] == 2)
                ||
                (gameMap.map[monster.rowPos][monster.colPos+1] == 0&& gameMap.map[monster.rowPos][monster.colPos+2] == 2 )
                ||
                (gameMap.map[monster.rowPos][monster.colPos+1] == 0&& gameMap.map[monster.rowPos][monster.colPos+2] == 0 && monster.rowPos == digDug.rowPos )) {
            System.out.println("Target to right");
            return true;
        }
        return false;
    }
    public boolean targetDigDugL(Monster monster) { //checks if DigDug is to the left of monster
        if ((gameMap.map[monster.rowPos][monster.colPos-1] == 2)||(gameMap.map[monster.rowPos][monster.colPos-1] == 0&& gameMap.map[monster.rowPos][monster.colPos-2] == 2 )) {
            System.out.println("Target to left");
            return true;
        }
        return false;
    }
    public void FireBallShot() { //Creates Fireball for Fire Monster
        int x = monsters[3].colPos;
        int y = monsters[3].rowPos;
        if(monsters[3].direction == 1){
            if(gameMap.map[y][x+1] == 2 || gameMap.map[y][x+1] == 2){
                digDug.alive = false;
            }
            gameMap.map[y][x+1] = 7;
            gameMap.map[y][x+2] = 7;
        } else if(monsters[3].direction == 3){
            if(gameMap.map[y][x-1] == 2 || gameMap.map[y][x-2] == 2){
                digDug.alive = false;
            }
            gameMap.map[y][x-1] = 7;
            gameMap.map[y][x-2] = 7;
        }






    }
    public void FireBallErase(){ //after fireball is finished, checks array for any fire and deletes
        for(int i = 0; i <16; i++){
            for(int j = 0;j<16; j++){
                if(gameMap.map[i][j] == 7){
                    gameMap.map[i][j] = 0;
                }
            }
        }
    }
    public void MonsterHold(Monster monster){ //makes monster hold place
        int holdx = monster.rowPos;
        int holdy = monster.colPos;
        monster.colPos = holdy;
        monster.rowPos = holdx;
    }
    public void MonsterAggresive(Monster monster){ //Aggressive movement, monsters will start to seek out DigDug and use GhostForm
        int monX, monY;
        int oldtype = monster.type;
        int targetx = digDug.colPos;
        int targety = digDug.rowPos;
        monX = monster.colPos;
        monY = monster.rowPos;
        if(monX != targetx || monY != targety) {
            //System.out.println("Monster should move in aggresive");
            if (monY > targety) {
                monster.direction = 0;
                if (monster.direction == 0) { //if monster is facing up
                    if (gameMap.map[monY - 1][monX] == 0) {
                        monster.moveUp();
                        if(gameMap.map[monY][monX] == 6){
                            gameMap.map[monY][monX] = 1;
                        } else {
                            gameMap.map[monY][monX] = 0;
                        }
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                    } else if(gameMap.map[monY-1][monX] == 1||monster.type == 6){
                        GhostMove(monster);
                    } else if(gameMap.map[monY-1][monX] == 2){
                        monster.moveUp();
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                        digDug.alive = false;
                    }

                    monster.type = oldtype;
                }
            }
            else if (monY < targety) {
                monster.direction = 2;
                if (monster.direction == 2) { //if monster is facing down
                    if (gameMap.map[monY + 1][monX] == 0) {
                        monster.moveDown();
                        if(gameMap.map[monY][monX] == 6){
                            gameMap.map[monY][monX] = 1;
                        } else {
                            gameMap.map[monY][monX] = 0;
                        }
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                    } else if(gameMap.map[monY+1][monX] == 1||monster.type == 6){
                        GhostMove(monster);
                    }  else if(gameMap.map[monY+1][monX] == 2){
                        monster.moveDown();
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                        digDug.alive = false;
                    }
                    monster.type = oldtype;
                }
            }
            else if (monX < targetx) {
                monster.direction = 1;
                if (monster.direction == 1) { //if monster is facing right
                    if (gameMap.map[monY][monX + 1] == 0) {
                        monster.moveRight();
                        if(gameMap.map[monY][monX] == 6){
                            gameMap.map[monY][monX] = 1;
                        } else {
                            gameMap.map[monY][monX] = 0;
                        }
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                    } else if(gameMap.map[monY][monX+1] == 1||monster.type == 6){
                        GhostMove(monster);
                    } else if(gameMap.map[monY][monX+1] == 2){
                        monster.moveRight();
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                        digDug.alive = false;
                    }
                    monster.type = oldtype;
                }
            }
            else if (monX > targetx) {
                //System.out.println("Monster should move left in aggresive");
                monster.direction = 3;
                if (monster.direction == 3) { //if monster is facing left
                    if (gameMap.map[monY][monX - 1] == 0) {
                        monster.moveLeft();
                        if(gameMap.map[monY][monX] == 6){
                            gameMap.map[monY][monX] = 1;
                        }else {
                            gameMap.map[monY][monX] = 0;
                        }
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                    } else if(gameMap.map[monY][monX-1] == 1||monster.type == 6){
                        GhostMove(monster);
                    } else if(gameMap.map[monY][monX-1] == 2){
                        monster.moveLeft();
                        gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                        digDug.alive = false;
                    }
                    monster.type = oldtype;
                }
            }
        }
    }
    public void MonsterPassive(Monster monster){ //Monsters will roam black area
        int monX, monY;

        monX = monster.colPos;
        monY = monster.rowPos;
        checkoutofbounds(monster);

        if(monster.direction == 0) { //if monster is facing up
            //Checks if up space is open
            if (gameMap.map[monY - 1][monX] == 0) {
                monster.moveUp();
                gameMap.map[monY][monX] = 0;
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;
            } else if(gameMap.map[monY-1][monX] == 2){
                monster.moveUp();
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                digDug.alive = false;
            } else if(gameMap.map[monY][monX+1] == 0){
                monster.direction = 1;
            } else if(gameMap.map[monY+1][monX] == 0){
                monster.direction = 2;
            } else if(gameMap.map[monY][monX-1] == 0){
                monster.direction = 3;
            } else if(gameMap.map[monY-1][monX] == 2){
                monster.moveUp();
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;

            }

        }
//---------------------------------------------------------------------------------------
        if(monster.direction == 2) { //if monster is facing down
            if (gameMap.map[monY + 1][monX] == 0) {
                monster.moveDown();
                gameMap.map[monY][monX] = 0;
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;
            } else if(gameMap.map[monY+1][monX] == 2){
                monster.moveDown();
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                digDug.alive = false;
            } else if(gameMap.map[monY][monX+1] == 0){
                monster.direction = 1;
            } else if(gameMap.map[monY-1][monX] == 0){
                monster.direction = 0;
            } else if(gameMap.map[monY][monX-1] == 0){
                monster.direction = 3;
            }
        }
//---------------------------------------------------------------------------------------
        if(monster.direction == 1) { //if monster is facing right
            if (gameMap.map[monY][monX + 1] == 0) {
                monster.moveRight();
                gameMap.map[monY][monX] = 0;
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;
            } else if(gameMap.map[monY][monX+1] == 2){
                monster.moveRight();
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                digDug.alive = false;
            } else if(gameMap.map[monY+1][monX+1] == 0){
                monster.direction = 2;
            } else if(gameMap.map[monY-1][monX] == 0){
                monster.direction = 0;
            } else if(gameMap.map[monY][monX-1] == 0){
                monster.direction = 3;
            }
        }
//---------------------------------------------------------------------------------------
        if(monster.direction == 3) { //if monster is facing left
            if (gameMap.map[monY][monX - 1] == 0) {
                monster.moveLeft();
                gameMap.map[monY][monX] = 0;
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;
            } else if(gameMap.map[monY][monX-1] == 2){
                monster.moveLeft();
                gameMap.map[monster.rowPos][monster.colPos] = monster.type;
                digDug.alive = false;
            } else if(gameMap.map[monY+1][monX+1] == 0){
                monster.direction = 2;
            } else if(gameMap.map[monY-1][monX] == 0){
                monster.direction = 0;
            } else if(gameMap.map[monY][monX+1] == 0){
                monster.direction = 1;
            }
        }

    }
    public void checkoutofbounds(Monster monster){ //makes sure monsters cannot go out of bounds
        if(monster.rowPos == 15 && monster.direction == 2 ){
            monster.direction = 0;
        }
        if(monster.colPos == 0 && monster.direction == 3){
            monster.direction = 1;
        }
        if(monster.colPos == 15 && monster.direction == 1){
            monster.direction = 3;
        }
    }
    public boolean rowCheck(Monster monster){ //checks for DigDug in consecutive black spaces in a row
        int bot = 15;
        int top = 3;
        int end1 = 0;
        int end2 = 0;


        if(monster.aggression){
            return true;
        }
        for(int i = monster.rowPos+1; i <= bot; i++){
            if(gameMap.map[i][monster.colPos] == 0){
                end1++;
            }
            if(gameMap.map[i][monster.colPos] == 1){
                break;
            }
        }
        //System.out.println("There are " + end1+ " black spaces below");
        for(int j = monster.rowPos-1; j >= top; j--){
            if(gameMap.map[j][monster.colPos] == 0){
                end2++;
            }
            if(gameMap.map[j][monster.colPos] == 1 ){
                break;
            }
        }
        //System.out.println("There are " + end2 + " black spaces above");
        for(int i = monster.rowPos; i <= monster.rowPos + end1; i++){
            if (i == digDug.rowPos){
                //System.out.println("Must chase DigDug to the bot");
                return true;
            }
        }
        for(int j = monster.rowPos; j >= monster.rowPos - end2; j--){
            if (j == digDug.rowPos){
                //System.out.println("Must chase DigDug to the top");
                return true;
            }
        }
        return false;
    }
    public boolean columnCheck(Monster monster){ //checks for DigDug in consecutive black spaces in a column
        int left = 0;
        int right  = 15;
        int end1 = 0;
        int end2 = 0;
        if(monster.aggression){
            return true;
        }
        for(int i = monster.colPos+1; i <= right; i++){
            if(gameMap.map[monster.rowPos][i] == 0){
                end1++;
            }
            if(gameMap.map[monster.rowPos][i] == 1){
                break;
            }
        }
        //System.out.println("There are " + end1+ " black spaces right");
        for(int j = monster.colPos-1; j >= left; j--){
            if(gameMap.map[monster.rowPos][j] == 0){
                end2++;
            }
            if(gameMap.map[monster.rowPos][j] == 1 ){
                break;
            }
        }
        //System.out.println("There are " + end2 + " black spaces left");
        for(int i = monster.colPos; i <= monster.colPos+end1; i++){

            if( i == digDug.colPos){
                //System.out.println("Must chase DigDug to the right");
                return true;
            }
        }
        for(int j = monster.colPos; j >= monster.colPos-end2; j--){
            if(j == digDug.colPos){
                //System.out.println("Must chase DigDug to the left");
                return true;
            }
        }
        return false;
    }

}


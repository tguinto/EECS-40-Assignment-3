package tguinto.eecs40assingment3;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView game = new GameView(this);

        setContentView(game);

        System.out.println("Completed onCreate");
    }
}

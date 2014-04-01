package Minigames.NetworkPipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.fhd.CollegeCrawl.Minigame;

/**
 * Created by Jan on 01/04/14.
 */
public class NetworkPipes extends Minigame{
    Pipe pipe1;

    public NetworkPipes(){
        super();
        pipe1 = new Pipe('v');
    }

    @Override
    public void run() {
        super.run();

        batch.begin();

        //Spielfeld zeichnen
        pipe1.draw(batch);

        batch.end();

        input();
    }

    private void input(){
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_8)){

            pipe1.rotate90(true);
        }
    }
}

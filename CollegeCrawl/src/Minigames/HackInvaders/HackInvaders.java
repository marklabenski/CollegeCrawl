package Minigames.HackInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fhd.CollegeCrawl.Minigame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Flo on 28.03.14.
 */
public class HackInvaders extends Minigame {
    Defender defender;
    ArrayList<Shot> shots;

    public HackInvaders() {
        super();
        defender = new Defender();
        shots = new ArrayList<Shot>();
    }

    @Override
    public void run() {
        super.run();

        batch.begin();

        //Spieler zeichnen
        defender.draw(batch);

        //Schüsse zeichnen
        for(Shot shot : shots) {
            shot.draw(batch);
            shot.move();
        }

        batch.end();

        input();
    }

    private void shot(float x, float y) {
        shots.add(new Shot(x, y));
    }

    /**
     * Verarbeitet alle Benutzereingaben
     */
    private void input() {
        //Hacker bewegen (links/rechts)
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            defender.goRight();
            shot(defender.getX()+defender.getWidth()/2, defender.getHeight());

        }else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            defender.goLeft();
            shot(defender.getX()+defender.getWidth()/2, defender.getHeight());
        }

    }
}

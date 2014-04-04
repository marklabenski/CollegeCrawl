package Minigames.HackInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fhd.CollegeCrawl.Minigame;
import com.fhd.CollegeCrawl.Timer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Flo on 28.03.14.
 */
public class HackInvaders extends Minigame {

    //some statics
    public static int waitBetweenShots = 20;
    public static int waitBeforeNewShot = 100;

    Defender defender;
    ArrayList<Shot> shots;
    ArrayList<Invader> invaders;
    float invaderSpeed = 0.7f;

    Timer spawnTimer;

    public HackInvaders() {
        super();
        defender = new Defender();
        shots = new ArrayList<Shot>();
        invaders = new ArrayList<Invader>();

        spawnTimer = new Timer();
    }

    @Override
    public void run() {
        super.run();

        batch.begin();

        //Schüsse zeichnen
        for(Shot shot : shots) {
            if(shot.isActive()) {
                shot.draw(batch);
                shot.move();
            }
        }
        //Invadder zeichnen
        for(Invader invader : invaders){
            if(invader.isActive()){
                invader.draw(batch);
                invader.move(this.invaderSpeed);
            }
        }

        //Spieler zeichnen
        defender.draw(batch);

        batch.end();

        //Schüsse + Invader auf Kolision prüfen
        for(Shot shot : shots){
            if(shot.isActive()){
                for(Invader invader : invaders){
                    if(shot.getBoundingRectangle().overlaps(invader.getBoundingRectangle())){
                        invader.destroy();
                    }
                }
            }
        }


        //Schnelligkeit von Invadern erhöhen
        this.invaderSpeed += 0.002f;

        //neuen Invader erstellen
        if(spawnTimer.done()) {
            invaders.add(new Invader(this.invaderSpeed));
            spawnTimer.wait(500);
        }

        //Eingaben verarbeiten
        input();
    }

    /**
     * Neuen Schuss absetzen
     * @param x Startpunkt x Koordinate
     * @param y Startpunkt Y Koordinate
     */
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
            if(defender.canShot()){
                shot(defender.getX()+defender.getWidth()/2, defender.getHeight());
            }
        }else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            defender.goLeft();
            if(defender.canShot()){
                shot(defender.getX()+defender.getWidth()/2, defender.getHeight());
            }
        }

    }
}

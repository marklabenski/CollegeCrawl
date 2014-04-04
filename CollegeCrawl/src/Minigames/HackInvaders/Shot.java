package Minigames.HackInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fhd.CollegeCrawl.Timer;

/**
 * Created by Flo on 30.03.14.
 */
public class Shot extends Sprite {

    private boolean active = true;
    private Timer moveShot;

    Shot(float x, float y) {
        super(new Texture("content/1.png"));
        this.setBounds(x, Gdx.graphics.getHeight()-y, 10, 10);
        moveShot = new Timer();
    }


    /**
     * Schuss bewegen (verschieben)
     */
    public void move() {
        if(moveShot.done()) {
            this.translateY(-20);
            if ((this.getY() <= 0)) {
                this.active = false;
            }
            moveShot.wait(HackInvaders.waitBetweenShots);
        }
    }

    /**
     * Gibt an, ob der Schuss noch gezeichnet werden soll oder nicht
     * @return boolean
     */
    public boolean isActive() {
        return this.active;
    }

}

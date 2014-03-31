package Minigames.HackInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Flo on 30.03.14.
 */
public class Shot extends Sprite {

    private boolean active = true;

    Shot(float x, float y) {
        super(new Texture("content/1.png"));
        this.setBounds(x, Gdx.graphics.getHeight()-y, 10, 10);
    }


    public void move() {
        this.translateY(-40);
        if((this.getY() <= 0)) {
            this.active = false;
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

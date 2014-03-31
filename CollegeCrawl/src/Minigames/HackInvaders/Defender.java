package Minigames.HackInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Flo on 29.03.14.
 */
public class Defender extends Sprite {

    private float speed = 10;


    Defender() {
        super(new Texture("content/0.png"));
        this.setBounds(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-50, 100, 50);
    }

    /**
     * Bewegt den Defender nach rechts
     */
    public void goRight() {
        if(!(this.getX()+this.getWidth() >= Gdx.graphics.getWidth())) {
            this.translateX(speed);
        }
    }

    /**
     * Bewegt den Defender nach links
     */
    public void goLeft() {
        if(!(this.getX() <= 0)) {
            this.translateX(-speed);
        }
    }
}

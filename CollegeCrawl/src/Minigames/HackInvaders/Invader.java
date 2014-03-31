package Minigames.HackInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

/**
 * Created by Flo on 30.03.14.
 */
public class Invader extends Sprite {

    private boolean active = true;
    private float speed;

    Invader(float _speed) {
        super(new Texture("content/2.png"));

        float startX = (new Random().nextFloat()) * Gdx.graphics.getWidth();

        this.setBounds(startX, -50, 50, 50);
        this.speed = _speed;

    }

    public boolean isActive() {
        return active;
    }

    /**
     * Bewegung nach unten
     * @param speed
     */
    public void move(float speed) {
        this.translateY(speed);
    }
}

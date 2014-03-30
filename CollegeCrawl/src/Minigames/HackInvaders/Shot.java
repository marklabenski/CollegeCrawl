package Minigames.HackInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Flo on 30.03.14.
 */
public class Shot extends Sprite {

    Shot(float x, float y) {
        super(new Texture("content/1.png"));
        this.setBounds(x, Gdx.graphics.getHeight()-y, 10, 10);
    }


    public void move() {
        this.translateY(-40);
    }

}

package Minigames.BugHunt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by marklabenski on 24.03.14.
 */
public class Bug extends Sprite{
    int speed = 1;
    Vector2 destPos;
    public boolean destReached = false;

    Bug (Vector2 _startPos, Vector2 _destPos) {
       destPos = _destPos;
       this.setBounds(_startPos.x, _startPos.y, 32, 32);
       this.setTexture(new Texture("content/1.png"));
    }

    public void goToNav()//(float _x, float _y)
    {
        final Vector2 destVec = new Vector2(destPos.x, destPos.y);
        final Vector2 posVec = new Vector2(this.getX(), this.getY());

        if (destVec.dst(posVec) > speed)
        {
            destVec.sub(posVec).nor().mul(speed);
            translate(destVec.x, destVec.y);

        } else
        {
            destVec.sub(posVec);
        }
        translate(destVec.x, destVec.y);

        if(this.getBoundingRectangle().contains(destPos))
            destReached = true;
    }

}

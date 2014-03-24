package Minigames.BugHunt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fhd.CollegeCrawl.MaCamera;
import com.fhd.CollegeCrawl.Minigame;

/**
 * Created by marklabenski on 24.03.14.
 */
public class BugHuntCore extends Minigame{
    Sprite tempSprite;

    public BugHuntCore() {
        super();
        tempSprite = new Sprite(new Texture("content/map.png"));
    }

    @Override
    public void run() {
        super.run();
        tempSprite.setBounds(100,100,200,200);

        batch.begin();

        tempSprite.draw(batch);

        batch.end();
    }
}

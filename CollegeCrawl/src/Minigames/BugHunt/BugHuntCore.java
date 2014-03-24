package Minigames.BugHunt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.fhd.CollegeCrawl.Minigame;
import com.fhd.CollegeCrawl.Timer;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by marklabenski on 24.03.14.
 */
public class BugHuntCore extends Minigame{
    Sprite tempSprite;
    ArrayList<Bug> bugList = new ArrayList<Bug>();

    Timer bugHuntTimer = new Timer();
    int bugSpawnTime = 2000;
    Random rand = new Random();

    public BugHuntCore() {
        super();
        tempSprite = new Sprite(new Texture("content/screen.png"));
        tempSprite.flip(false, true);

    }

    @Override
    public void run() {
        super.run();



        bugSpawnTime -= 1;
        if(bugHuntTimer.done()) {
            int randX = -100 + rand.nextInt(1000);
            int randY = -100 + rand.nextInt(1000);
            bugHuntTimer.wait(bugSpawnTime);
            bugList.add(new Bug(new Vector2(-100, -100), new Vector2(randX,randY)));
        }
        tempSprite.setBounds(0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        batch.begin();

        for(int i = bugList.size()-1; i >= 0 ; i--)
        {
            Bug bug = bugList.get(i);

            if(!bug.destReached) {
                bug.draw(batch);
                bug.goToNav();
            }
        }




        tempSprite.draw(batch);

        batch.end();
    }
}

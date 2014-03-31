package Minigames.FlappyServer;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fhd.CollegeCrawl.Minigame;

public class Flappy extends Minigame{

	public float gravity = 1f;
	public Sprite player;
	public ArrayList<Sprite> walls = new ArrayList<Sprite>();
	public Sprite backgroud; 
	private Random random = new Random();
	private float speed = 4;
	
	public Flappy()
	{
		super();

		backgroud = new Sprite(new Texture("content/screen.png"));
		backgroud.setBounds(0, 0,3000, Gdx.graphics.getHeight());
		player = new Sprite(new Texture("content/0.png"));
		player.setBounds(100,100, 64, 64);
		player.flip(false, true);

		for(int i = 0; i < 100; i++)
		{
			int r = random.nextInt(4);
			
			Sprite wall1 = new Sprite(new Texture("content/2.png"));
			wall1.setBounds(600+(i*400), -300+(r*100), 64, 300);
			walls.add(wall1);

			
			Sprite wall2 = new Sprite(new Texture("content/2.png"));
			wall2.setBounds(600+(i*400), 300+(r*100), 64, 1000);
			walls.add(wall2);
		}
	}

	@Override
	public void run()
	{
		super.run();

		camera.position.x = player.getX();

		batch.begin();///////////////////////////////////////

		backgroud.draw(batch);

		for(Sprite w: walls)
		{
			w.draw(batch);
		}

		player.translateX(speed);
		player.translateY(gravity);
		
		gravity += 0.5f;
		speed += 0.01f;
		
		player.draw(batch);

		batch.end();//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		movementcontrol();
		collision();
		input();
	}

	private void movementcontrol() 
	{
		if(player.getY() < 0)
		{
			player.setY(0);
		}
		if(player.getY() > Gdx.graphics.getHeight()-player.getHeight())
		{
			player.setX(0);
			player.setY(0);
			gravity = 0;
		}
	}

	private void input() 
	{
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			gravity = -10;
		}
		if(Gdx.input.justTouched())
		{
			gravity = -10;
		}
		if(Gdx.input.isKeyPressed(Keys.UP))
		{
			player.translateY(-10);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN))
		{
			player.translateY(+10);
		}
	}

	private void collision() 
	{
		for(Sprite w: walls)
		{
			if(player.getBoundingRectangle().overlaps(w.getBoundingRectangle()))
			{
				player.setX(0);
				player.setY(0);
				gravity = 0;
				speed = 4;
			}
		}
	}

}

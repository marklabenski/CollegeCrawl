package com.fhd.CollegeCrawl;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 *@author Christopherson
 */
public class Animation extends Sprite{

	private ArrayList<Texture> ani = new ArrayList<Texture>();
	private float frame = 0;
	private float speed = 0.02f;

	public Animation(int _x, int _y, String type)
	{
		System.out.println("new Animation: " +type);
	
		//load all frames of the animation
		for (int i = 0; i < 50; i++)
		{
			try
			{
				ani.add(new Texture("content/animations/" + type + "/" +i+".png"));
			}
			catch(Exception e)
			{
				System.out.print("~");
				System.out.println("content/animations/" + type + "/" +i+".png");
			}
		}

		this.set(new Sprite(ani.get(0)));
		this.flip(false, true);
		this.setPosition(_x, _y);
	}


	public void update()
	{
		frame += speed;
		if(frame > ani.size())
		{
			frame = 0;
		}
		this.setTexture(ani.get((int) frame));
	}
}


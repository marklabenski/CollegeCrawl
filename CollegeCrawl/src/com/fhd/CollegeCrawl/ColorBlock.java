package com.fhd.CollegeCrawl;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ColorBlock extends Sprite{

	public int color = 0;
	private Random random = new Random();
	public boolean clear = false;

	public ColorBlock(){
		color = random.nextInt(3);
		this.set(new Sprite(new Texture("content/"+color+".png")));
	}

	public void touched(){

		color++;

		try
		{
			this.setTexture(new Texture("content/"+color+".png"));
		}
		catch(Exception e)
		{
			color = 0;
			this.setTexture(new Texture("content/"+color+".png"));
		}
	}

}

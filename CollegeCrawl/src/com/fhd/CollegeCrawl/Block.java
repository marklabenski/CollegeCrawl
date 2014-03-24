package com.fhd.CollegeCrawl;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block extends Sprite{

	public int color = 0;
	private Random random = new Random();
	public boolean clear = false;

	public Block(){
		color = 0;
		this.set(new Sprite(new Texture("content/"+color+".png")));

	
	}

	public void touched(){

		color++;

		if(color > 1)
		{
			color = 0;
		}

		this.setTexture(new Texture("content/"+color+".png"));
	}

}

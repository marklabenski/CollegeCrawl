package com.fhd.CollegeCrawl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite{

	public float dy = 0;
	private int targetX;
	public boolean ismoving;

	public Player(Texture texture) {
		this.setTexture(texture);
	}

	public void go2steps()
	{
		dy -=10;
		targetX = 100;
	}
	
	public void go3steps()
	{
		dy -=12;
		targetX = 200;
	}
	
	public void update()
	{
		translateY(dy);
		if(targetX > 0){
			ismoving = true;
			
			this.setX(this.getX()+5);
			targetX-=5;
		}
		else {
			setRotation(0);
			ismoving = false;
		}
	}
}

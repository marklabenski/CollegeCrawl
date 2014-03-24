package com.fhd.CollegeCrawl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite{

	private Texture imgleft;
	private Texture imgright;
	private float frame = 0;
	private int maxframes = 5;
	private Texture[] walk_ani_tex = new Texture[maxframes];
	private boolean isLookingLeft ,isLookingRight;
	boolean isWalkingRight;
	boolean isWalkingLeft;
	boolean isWalkingUp;
	boolean isWalkingDown;
	
	//TODO player braucht collision-rectangles
	
	/**
	 *@author Christoph Vogel 
	 */
	public Player (int type)
	{
		for (int i=0; i < maxframes; i++)
		{
			walk_ani_tex[i] = new Texture("content/girl/girl_walk/"+i+".png");
		}

		imgleft = new Texture("content/girl/idle.png");
		imgright = new Texture("content/girl/idle.png");

		this.set(new Sprite(imgleft));
		this.setBounds(300, 300, 64, 64);
		this.flip(true, true);
		isLookingRight = true;

	}

	public void goDown(){
		frame+=0.15;
		isWalkingDown = true;
	}
	public void goUp(){
		frame+=0.15;
		isWalkingUp = true;
	}
	public void goRight(){
		frame+=0.15;
		
		isWalkingRight = true;
		
		if(isLookingLeft){
			this.flip(true, false);
			isLookingLeft = false;
		}
	}
	public void goLeft(){
		frame+=0.15;
		
		isWalkingLeft = true;
		
		if(!isLookingLeft){
			this.flip(true, false);
			isLookingLeft = true;
		}
	}

	public void update()
	{
		System.out.println(isLookingLeft+" / "+isWalkingLeft+" / "+ isWalkingRight);
		
		if(!isWalkingLeft && !isWalkingRight && !isWalkingUp && !isWalkingDown)
		{
			if(isLookingLeft)
			{
				this.setTexture(imgleft);
			} else {
				this.setTexture(imgright);
			}
		} 
		else 
		{
			if(frame >= maxframes)
			{
				frame = 0;
			}
			this.setTexture(walk_ani_tex[(int) frame]);
		}
	}
}

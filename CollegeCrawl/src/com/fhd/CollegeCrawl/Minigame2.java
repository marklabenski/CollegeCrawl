package com.fhd.CollegeCrawl;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Minigame2 extends Minigame
{

	ArrayList<ColorBlock> blocks;
	SpriteBatch batch;
	MaCamera camera;


	public Minigame2() {
		blocks = new ArrayList<ColorBlock>();
		
		batch = new SpriteBatch();
		camera = new MaCamera();
		camera.setToOrtho(true);
		
		makeNewGrid();

	}


	public void run() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		camera.goHereSmoth(blocks.get(0).getX(), blocks.get(0).getY());

		batch.begin();/////////////////////////////////////
		
		for(ColorBlock b : blocks)
		{
			b.draw(batch);

			if(b.clear)
			{
				b.rotate(10);
				b.translateY(-20.0f);
				b.translateX(20.0f);
			}
		}

		batch.end();//\\\\\\\\\\\\\\\\\\\\\\\\\\\

		//check touch inputs
		if(Gdx.input.justTouched())
		{
			for(ColorBlock b : blocks)
			{
				if(b.getBoundingRectangle().contains(Gdx.input.getX(),Gdx.input.getY()))
				{
					b.touched();
				}
			}

			checkWinning();
		}
	}

	private void checkWinning() {

		boolean allorange = true;
		boolean allgreen = true;
		boolean allblue = true;

		for(ColorBlock b : blocks)
		{
			if(!b.clear){
				if(b.color != 0){
					allgreen = false;
				}
				if(b.color != 1){
					allorange = false;
				}
				if(b.color != 2){
					allblue = false;
				}
			}
			System.out.println(b.color+"");
		}

		if(allgreen || allorange || allblue)
		{

			for(ColorBlock b : blocks)
			{
				b.clear = true;
			}
			makeNewGrid();
		}

	}



	private void makeNewGrid() {

		System.out.println("new Grid");
		int y = 0;
		int x = 0;
		for(int i = 0; i < 16; i++) 
		{
			x++;
			if(i%4 == 0)
			{
				y++;
				x=0;
			}
			ColorBlock b = new ColorBlock();
			b.setBounds(100+100*x, 100*y, 64, 64);
			blocks.add(b);
		}
	}

}

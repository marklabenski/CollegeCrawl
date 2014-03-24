package com.fhd.CollegeCrawl;


import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Minigame1 extends Minigame
{

	ArrayList<Block> blocks;
	private boolean gameFinished = false ;
	private Player player;
	private Random random = new Random();
	private float gravity = 0.6f;
	SpriteBatch batch;
	MaCamera camera;
	
	public Minigame1() {
		camera = new MaCamera();
		batch = new SpriteBatch();

		player = new Player(new Texture("content/1.png"));
		player.setBounds(200, 400-64, 64, 64);

		blocks = new ArrayList<Block>();

		makeNewGrid();
	}


	public void run() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		camera.goHereSmoth(player.getX(),player.getY());
		
		batch.begin();///////////////////////////////

		if(player.getY() > 1000){
			player.setY(0);
		}

		player.dy += gravity;
		player.update();
		player.draw(batch);

		for(Block b : blocks)
		{
			//collision with player
			if(player.getBoundingRectangle().overlaps(b.getBoundingRectangle())){
				player.setY(b.getY()-player.getHeight());
				player.dy = 0;

			} 

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
		if(!player.ismoving){
			if(Gdx.input.justTouched()){
				Vector2 touchvec = new Vector2(Gdx.input.getX(),Gdx.input.getY());

				if(touchvec.x < Gdx.graphics.getWidth()/2){
					//go 1 step
					player.go2steps();
				}
				if(touchvec.x > Gdx.graphics.getWidth()/2){
					//go 2 steps
					player.go3steps();
				}
			}

			if(Gdx.input.isKeyPressed(Keys.A)){
				player.go2steps();
			}
			if(Gdx.input.isKeyPressed(Keys.L)){
				player.go3steps();
			}
		}

	}

	private void checkWinning() {

	}



	private void makeNewGrid() {

		System.out.println("new Grid");
		int y = 400;

		for(int i = 0; i < 500; i++) 
		{
			boolean r = random.nextBoolean();

			Block b = new Block();
			if(blocks.size() > 0 && r && blocks.get(i-1).getY() < 1000)
			{
				b.setBounds(100*i, 2000, 64, 64);
			} else {
				b.setBounds(100*i, y, 64, 64);
			}

			blocks.add(b);
		}
	}


}

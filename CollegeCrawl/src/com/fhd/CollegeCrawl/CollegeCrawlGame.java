package com.fhd.CollegeCrawl;

import java.util.ArrayList;

import Minigames.BugHunt.BugHuntCore;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class CollegeCrawlGame implements ApplicationListener
{

	private MaCamera camera;
	private SpriteBatch batch;
	private Minigame minigame = null;
	private Sprite worldmap;
	private Player player;
	private ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();

	@Override
	public void create() {
		camera = new MaCamera();
		camera.zoom = 0.5f;
		batch = new SpriteBatch();

		player = new Player(1);
		
		worldmap = new Sprite(new Texture("content/map.png"));
		worldmap.setBounds(0, 0, 1024, 1024);
		worldmap.flip(false, true);
		
		//TODO grobe collision-map machen
		//TODO player movement nur mit "gohere", animationen darauf ändern
	}


	@Override
	public void render() {

		if(minigame != null)
		{
			minigame.run();
		} 
		else 
		{
			Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			camera.update();
			batch.setProjectionMatrix(camera.combined);

			camera.goHereSmoth(player.getX(), player.getY());

			batch.begin();

			worldmap.draw(batch);
			player.draw(batch);
			player.update();
			
			batch.end();
		}

		if(Gdx.input.isKeyPressed(Keys.NUM_1))
		{
			minigame = null;
//			minigame = new Minigame1();
			minigame = new BugHuntCore();
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_2))
		{
			minigame = null;
			minigame = new Minigame2();	
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_3))
		{
			minigame = null;
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			player.translateX(2);
			player.goRight();
		} else {
			player.isWalkingRight = false;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			player.translateX(-2);
			player.goLeft();
		} else {
			player.isWalkingLeft = false;
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)){
			player.translateY(-1.7f);
			player.goUp();
		} else {
			player.isWalkingUp = false;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			player.translateY(1.7f);
			player.goDown();
		} else {
			player.isWalkingDown = false;
		}
	}



	@Override
	public void dispose() {

	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}

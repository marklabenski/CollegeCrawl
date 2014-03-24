package com.fhd.CollegeCrawl;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class CollegeCrawlGame implements ApplicationListener
{

	private MaCamera camera;
	private SpriteBatch batch;
	private Minigame minigame = null;
	private Sprite worldmap;
	private Sprite player;

	@Override
	public void create() {
		camera = new MaCamera();
		camera.setToOrtho(true);
		camera.zoom = 0.5f;
		batch = new SpriteBatch();

		player = new Sprite(new Texture("content/girl_walk/1.png"));
		player.setBounds(100, 100, 64, 64);
		player.flip(false, true);
		
		worldmap = new Sprite(new Texture("content/map.png"));
		worldmap.setBounds(0, 0, 1024, 1024);
		worldmap.flip(false, true);
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
			
			batch.end();
		}

		if(Gdx.input.isKeyPressed(Keys.NUM_1))
		{
			minigame = null;
			minigame = new Minigame1();
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
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			player.translateX(-2);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			player.translateY(-2);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			player.translateY(2);
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

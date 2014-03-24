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
	private Sprite tempsprite;
	
	@Override
	public void create() {
		camera = new MaCamera();
		batch = new SpriteBatch();
		
		tempsprite = new Sprite(new Texture("content/1.png"));
		
		camera.setToOrtho(true);
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

			camera.goHereSmoth(tempsprite.getX(), tempsprite.getY());
			camera.zoom += 0.1f;
			
			batch.begin();
			
			tempsprite.setBounds(200, 200, 200, 200);
			tempsprite.setOrigin(100, 100);
			tempsprite.rotate(5);
			
			
			tempsprite.draw(batch);
			
			batch.end();
			
		}
		
		if(Gdx.input.isKeyPressed(Keys.NUM_1)){
			minigame = null;
			minigame = new Minigame1();
		}
		else if(Gdx.input.isKeyPressed(Keys.NUM_2)){
			minigame = null;
			minigame = new Minigame2();	
		}
		else if(Gdx.input.isKeyPressed(Keys.NUM_3)){
			minigame = null;
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

package com.fhd.CollegeCrawl;

import java.util.ArrayList;

import Minigames.BugHunt.BugHuntCore;
import Minigames.HackInvaders.HackInvaders;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class CollegeCrawlGame implements ApplicationListener
{

	private MaCamera camera;
	private SpriteBatch batch;
	private Minigame minigame = null;
	private Sprite worldmap;
	private Player player;
	private Vector2 touch;
	private ShapeRenderer shaperenderer;
	private FileManager filemanager;

	private ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();
	private ArrayList<Animation> animations = new ArrayList<Animation>();
	private boolean debugmode = true;
	private Rectangle selected;
	private Timer buttontimer = new Timer();
	private Vector2 touchSnaped = new Vector2();

	@Override
	public void create() {
		camera = new MaCamera();
		camera.zoom = 0.5f;
		batch = new SpriteBatch();
		shaperenderer = new ShapeRenderer();
		filemanager = new FileManager();

		player = new Player();

		worldmap = new Sprite(new Texture("content/map3.png"));
		worldmap.setBounds(0, 0, 1024, 1024);
		worldmap.flip(false, true);

		//		blocks.add(new Rectangle(200,200,100,100));
		//		blocks.add(new Rectangle(500,200,100,100));
		//		blocks.add(new Rectangle(200,600,100,100));
		blocks.addAll(filemanager.loadBlocks());
		animations.add(new Animation(300,300,"temp"));
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

			if(!debugmode)
			{
				camera.goHereSmoth(player.getX(), player.getY());
				camera.zoom = 0.5f;
			} else {
				camera.zoom = 1;
			}

			batch.begin();////////////////////////////////
			//draw the map
			worldmap.draw(batch);

			//draw animations
			for(Animation a: animations)
			{
				a.update();
				a.draw(batch);
			}
			//draw the player
			player.shadow.draw(batch);
			player.draw(batch);
			player.update();

			batch.end();//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

			collision();
			editor();
			if(!debugmode)
			{
				input();
			}
			if(Gdx.input.isKeyPressed(Keys.E) && buttontimer.done())
			{
				if(debugmode)
				{
					debugmode = false;
				} else {
					debugmode = true;
				}
				buttontimer.wait(200);
			}
		}



	}



	private void collision() {
		for(Rectangle b : blocks)
		{
			//TODO AABB collision oder das alte collision system
			if(b.overlaps(player.rect))
			{
				player.setNav(new Vector2(player.getX()+32,player.getY()+50));
			}
			/*
			if(b.overlaps(player.top))
			{
				player.translateY(5);
				player.setNav(new Vector2(player.getX()+32,player.getY()+50));
			}
			if(b.overlaps(player.left))
			{
				player.translateX(5);
				player.setNav(new Vector2(player.getX()+32,player.getY()+50));
			}
			if(b.overlaps(player.right))
			{
				player.translateX(-5);
				player.setNav(new Vector2(player.getX()+32,player.getY()+50));
			}
			if(b.overlaps(player.bottom))
			{
				player.translateY(-5);	
				player.setNav(new Vector2(player.getX()+32,player.getY()+50));
			}
			 */
		}
	}


	private void editor() {
		if(debugmode)
		{
			if(Gdx.input.justTouched())
			{
				//touch coordinates to world coordinates
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(pos);
				touch = new Vector2(pos.x, pos.y);

				for(Rectangle r :blocks)
				{
					if(r.contains(touch))
					{
						selected = r;
					}
				}
			}



			if(Gdx.input.isKeyPressed(Keys.SPACE))
			{
				//touch coordinates to world coordinates
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(pos);
				touch = new Vector2(pos.x, pos.y);

				camera.goHereSmoth(touch.x, touch.y);
			} 	

			//wenn ein objekt markiert ist
			if(selected != null)
			{
				if(Gdx.input.isKeyPressed(Keys.W))
				{
					//touch coordinates to world coordinates
					Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
					camera.unproject(pos);
					touch = new Vector2(pos.x, pos.y);

					touchSnaped.x = touch.x - (touch.x % 32);
					touchSnaped.y = touch.y - (touch.y % 32);
					
					selected.setPosition(touchSnaped);
				}
				
				if(buttontimer.done())
				{
					if(Gdx.input.isKeyPressed(Keys.UP))
					{
						selected.setHeight(selected.getHeight()-8);
						buttontimer.wait(100);
					}
					if(Gdx.input.isKeyPressed(Keys.DOWN))
					{
						selected.setHeight(selected.getHeight()+8);
						buttontimer.wait(100);
					}
					if(Gdx.input.isKeyPressed(Keys.LEFT))
					{
						selected.setWidth(selected.getWidth()-8);
						buttontimer.wait(100);
					}
					if(Gdx.input.isKeyPressed(Keys.RIGHT))
					{
						selected.setWidth(selected.getWidth()+8);
						buttontimer.wait(100);
					}
				}
			}
			shaperenderer.setProjectionMatrix(camera.combined);
			shaperenderer.begin(ShapeType.Line);

			shaperenderer.setColor(Color.RED);
			shaperenderer.rect(player.rect.x, player.rect.y, player.rect.width,player.rect.height);

			//		shaperenderer.setColor(Color.WHITE);
			//		shaperenderer.rect(player.top.x, player.top.y, player.top.width,player.top.height);
			//		shaperenderer.setColor(Color.ORANGE);
			//		shaperenderer.rect(player.left.x, player.left.y, player.left.width,player.left.height);
			//		shaperenderer.setColor(Color.GREEN);
			//		shaperenderer.rect(player.right.x, player.right.y, player.right.width,player.right.height);
			//		shaperenderer.setColor(Color.PINK);
			//		shaperenderer.rect(player.bottom.x, player.bottom.y, player.bottom.width,player.bottom.height);

			for(Rectangle b : blocks)
			{
				if(selected != null && b.equals(selected))
				{
					shaperenderer.setColor(Color.RED);
					shaperenderer.rect(b.x, b.y, b.width,b.height);
					shaperenderer.rect(b.x+8, b.y+8, b.width-16,b.height-16);

				} else {		
					shaperenderer.setColor(Color.BLUE);
					shaperenderer.rect(b.x, b.y, b.width,b.height);

				}

			}

			shaperenderer.end();
		}
	}


	private void input() {

		if(Gdx.input.justTouched())
		{
			//touch coordinates to world coordinates
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(pos);
			touch = new Vector2(pos.x, pos.y);

			player.setNav(touch);
		}

		if(Gdx.input.isKeyPressed(Keys.S))
		{
			filemanager.saveBlocks(blocks);
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_1))
		{
			minigame = null;
			minigame = new BugHuntCore();
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_2))
		{
			minigame = null;
			minigame = new HackInvaders();	
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_3))
		{
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

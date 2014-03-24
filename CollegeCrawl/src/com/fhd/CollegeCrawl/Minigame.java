package com.fhd.CollegeCrawl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class Minigame {
    public boolean gameFinished = false;

    public SpriteBatch batch;
    public MaCamera camera;

    public Minigame() {
        camera = new MaCamera();
        batch = new SpriteBatch();
    }

	public void run(){
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
	}

}

package com.fhd.CollegeCrawl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class Minigame {
	protected boolean gameFinished = false;

	protected SpriteBatch batch;
	protected MaCamera camera;
	protected ArrayList<ParticleEffect> particleFX;

	public Minigame() {
		camera = new MaCamera();
		batch = new SpriteBatch();
		particleFX = new ArrayList<ParticleEffect>();
	}

	public void run(){
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		updateParticle();
	}

	protected void addParticleEffect(String in, float x, float y) {
		ParticleEffect pe = new ParticleEffect();
		pe.load(Gdx.files.internal("content/particles/" + in + ".p"),
				Gdx.files.internal("content/particles"));
		pe.start();
		pe.setPosition(x, y);
		particleFX.add(pe);
	}

	private void updateParticle() {
		for (int i = particleFX.size() - 1; i >= 0; i--) {

			particleFX.get(i).update(Gdx.graphics.getRawDeltaTime());
			if (particleFX.get(i).isComplete()) {
				particleFX.remove(i);
			}
		}
	}


}

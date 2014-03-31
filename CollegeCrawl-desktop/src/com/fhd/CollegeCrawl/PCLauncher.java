package com.fhd.CollegeCrawl;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class PCLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "CollegeCrawl";
		cfg.useGL20 = false;
		cfg.width = 1024;
		cfg.height = 600;
		
		new LwjglApplication(new CollegeCrawlGame(), cfg);

		//wiiiiiiiii christoph
	}
}

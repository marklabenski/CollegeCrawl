package com.fhd.CollegeCrawl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Christopherson
 */
public class FileManager {


	private FileHandle file;
	private String path = "../CollegeCrawl/data/content/files/";
	private int loadamount = 100;


	//TODO save and load animations
	
	/**
	 * save all collision Blocks
	 * @param list Die Liste der collision Blocks die gespeichert werden soll
	 */
	public void saveBlocks(ArrayList<Rectangle>  list){
		System.out.println("saving dialogs");
		try{
			for(int i = 0; i < list.size(); i++){

				BufferedWriter out = new BufferedWriter(new FileWriter(path+"blocks/"+i+".txt"));

				String attributes = list.get(i).x+","+
						list.get(i).y+","+
						list.get(i).width+","+
						list.get(i).height;
				System.out.println("saving... "+attributes);

				out.write(attributes);
				out.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * load all collision blocks
	 * @return Eine Liste der geladenen Collision Rectangle
	 */
	public ArrayList<Rectangle> loadBlocks()
	{
		ArrayList<Rectangle> loadedblocks = new ArrayList<Rectangle>();

		for(int i = 0; i < loadamount; i++)
		{
			String filecontent = "";
			file = Gdx.files.internal(path+"blocks/"+i+".txt"); 
			
			try
			{
				filecontent = file.readString();

				System.out.println("load... "+file.name()+": "+filecontent+"\n");

				String[] lines = filecontent.split(",");

				loadedblocks.add(new Rectangle(Float.valueOf(lines[0].trim()), 
						Float.valueOf(lines[1].trim()), 
						Float.valueOf(lines[2].trim()), 
						Float.valueOf(lines[3].trim())));
			} 
			catch (Exception e)
			{
//				System.out.print("\t not found: "+path+"blocks/"+i+".txt");
			}
		}
		return loadedblocks;
	}
}

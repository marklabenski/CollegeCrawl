package com.fhd.CollegeCrawl;

import java.util.Random;

public class NPC extends Player
{
	
	NPC()
	{
		super();
		Random random = new Random();
		int r = random.nextInt(4);
		
		if(r == 0)
			this.setApperence("girl");
		if(r == 1)
			this.setApperence("guy");
		if(r == 2)
			this.setApperence("jim");
		if(r == 3)
			this.setApperence("chiqe");
		if(r == 4)
			this.setApperence("prof01");
		if(r == 5)
			this.setApperence("prof02");
		if(r == 6)
			this.setApperence("girl");
		
		this.setPosition(1000+random.nextInt(1000), 1000+random.nextInt(1000));
		this.destReached = true;
	}
	
}

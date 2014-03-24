package com.fhd.CollegeCrawl;


public class Timer {

	private long wait = -1;

	public void wait(int ms){
		wait  = System.currentTimeMillis()+ms;
	}
	
	public boolean done(){
		return !(wait != -1 && wait > System.currentTimeMillis()); //if waiting point is in the future
	}
}
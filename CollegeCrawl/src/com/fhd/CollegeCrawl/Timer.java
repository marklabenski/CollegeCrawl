package com.fhd.CollegeCrawl;

/**
 * Created by Jan on 31/03/14.
 */
public class Timer {
    private long wait = -1;

    public void wait(int ms) {
        wait = System.currentTimeMillis() + ms;
    }

    public boolean done(){
        return !(wait != -1 && wait > System.currentTimeMillis()); //if waiting point is in the future
    }
}

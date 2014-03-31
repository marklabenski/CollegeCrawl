package com.fhd.CollegeCrawl;


public class MinigameTimer {

    private long endTime;
    private long startTime;
    private int bonus = 1500;
    private int startValue = 30000;

    public MinigameTimer() {
        this.startTime = System.currentTimeMillis();
        this.endTime = this.startTime + startValue;
    }

    public MinigameTimer(int withValue, int andBonus){
        this.startValue = withValue;
        this.bonus = andBonus;
        this.startTime = System.currentTimeMillis();
        this.endTime = this.startTime + startValue;
    }

    public long getRemainingTime(){
        return this.endTime - System.currentTimeMillis();
    }

    public long getElapsedTime(){
        if(this.endTime <= System.currentTimeMillis()) {
            return this.endTime - this.startTime;
        }
        else return -1;
    }

    public void resetTimer(){
        this.endTime = System.currentTimeMillis() + startValue;
    }

    public void addBonus(){
        this.endTime += this.bonus;
    }

    public boolean done(){
        return (this.endTime >= System.currentTimeMillis());
    }
}
package com.fhd.CollegeCrawl;


public class Timer {

    private long endTime;
    private long startTime;
    private int bonus = 1500;
    private int startValue = 30000;

    public Timer() {
        this.startTime = System.currentTimeMillis();
        this.endTime = this.startTime + startValue;
    }

    public Timer(int withValue, int andBonus){
        this.startValue = withValue;
        this.bonus = andBonus;
        this.startTime = System.currentTimeMillis();
        this.endTime = this.startTime + startValue;
    }

    public long getRemainingTime(){
        return this.endTime - System.currentTimeMillis();
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
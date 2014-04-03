package Minigames.NetworkPipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.fhd.CollegeCrawl.Minigame;

import java.util.Random;

/**
 * Created by Jan on 01/04/14.
 */
public class NetworkPipes extends Minigame{
    Pipe[][] pipeField;
 //   Pipe pipe1;

    public NetworkPipes(){
        super();
        this.pipeField = new Pipe[3][3];
        fillUpField();
    }

    @Override
    public void run() {
        super.run();

        batch.begin();

        //Spielfeld zeichnen
        for(Pipe[] innerArray:this.pipeField){
            for(Pipe p:innerArray){
                p.draw(batch);
            }
        }

        batch.end();

        input();
    }

    private void input(){
        if(Gdx.input.justTouched()){
            int x=Gdx.input.getX();
            int y=Gdx.input.getY();
            for(Pipe[] ip:this.pipeField){
                for(Pipe p:ip){
                    if(p.getBoundingRectangle().contains(x,y)){
                        p.rotate(false);
                        break;
                    }
                }
            }
        }
    }

    private Pipe randomPipe(){
        Random rand = new Random();
        int rot = rand.nextInt(3);
        int type = rand.nextInt(3);
        Pipe randomPipe = new Pipe(type);
        while(rot != 0){
            randomPipe.rotate(true);
            rot--;
        }
        return randomPipe;
    }

    private void fillUpField(){
        int posX=320;
        int posY=100;
        for(int y=0;y < this.pipeField.length;y++){
            for(int x=0;x< this.pipeField[y].length;x++){
                this.pipeField[y][x] = randomPipe();
                this.pipeField[y][x].setPosition(posX+this.pipeField[y][x].getWidth()*x,posY+this.pipeField[y][x].getHeight()*y);
            }
        }
    }
}

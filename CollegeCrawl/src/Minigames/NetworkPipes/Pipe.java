package Minigames.NetworkPipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Jan on 01/04/14.
 */
public class Pipe extends Sprite {
    boolean l,r,t,b;
    Pipe(int type){
        super(new Texture("content/pipes/"+ type +".png"));
        this.setBounds(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,128,128);
        this.t=true;
        switch (type){
            case '0': l=r=b=t; break;
            case '1': l=b=t; break;
            case '2': l=t; break;
            case '3': b=t; break;
        }
    }

    public void rotate(boolean clockwise){
        this.rotate90(clockwise);
        boolean temp;
        if(clockwise){
            temp = this.l;
            this.l=this.b;
            this.b=this.r;
            this.r=this.t;
            this.t=temp;
        }
        else{
            temp=this.l;
            this.l=this.t;
            this.t=this.r;
            this.r=this.b;
            this.b=temp;
        }
    }
}

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
            case 0: l=b=t; break;
            case 1: b=t; break;
            case 2: l=b=t;t=false; break;//Warum zur Hölle wird das Bild schon um 90grad rotiert geladen ?!?!kleiner Workaround wenn libgdx es so will ;)
         // case 2: l=t; break; //siehe Bild Top und Left müssten eigentlich true sein
            case 3: l=r=b=t; break;
        }
    }

    public void rotate(boolean clockwise){
        this.rotate90(!clockwise);//Die Uhren drehen bei libgdx wohl auch anders herum
        boolean temp;
        System.out.println("left:"+this.l+" right:"+this.r+" top:"+this.t+" bottom:"+this.b);
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
        System.out.println("left:"+this.l+" right:"+this.r+" top:"+this.t+" bottom:"+this.b);
    }
}

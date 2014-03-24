package com.fhd.CollegeCrawl;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Fleat extends Sprite{

	ArrayList<Ship> ships = new ArrayList<Ship>();
	private Vector2 vec;
	public int size;
	public boolean inWarp = false ;
	public Planet inOrbitOf;
	private float speed = 4;

	public Fleat(float _x, float _y){
		this.setBounds(_x, _y, 100, 100);
		this.setOrigin(50, 50);
		ships.add(new Ship(_x,0,_y,0,1));
		ships.add(new Ship(_x,80,_y,0,1));
		ships.add(new Ship(_x,50,_y,50,1));
		ships.add(new Ship(_x,0,_y,80,1));
		ships.add(new Ship(_x,80,_y,80,1));
		size = 1;
	}

	public void update(){
		this.setOrigin(50,50);

		this.speed = 4;
				
		if(this.vec!=null){
			this.goHere(vec);
		}
		for(Ship s : this.ships)
		{
			s.setPosition(this.getX()+s.fleetplace.x, this.getY()+s.fleetplace.y);
//			s.setOrigin(s.getWidth()/2,s.getHeight()/2);
		}
	}

	private void goHere2(Vector2 _vec){
		float dx = _vec.x - getX();
		float dy = _vec.y - getY();
		double h = Math.sqrt(dx * dx + dy * dy);
		float dn = (float)(h / Math.sqrt(2));

		float speed = 0.007f;
		Vector2 newPos = new Vector2((dx/dn)*(dn*speed), (dy/dn)*(dn*speed));
		//		newPos = new Vector2(dx/dn, dy/dn);

		translate(newPos.x, newPos.y);

		for(Ship s : this.ships){
			s.translate(newPos.x, newPos.y);
			s.setRotation(newPos.angle()+90);
			s.setOrigin(s.getWidth()/2,s.getHeight()/2);
		}
	}
	
	private void goHere(Vector2 nav)//(float _x, float _y)
	{
		final Vector2 zielVec = new Vector2(nav.x, nav.y);
		final Vector2 posVec = new Vector2(this.getX(), this.getY());

		if (zielVec.dst(posVec) > speed)
		{
			zielVec.sub(posVec).nor().mul(speed);
			translate(zielVec.x, zielVec.y);

		} else
		{
			zielVec.sub(posVec);
		}
		translate(zielVec.x, zielVec.y);
		
	}
	
	public Vector2 getNavpoint(){
		if(vec!=null)return vec;
		else return new Vector2(0,0);
	}

	public void drawShips(SpriteBatch _batch){
		for(Ship s :ships){
			s.draw(_batch);
		}
	}

	public float x(){
		return getX()+getOriginX();
	}
	public float y(){
		return getY()+getOriginY();
	}
	
	public void setNav(Vector2 _vec){
		vec = _vec;
	}
}

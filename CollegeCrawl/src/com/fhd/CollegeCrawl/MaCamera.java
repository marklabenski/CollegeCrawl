package com.fhd.CollegeCrawl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/*
 * usage:
 * 
 * camera.startQuake(3000, 0.2f); length in MS
 * 
 * call camera.update() every frame
 * 
 * this code only works properly if the camera position is set every frame, like camera.position = character bla
 * if you dont the camera will move away from the source.
 * you could fix this with a hook of course.
 * 
 */
public class MaCamera extends OrthographicCamera
{
	private boolean isQuaking = false;
	private float quakeStrength;
	private long quakeEndPoint;
	private float rotation = 0;
	private float aimrot = 0;
	
	public MaCamera()
	{
		this.setToOrtho(true);
	}

	public MaCamera(float viewportWidth, float viewportHeight)
	{
		super(viewportWidth, viewportHeight);
	}
	
	public void goHereStatic(float _x, float _y) 
	{
		this.position.set(_x,_y,0);
	}
	
	public void goHereSmoth(float _x, float _y) 
	{
		System.out.println(this.position);

		float dx = _x - this.position.x;
		float dy = _y - this.position.y;
		double h = Math.sqrt(dx * dx + dy * dy);
		float dn = (float) (h / Math.sqrt(2));

		Vector2 newPos = new Vector2((dx / dn) * (dn * 0.04f), (dy / dn)* (dn * 0.05f));

		this.position.x += newPos.x;
		this.position.y += newPos.y;
	}
	
	public void startQuake(long length, float strengthFactor)
	{
		quakeStrength = strengthFactor;
		quakeEndPoint = System.currentTimeMillis()+length;
		isQuaking = true;
	}

	@Override public void update()
	{
		updateQuake();
		super.update();

		float speed = 5.0f;//(((Math.abs(rotation) - Math.abs(aimrot))*0.1f)*0.5f);

		if(rotation < -179)
		{
			rotation = 180;
		}
		
		if(rotation <= -90 && aimrot == 180)
		{
			rotation -= speed;
			rotate(-speed);		
		} else {

			if(aimrot > rotation)
			{
				rotation += speed;
				rotate(speed);
			}

			if(aimrot < rotation)
			{
				rotation -= speed;
				rotate(-speed);
			} 
		}
//		System.out.println(aimrot+"AIM / ROT"+rotation);
	}

	@Override public void update(boolean updateFrustum)
	{
		updateQuake();
		super.update(updateFrustum);
	}

	public void setRotation(float _r)
	{
		aimrot = _r;
	}

	public float getCameraCurrentXYAngle()
	{
		return  (float) (Math.atan2(this.up.x, this.up.y)* MathUtils.radiansToDegrees);
	}

	private void updateQuake()
	{
		if (!isQuaking) return;

		position.x+= (int)(35*quakeStrength-(Math.random()*70*quakeStrength));
		position.y+= (int)(35*quakeStrength-(Math.random()*70*quakeStrength));

		if (System.currentTimeMillis() >= quakeEndPoint)
		{
			isQuaking = false;
		}
	}

}

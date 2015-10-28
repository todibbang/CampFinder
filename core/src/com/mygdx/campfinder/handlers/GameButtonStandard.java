package com.mygdx.campfinder.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.campfinder.main.CampFinder;

public class GameButtonStandard {
	
	private float x;
	private float y;
	private float width;
	private float height;
	private float letterScale;
	
	private float yMin = 0;
	private float yMax = 0;
	private float xMin = 0;
	private float xMax = 0;
	
	Vector3 vec;
	private OrthographicCamera cam;
	
	private TextureRegion reg;
	
	private String buttonText;
	
	private boolean clicked;
	private boolean released;
	private boolean down;
	
	
	
	//public GameButton(TextureRegion regOut, float x, float y, OrthographicCamera cam){
	public GameButtonStandard(String string, float x, float y, OrthographicCamera cam, float sX, float sY,  float Ls){	
		
		Texture PlainButtonTex = CampFinder.res.getTexture("Button");
		this.reg = new TextureRegion(PlainButtonTex, 0, 0, PlainButtonTex.getWidth(), PlainButtonTex.getHeight());
		
		this.buttonText = string;
		//this.reg = reg;
		this.x = x;
		this.y = y;
		
		
		this.cam = cam;
		
		this.letterScale = Ls;
		
		this.width = sX;
		this.height = sY;
		this.vec = new Vector3();
		
		this.yMin = y - (height / 2);
		this.yMax = y + (height / 2);
		this.xMin = x - (width / 2);
		this.xMax = x + (width / 2);
		
	}
	
	public boolean isClicked() { return clicked; }
	public boolean isReleased() { return released; }
	public boolean isDown() { return down; }
	
	public void update(float dt) {
		
		
		vec.set(MyInput.x, MyInput.y, 0);
		if(cam != null){
		cam.unproject(vec);
		}
		
		if(MyInput.isPressed() &&
			vec.x > xMin && vec.x < xMax &&
			vec.y > yMin && vec.y < yMax) {
			clicked = true;
		}
		else {
			clicked = false;
		}
		
		if(MyInput.isDown() &&
				vec.x > xMin && vec.x < xMax &&
				vec.y > yMin && vec.y < yMax) {
				down = true;
			}
			else {
				down = false;
			}
		
		if(MyInput.isReleased() &&
				vec.x > xMin && vec.x < xMax &&
				vec.y > yMin && vec.y < yMax) {
				released = true;
			}
		else released = false;
		
	}
	
	public void render(SpriteBatch sb) {
		
		sb.begin();
		sb.draw(reg, x - (width/2), y - (height / 2), width, height); 
		sb.end();
		Letters.drawLetter(sb, buttonText, x, y, letterScale, true, 1000);
		
		
	}
	
	
}
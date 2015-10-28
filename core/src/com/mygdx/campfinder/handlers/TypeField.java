package com.mygdx.campfinder.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.mygdx.campfinder.main.CampFinder;

public class TypeField implements TextInputListener{
	
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
	private float textX;
	private float textY;
	private boolean Centered = false;
	
	public String buttonText = "";
	
	private boolean clicked;
	private boolean released;	
	
	public TypeField(float x, float y, OrthographicCamera cam, float sX, float sY,  float Ls, boolean xCentered, boolean yCentered){	
		
		Texture PlainButtonTex = CampFinder.res.getTexture("Button");
		this.reg = new TextureRegion(PlainButtonTex, 0, 0, PlainButtonTex.getWidth(), PlainButtonTex.getHeight());
		
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
		
		if(xCentered) this.Centered = true;
		
		if(xCentered) textX = this.x;
		else textX = xMin + 10;
		if(yCentered) textY = this.y;
		else textY = yMax - 30;
	}
	
	public boolean isClicked() { return clicked; }
	public boolean isReleased() { return released; }
	
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
		
		if(MyInput.isReleased() &&
				vec.x > xMin && vec.x < xMax &&
				vec.y > yMin && vec.y < yMax) {
				released = true;
				Gdx.input.getTextInput(this, buttonText, buttonText, "");

			}
		else released = false;

		//buttonText = Letters.type(buttonText);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(reg, x - (width/2), y - (height / 2), width, height); 
		sb.end();
		Letters.drawLetter(sb, buttonText, textX, textY, letterScale, Centered, (int) width - 20);
	}

	@Override
	public void input(String text) {
		buttonText = text;
	}
	@Override
	public void canceled() {
		buttonText = "";
	}
}
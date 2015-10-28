package com.mygdx.campfinder.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor{
	
	public boolean mouseMoved(int x, int y) {
		MyInput.x = x;
		MyInput.y = y;
		return true;
	}
	
	public boolean touchDragged(int x, int y, int pointer) {
		
		
		MyInput.x = x;
		MyInput.y = y;
		MyInput.down = true;
		return true;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		if(pointer < 5)
		{
			MyInput.touches[pointer].touchX = x;
			MyInput.touches[pointer].touchY = y;
			MyInput.touches[pointer].touched = true;
		}
		
		MyInput.x = x;
		MyInput.y = y;
		MyInput.down = true;
		return true;
	}
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		if(pointer < 5)
		{
			MyInput.touches[pointer].touchX = x;
			MyInput.touches[pointer].touchY = y;
			MyInput.touches[pointer].touched = false;
		}
		
		MyInput.x = x;
		MyInput.y = y;
		MyInput.down = false;
		return true;
	}
	public boolean scrolled(int s)
	{
		MyInput.scroll = s;
		return true;
	}
	
	public boolean keyDown(int k){

		switch(k)
		{
			case Keys.A : MyInput.setKey(MyInput.A, true);
			
		}
		
		/*
		if(k == Keys.A) MyInput.setKey(MyInput.A, true);
		
		*/
		if(k == Keys.B) MyInput.setKey(MyInput.B, true);
		if(k == Keys.C) MyInput.setKey(MyInput.C, true);
		if(k == Keys.D) MyInput.setKey(MyInput.D, true);
		if(k == Keys.E) MyInput.setKey(MyInput.E, true);
		if(k == Keys.F) MyInput.setKey(MyInput.F, true);
		if(k == Keys.G) MyInput.setKey(MyInput.G, true);
		if(k == Keys.H) MyInput.setKey(MyInput.H, true);
		if(k == Keys.I) MyInput.setKey(MyInput.I, true);
		if(k == Keys.J) MyInput.setKey(MyInput.J, true);
		if(k == Keys.K) MyInput.setKey(MyInput.K, true);
		if(k == Keys.L) MyInput.setKey(MyInput.L, true);
		if(k == Keys.M) MyInput.setKey(MyInput.M, true);
		if(k == Keys.N) MyInput.setKey(MyInput.N, true);
		if(k == Keys.O) MyInput.setKey(MyInput.O, true);
		if(k == Keys.P) MyInput.setKey(MyInput.P, true);
		if(k == Keys.Q) MyInput.setKey(MyInput.Q, true);
		if(k == Keys.R) MyInput.setKey(MyInput.R, true);
		if(k == Keys.S) MyInput.setKey(MyInput.S, true);
		if(k == Keys.T) MyInput.setKey(MyInput.T, true);
		if(k == Keys.U) MyInput.setKey(MyInput.U, true);
		if(k == Keys.V) MyInput.setKey(MyInput.V, true);
		if(k == Keys.W) MyInput.setKey(MyInput.W, true);
		if(k == Keys.X) MyInput.setKey(MyInput.X, true);
		if(k == Keys.Y) MyInput.setKey(MyInput.Y, true);
		if(k == Keys.Z) MyInput.setKey(MyInput.Z, true);
		
		
		
		if(k == Keys.SPACE) MyInput.setKey(MyInput.SPACE, true);
		if(k == Keys.ENTER) MyInput.setKey(MyInput.ENTER, true);
		if(k == Keys.BACKSPACE) MyInput.setKey(MyInput.BACKSPACE, true);
		
		if(k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) MyInput.setKey(MyInput.SHIFT, true);
		
		
		
		return true;
	}
	
	public boolean keyUp(int k){
		if(k == Keys.A) MyInput.setKey(MyInput.A, false);
		if(k == Keys.B) MyInput.setKey(MyInput.B, false);
		if(k == Keys.C) MyInput.setKey(MyInput.C, false);
		if(k == Keys.D) MyInput.setKey(MyInput.D, false);
		if(k == Keys.E) MyInput.setKey(MyInput.E, false);
		if(k == Keys.F) MyInput.setKey(MyInput.F, false);
		if(k == Keys.G) MyInput.setKey(MyInput.G, false);
		if(k == Keys.H) MyInput.setKey(MyInput.H, false);
		if(k == Keys.I) MyInput.setKey(MyInput.I, false);
		if(k == Keys.J) MyInput.setKey(MyInput.J, false);
		if(k == Keys.K) MyInput.setKey(MyInput.K, false);
		if(k == Keys.L) MyInput.setKey(MyInput.L, false);
		if(k == Keys.M) MyInput.setKey(MyInput.M, false);
		if(k == Keys.N) MyInput.setKey(MyInput.N, false);
		if(k == Keys.O) MyInput.setKey(MyInput.O, false);
		if(k == Keys.P) MyInput.setKey(MyInput.P, false);
		if(k == Keys.Q) MyInput.setKey(MyInput.Q, false);
		if(k == Keys.R) MyInput.setKey(MyInput.R, false);
		if(k == Keys.S) MyInput.setKey(MyInput.S, false);
		if(k == Keys.T) MyInput.setKey(MyInput.T, false);
		if(k == Keys.U) MyInput.setKey(MyInput.U, false);
		if(k == Keys.V) MyInput.setKey(MyInput.V, false);
		if(k == Keys.W) MyInput.setKey(MyInput.W, false);
		if(k == Keys.X) MyInput.setKey(MyInput.X, false);
		if(k == Keys.Y) MyInput.setKey(MyInput.Y, false);
		if(k == Keys.Z) MyInput.setKey(MyInput.Z, false);
		
		if(k == Keys.SPACE) MyInput.setKey(MyInput.SPACE, false);
		if(k == Keys.ENTER) MyInput.setKey(MyInput.ENTER, false);
		if(k == Keys.BACKSPACE) MyInput.setKey(MyInput.BACKSPACE, false);
		
		if(k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) MyInput.setKey(MyInput.SHIFT, false);
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
}

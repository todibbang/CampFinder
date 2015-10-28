package com.mygdx.campfinder.handlers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.mygdx.campfinder.main.CampFinder;

public class Letters {
	
	private static TextureRegion[] spriteLetters;
	
	public static void drawLetter(SpriteBatch sb, String s, float x, float y, float ls, boolean Centered, float maxWidth) 
	{
		if( s == null) return;
		
		String [] words = s.split(" ");
		
		float lsX = ls;
		float lsY = ls / 18f * 24f; 
		
		int startX = (int) (s.length() * lsX)/2;
		int startY = (int) (lsY)/2;
		
		Texture texLetters = CampFinder.res.getTexture("Letters");
		spriteLetters = new TextureRegion[100];
		
		for(int i = 0; i < 29; i ++)
		{
			spriteLetters[i] = new TextureRegion(texLetters, i * 18, 0, 18, 24);
		}
		for(int i = 0; i < 29; i ++)
		{
			spriteLetters[i+29] = new TextureRegion(texLetters, i * 18, 24, 18, 24);
		}
		for(int i = 0; i < 29; i ++)
		{
			spriteLetters[i+58] = new TextureRegion(texLetters, i * 18, 48, 18, 24);
		}
		
		float yC = y;
		float xC = x;
		int ii = 0;
		
		for(int w = 0; w < words.length; w++)
		{
			if((words[w].length() * lsX) +  (ii * lsX) > maxWidth)
			{
				yC -= lsY;
				ii = 0;
			}
			for(int i = 0; i < words[w].length(); i++) 
			{
				char c = words[w].charAt(i); 
				if(c == 'A') c = 0;
				else if(c == 'B') c = 1;
				else if(c == 'C') c = 2;
				else if(c == 'D') c = 3;
				else if(c == 'E') c = 4;
				else if(c == 'F') c = 5;
				else if(c == 'G') c = 6;
				else if(c == 'H') c = 7;
				else if(c == 'I') c = 8;
				else if(c == 'J') c = 9;
				else if(c == 'K') c = 10;
				else if(c == 'L') c = 11;
				else if(c == 'M') c = 12;
				else if(c == 'N') c = 13;
				else if(c == 'O') c = 14;
				else if(c == 'P') c = 15;
				else if(c == 'Q') c = 16;
				else if(c == 'R') c = 17;
				else if(c == 'S') c = 18;
				else if(c == 'T') c = 19;
				else if(c == 'U') c = 20;
				else if(c == 'V') c = 21;
				else if(c == 'W') c = 22;
				else if(c == 'X') c = 23;
				else if(c == 'Y') c = 24;
				else if(c == 'Z') c = 25;
				else if(c == '.') c = 26;
				else if(c == ',') c = 27;
				else if(c == ' ') c = 28;
				else if(c == 'a') c = 29;
				else if(c == 'b') c = 30;
				else if(c == 'c') c = 31;
				else if(c == 'd') c = 32;
				else if(c == 'e') c = 33;
				else if(c == 'f') c = 34;
				else if(c == 'g') c = 35;
				else if(c == 'h') c = 36;
				else if(c == 'i') c = 37;
				else if(c == 'j') c = 38;
				else if(c == 'k') c = 39;
				else if(c == 'l') c = 40;
				else if(c == 'm') c = 41;
				else if(c == 'n') c = 42;
				else if(c == 'o') c = 43;
				else if(c == 'p') c = 44;
				else if(c == 'q') c = 45;
				else if(c == 'r') c = 46;
				else if(c == 's') c = 47;
				else if(c == 't') c = 48;
				else if(c == 'u') c = 49;
				else if(c == 'v') c = 50;
				else if(c == 'w') c = 51;
				else if(c == 'x') c = 52;
				else if(c == 'y') c = 53;
				else if(c == 'z') c = 54;
				else if(c == '?') c = 55;
				else if(c == '!') c = 56;
				else if(c == '0') c = 58;
				else if(c == '1') c = 59;
				else if(c == '2') c = 60;
				else if(c == '3') c = 61;
				else if(c == '4') c = 62;
				else if(c == '5') c = 63;
				else if(c == '6') c = 64;
				else if(c == '7') c = 65;
				else if(c == '8') c = 66;
				else if(c == '9') c = 67;
				else if(c == ':') c = 68;
				else if(c == ';') c = 69;
				else if(c == '/') c = 70;
				else if(c == '(') c = 71;
				else if(c == ')') c = 72;
				else if(c == '%') c = 73;
				else if(c == '+') c = 74;
				else if(c == '-') c = 75;
				else if(c == '~') 
				{
					yC -= lsY;
					ii = 0;
					continue;
				}
				else continue;
				
				sb.begin();
				if(Centered) sb.draw(spriteLetters[c], (xC - startX) + (ii * lsX), yC - startY , lsX, lsY);
				else sb.draw(spriteLetters[c], (xC) + (ii * lsX), yC - startY , lsX, lsY);
				sb.end();
				++ii;
			}
			
			sb.begin();
			if(Centered) sb.draw(spriteLetters[28], (xC - startX) + (ii * lsX), yC - startY , lsX, lsY);
			else sb.draw(spriteLetters[28], (xC) + (ii * lsX), yC - startY , lsX, lsY);
			sb.end();
			++ii;
		}
	}
	
	public static String type(String text)
	{
		if(MyInput.isPressed(MyInput.A) && MyInput.isDown(MyInput.SHIFT)) text = text + "A";
		else if(MyInput.isPressed(MyInput.A)) text = text + "a";
		if(MyInput.isPressed(MyInput.B) && MyInput.isDown(MyInput.SHIFT)) text = text + "B";
		else if(MyInput.isPressed(MyInput.B)) text = text + "b";
		if(MyInput.isPressed(MyInput.C) && MyInput.isDown(MyInput.SHIFT)) text = text + "C";
		else if(MyInput.isPressed(MyInput.C)) text = text + "c";
		if(MyInput.isPressed(MyInput.D) && MyInput.isDown(MyInput.SHIFT)) text = text + "D";
		else if(MyInput.isPressed(MyInput.D)) text = text + "d";
		if(MyInput.isPressed(MyInput.E) && MyInput.isDown(MyInput.SHIFT)) text = text + "E";
		else if(MyInput.isPressed(MyInput.E)) text = text + "e";
		if(MyInput.isPressed(MyInput.F) && MyInput.isDown(MyInput.SHIFT)) text = text + "F";
		else if(MyInput.isPressed(MyInput.F)) text = text + "f";
		if(MyInput.isPressed(MyInput.G) && MyInput.isDown(MyInput.SHIFT)) text = text + "G";
		else if(MyInput.isPressed(MyInput.G)) text = text + "g";
		if(MyInput.isPressed(MyInput.H) && MyInput.isDown(MyInput.SHIFT)) text = text + "H";
		else if(MyInput.isPressed(MyInput.H)) text = text + "h";
		if(MyInput.isPressed(MyInput.I) && MyInput.isDown(MyInput.SHIFT)) text = text + "I";
		else if(MyInput.isPressed(MyInput.I)) text = text + "i";
		if(MyInput.isPressed(MyInput.J) && MyInput.isDown(MyInput.SHIFT)) text = text + "J";
		else if(MyInput.isPressed(MyInput.J)) text = text + "j";
		if(MyInput.isPressed(MyInput.K) && MyInput.isDown(MyInput.SHIFT)) text = text + "K";
		else if(MyInput.isPressed(MyInput.K)) text = text + "k";
		if(MyInput.isPressed(MyInput.L) && MyInput.isDown(MyInput.SHIFT)) text = text + "L"; 
		else if(MyInput.isPressed(MyInput.L)) text = text + "l";
		if(MyInput.isPressed(MyInput.M) && MyInput.isDown(MyInput.SHIFT)) text = text + "M";
		else if(MyInput.isPressed(MyInput.M)) text = text + "m";
		if(MyInput.isPressed(MyInput.N) && MyInput.isDown(MyInput.SHIFT)) text = text + "N";
		else if(MyInput.isPressed(MyInput.N)) text = text + "n";
		if(MyInput.isPressed(MyInput.O) && MyInput.isDown(MyInput.SHIFT)) text = text + "O";
		else if(MyInput.isPressed(MyInput.O)) text = text + "o";
		if(MyInput.isPressed(MyInput.P) && MyInput.isDown(MyInput.SHIFT)) text = text + "P";
		else if(MyInput.isPressed(MyInput.P)) text = text + "p";
		if(MyInput.isPressed(MyInput.Q) && MyInput.isDown(MyInput.SHIFT)) text = text + "Q";
		else if(MyInput.isPressed(MyInput.Q)) text = text + "q";
		if(MyInput.isPressed(MyInput.R) && MyInput.isDown(MyInput.SHIFT)) text = text + "R";
		else if(MyInput.isPressed(MyInput.R)) text = text + "r";
		if(MyInput.isPressed(MyInput.S) && MyInput.isDown(MyInput.SHIFT)) text = text + "S";
		else if(MyInput.isPressed(MyInput.S)) text = text + "s";
		if(MyInput.isPressed(MyInput.T) && MyInput.isDown(MyInput.SHIFT)) text = text + "T";
		else if(MyInput.isPressed(MyInput.T)) text = text + "t";
		if(MyInput.isPressed(MyInput.U) && MyInput.isDown(MyInput.SHIFT)) text = text + "U";
		else if(MyInput.isPressed(MyInput.U)) text = text + "u";
		if(MyInput.isPressed(MyInput.V) && MyInput.isDown(MyInput.SHIFT)) text = text + "V";
		else if(MyInput.isPressed(MyInput.V)) text = text + "v";
		if(MyInput.isPressed(MyInput.W) && MyInput.isDown(MyInput.SHIFT)) text = text + "W";
		else if(MyInput.isPressed(MyInput.W)) text = text + "w";
		if(MyInput.isPressed(MyInput.X) && MyInput.isDown(MyInput.SHIFT)) text = text + "X";
		else if(MyInput.isPressed(MyInput.X)) text = text + "x";
		if(MyInput.isPressed(MyInput.Y) && MyInput.isDown(MyInput.SHIFT)) text = text + "Y";
		else if(MyInput.isPressed(MyInput.Y)) text = text + "y";
		if(MyInput.isPressed(MyInput.Z) && MyInput.isDown(MyInput.SHIFT)) text = text + "Z";
		else if(MyInput.isPressed(MyInput.Z)) text = text + "z";
		
		
		if(MyInput.isPressed(MyInput.SPACE)) text = text + " ";
		if(MyInput.isPressed(MyInput.BACKSPACE) && text.length() > 0) text = text.substring(0, text.length()-1);
		
		return text;
	}
	

	public float x(float x) {
		x = x * (CampFinder.V_WIDTH / 1000f);
		return x;
	}
	public float y(float y) {
		y = y * (CampFinder.V_HEIGHT / 1000f);
		return y;
	}

}

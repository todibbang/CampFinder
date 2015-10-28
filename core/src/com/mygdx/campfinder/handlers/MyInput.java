package com.mygdx.campfinder.handlers;

public class MyInput {
	public static int x;
	public static int y;
	public static boolean down;
	public static boolean pdown;
	
	public static class touchInfo{
		public float touchX = 0;
		public float touchY = 0;
		public boolean touched = false;
	}
	public static touchInfo touches [] = new touchInfo[5];
	
	public static int scroll = 0;
	
	public static boolean[] keys;
	public static boolean[] pkeys;
	
	public static final int NUM_KEYS = 30;
	
	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int G = 6;
	public static final int H = 7;
	public static final int I = 8;
	public static final int J = 9;
	public static final int K = 10;
	public static final int L = 11;
	public static final int M = 12;
	public static final int N = 13;
	public static final int O = 14;
	public static final int P = 15;
	public static final int Q = 16;
	public static final int R = 17;
	public static final int S = 18;
	public static final int T = 19;
	public static final int U = 20;
	public static final int V = 21;
	public static final int W = 22;
	public static final int X = 23;
	public static final int Y = 24;
	public static final int Z = 25;
	
	public static final int SPACE = 26;
	public static final int ENTER = 27;
	public static final int BACKSPACE = 28;
	
	public static final int SHIFT = 29;

	
	static {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		scroll = 0;
		
		pdown = down;
		for (int i = 0;  i < NUM_KEYS; i++){
			pkeys[i] = keys[i];
		}
	}
	
	
	
	public static void setKey(int i, boolean b){
		keys[i] = b;
	}
	
	public static boolean isDown(int i){
		return keys[i];
	}
	
	public static boolean isPressed(int i){
		return keys[i] && !pkeys[i];
	}
	
	public static boolean isReleased(int i){
		return !keys[i] && pkeys[i];
	}
	
	public static boolean isDown() { return down; }
	public static boolean isPressed() { return down && !pdown; }
	public static boolean isReleased() { return !down && pdown; }
	
}

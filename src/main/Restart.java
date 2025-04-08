package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Restart {
	public  int x, y,w,h;
	public String str;
	public static boolean pressed=false;
	public static boolean mudaCor=false;
	public int cur=0,maxCur=5;
	
	public Restart(int x,int y,int w, int h, String str) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.str=str;
	}
	
	public void update() {
		if(pressed) {
			pressed=false;
			if(Main.mx>=x-22&&Main.mx<=x-22+w&&Main.my>=y-32&&Main.my<=y-32+h) {
				Main.restart();
				mudaCor=true;
			}
		}
		if(mudaCor) {
			cur++;
			if(cur>=maxCur) {
				mudaCor=false;
				cur=0;
			}
		}
	}
	
	public void render(Graphics g) {
		if(mudaCor) {
			g.setColor(Color.LIGHT_GRAY);
		}else {
			g.setColor(Color.DARK_GRAY);
		}
		g.fillRect(x-22, y-32, w, h);
		g.drawRect(x-22, y-32, w, h);
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.BOLD,28));
		g.drawString(str, x, y);
		
		g.drawRect(x-22, y-32, w, h);
	}
}

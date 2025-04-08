package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UI{
	
	public Restart restart=new Restart(1002,200,150,50,"Resetar");
	public static int dist = 0;
	private int x = -800;
	private int y = 380;
	
	public void update() {
		restart.update();
	}
	
	public void render(Graphics g) {
		restart.render(g);
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.BOLD,28));
		g.drawString("Algoritmo Dijkstra", 930, 100);
		g.setFont(new Font("Arial",Font.BOLD,20));
		
		g.drawString("*Clique sobre os nós para definir a origem e o destino.", 600, 500);
		g.drawString("Você pode clicar em RESETAR ou pressionar Enter para", 600, 530);
		g.drawString("começar de novo.", 600, 560);
		g.drawString("Destinação:", 820+x, 25+y);
		g.drawString("Menor Caminho:", 820+x, 95+y);
		g.drawString("Distância:", 1050+x, 95+y);
		g.drawString(dist+" Km", 1050+x, 125+y);
		g.setFont(new Font("Arial",Font.BOLD,18));
		g.drawString(Grafo.getNome(Main.from)+"->"+Grafo.getNome(Main.to), 810+x, 50+y);
		//g.drawString(Grafo.getNome(Main.from)+"->"+Grafo.getNome(Main.to), 900, 120);
		if(Main.from!=-1&&Main.to!=-1) {
			for(int i=0;i<Main.dj.size();i++) {
				g.setFont(new Font("Arial",Font.BOLD,18));
				
				if(i>=Main.dj.size()-1) {
					g.drawString(Main.dj.get(i), 810+x, 130+i*30+y);
				}else {
					g.drawString(Main.dj.get(i)+"->", 810+x, 130+i*30+y);
				}
	        }
		}
	}
}

package main;

import java.awt.Color;
import java.awt.Graphics;

public class Node {
	public  int x, y;
	public boolean marcar = false;
	public boolean clicked = false;
	
	public String nome;
	public static String[] cidades = {"Capinópolis","Ituiutaba","Itumbiara","Centralina","Monte Alegre","Tupaciguara","Douradinhos","Uberlândia","Araguari",
			"Indianápolis","Cascalho Rico","Grupiara","Estrela do Sul","Romaria","São Juliana"};
	public static int num=0;
	public Node(int x, int y) {
		this.x=x;
		this.y=y;
		this.nome=cidades[num];
		num++;
	}
	
	public void update() {
		if(clicked) {
			clicked=false;
			if((Main.from==-1||Main.to==-1)&&Main.mx>=x-26&&Main.mx<=x+26&&Main.my>=y-26&&Main.my<=y+26) {
				marcar=true;
				for(int i =0; i<cidades.length;i++) {
					if(nome==cidades[i]) {
						if(Main.from==-1) {
							Main.from=i;
						}else if(Main.to==-1){
							Main.to=i;
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		//System.out.println(marcar);
		if(marcar) {
			g.setColor(Color.red);
		}else {
			g.setColor(Color.blue);
		}
		g.fillOval(x, y, 26, 26);
		
	}
}

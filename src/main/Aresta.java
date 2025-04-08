package main;

import java.awt.Color;
import java.awt.Graphics;

public class Aresta {
	public Node n1,n2;
	public boolean marcar = false;
	public int peso;
	
	public Aresta(Node n1, Node n2, int peso) {
		this.n1=n1;
		this.n2=n2;
		this.peso=peso;
	}

	public void render(Graphics g) {
		if(marcar) {
			g.setColor(Color.red);
		}else {
			g.setColor(Color.blue);
		}
		g.drawLine(n1.x+13, n1.y+13, n2.x+13,n2.y+13);
	}
}

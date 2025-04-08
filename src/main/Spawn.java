package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spawn {

	public static int WIDTH,HEIGHT;
	
	public Spawn(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT,pixels, 0, WIDTH);
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					if(pixelAtual == 0xFFFF0000){
						Node n = new Node(xx-13,yy-13);
						Main.nodes.add(n);
					}
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		Main.grafo.addAresta(Main.Capinopolis, Main.Centralina, 40);
		Main.grafo.addAresta(Main.Capinopolis, Main.Ituiutaba, 30);
		Main.grafo.addAresta(Main.Ituiutaba, Main.MonteAlegreDeMinas, 85);
		Main.grafo.addAresta(Main.Ituiutaba, Main.Douradinhos, 90);
		Main.grafo.addAresta(Main.Itumbiara, Main.Centralina, 20);
		Main.grafo.addAresta(Main.Itumbiara, Main.Tupaciguara, 55);
		Main.grafo.addAresta(Main.Centralina, Main.MonteAlegreDeMinas, 75);
		Main.grafo.addAresta(Main.Tupaciguara, Main.MonteAlegreDeMinas, 44);
		Main.grafo.addAresta(Main.Tupaciguara, Main.Uberlandia, 60);
		Main.grafo.addAresta(Main.MonteAlegreDeMinas, Main.Centralina, 75);
		Main.grafo.addAresta(Main.MonteAlegreDeMinas, Main.Uberlandia, 60);
		Main.grafo.addAresta(Main.MonteAlegreDeMinas, Main.Douradinhos, 28);
		Main.grafo.addAresta(Main.Douradinhos, Main.Uberlandia, 63);
		Main.grafo.addAresta(Main.Uberlandia, Main.Araguari, 30);
		Main.grafo.addAresta(Main.Uberlandia, Main.Romaria, 78);
		Main.grafo.addAresta(Main.Uberlandia, Main.Indianopolis, 45);
		Main.grafo.addAresta(Main.Araguari, Main.CascalhoRico, 28);
		Main.grafo.addAresta(Main.Araguari, Main.EstrelaDoSul, 34);
		Main.grafo.addAresta(Main.CascalhoRico, Main.Grupiara, 32);
		Main.grafo.addAresta(Main.Grupiara, Main.EstrelaDoSul, 38);
		Main.grafo.addAresta(Main.EstrelaDoSul, Main.Romaria, 27);
		Main.grafo.addAresta(Main.Romaria, Main.SaoJuliana, 28);
		Main.grafo.addAresta(Main.SaoJuliana, Main.Indianopolis, 40);
		
		for(int i=0; i<15;i++) {
			for(int j=0;j<15;j++) {
				Node n1 = Main.nodes.get(i);
				Node n2 = Main.nodes.get(j);
				if(Main.grafo.getPeso(i, j)>0) {
					Aresta a = new Aresta(n1,n2,40);
					Main.arestas.add(a);
				}
			}
		}
		
	}
		
}


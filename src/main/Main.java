package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable, MouseMotionListener, MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH=795+450, HEIGHT=370+340;
	public static final int SCALE =1;
	public static final int fps=1000/60;
	public BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	public BufferedImage mapa;
	public Spawn spawn;
	public static List<Node> nodes;
	public static List<Aresta> arestas;
	public UI ui = new UI();
	public static Grafo grafo;
	public static int mx,my;
	public static final int 
	Capinopolis = 0,
	Ituiutaba=1,
	Itumbiara=2,
	Centralina=3,
	MonteAlegreDeMinas=4,
	Tupaciguara=5,
	Douradinhos=6,
	Uberlandia=7,
	Araguari=8,
	Indianopolis=9,
	CascalhoRico=10,
	Grupiara=11,
	EstrelaDoSul=12,
	Romaria=13,
	SaoJuliana=14;
	public static int from=-1;
	public static int to=-1;
	public static int opt=89;
	public static List<String> dj;

	public Main() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		nodes = new ArrayList<Node>();
		arestas = new ArrayList<Aresta>();
		try {
			mapa = ImageIO.read(getClass().getResource("/mapa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		grafo = new Grafo(15);
		spawn = new Spawn("/mapa.png");
		
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Algoritmo Dijkstra");
		Main main = new Main();
		
		frame.add(main);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(main).start();
	}
	
	public void update() {
		
		for(int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			n.update();
		}
		if(from!=-1&&to!=-1) {
			dj = Main.grafo.path(from, to);
			
			for (String s : dj) {
	            for(int i=0; i<nodes.size();i++) {
	            	Node n = nodes.get(i);
		            if(s==n.nome) {
		            	n.marcar=true;
		            }
	            }
	       }
			
			for(int x=1;x<dj.size();x++) {
				 for(int i=0; i<nodes.size();i++) {
					 for(int j=0; j<nodes.size();j++) {
		            	Node n1 = nodes.get(i);
		            	Node n2 = nodes.get(j);
			            if(dj.get(x)==n1.nome&&dj.get(x-1)==n2.nome&&arestas.size()<opt) {
			            		Aresta a = new Aresta(n1,n2,40);
			            		arestas.add(a);
				            	a.marcar=true;
			            	
			            }
					 }
				 }
			}
		}
		ui.update();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		
		//Render
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(mapa,0, 0,null);

		for(int i = 0; i < arestas.size(); i++) {
			Aresta a = arestas.get(i);
			a.render(g);
		}
		for(int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			n.render(g);
		}
		ui.render(g);
		//
		
		g=bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE, HEIGHT*SCALE,null);
		bs.show();
	}
	
	public void run() {
		while(true) {
			update();
			render();
			try {
				Thread.sleep(fps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			if(!n.clicked) {
				n.clicked=true;
				Main.mx=e.getX();
				Main.my=e.getY();
			}
		}
		
		if(!Restart.pressed) {
			Restart.pressed=true;
			Main.mx=e.getX();
			Main.my=e.getY();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void restart() {
		from=-1;
		to=-1;
		UI.dist=0;
		for(int i=0;i<nodes.size();i++) {
			Node n = nodes.get(i);
			n.marcar=false;
		}
		
		arestas.clear();
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

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			restart();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

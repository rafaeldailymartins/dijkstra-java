package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grafo {
	private int[][] arestas;
	
	public Grafo(int numNodes) {
		arestas = new int[numNodes][numNodes];
	}
	
	public void addAresta(int node1, int node2, int peso) {
		arestas[node1][node2]=peso;
		arestas[node2][node1]=peso;
	}
	
	public void removeAresta(int node1, int node2) {
		arestas[node1][node2]=0;
		arestas[node2][node1]=0;
	}
	
	public int getPeso(int node1, int node2) {
		return arestas[node1][node2];
	}
	
	public List<Integer> getVizinhos(int node){
		List<Integer> vizinhos = new ArrayList<>();
		for(int i=0; i<arestas[node].length;i++) {
			if(arestas[node][i]>0) {
				vizinhos.add(i);
			}
		}
		return vizinhos;
	}
	
	public int closest(int[] peso, Set<Integer> unvisited) {
		double minDist = Integer.MAX_VALUE;
	     int minIndex = 0;
	     for (Integer i : unvisited) {
	    	 if (peso[i] < minDist) {
	    		 minDist = peso[i];
	             minIndex = i;
	    	 }
	     }
	     return minIndex;
	     }
	
	public List<String> path(int ni, int nf) {

        int peso[] = new int[arestas.length];
        int visited[] = new int[arestas.length];
        Set<Integer> unvisited = new HashSet<>();

        peso[ni] = 0;

        for (int i = 0; i < arestas.length; i++) {
            if (i != ni) {
                peso[i] = Integer.MAX_VALUE;
            }
            visited[i] = -1;
            unvisited.add(i);
        }

        while (!unvisited.isEmpty()) {
        	
            int near = closest(peso, unvisited);
            unvisited.remove(near);

            for (Integer vizinho : getVizinhos(near)) {
                int totalCost = peso[near] + getPeso(near, vizinho);
                if (totalCost < peso[vizinho]) {
                	peso[vizinho] = totalCost;
                    visited[vizinho] = near;
                }
            }
            if (near == nf) {
            	UI.dist=peso[near];
                return makeCaminho(visited, near);
            }
        }

        return Collections.emptyList();
    }
	
	   private List<String> makeCaminho(int[] visited, int u) {
	        List<String> path = new ArrayList<>();
	        path.add(getNome(u));
	        while (visited[u] != -1) {
	            path.add(getNome(visited[u]));
	            u = visited[u];
	        }
	        Collections.reverse(path);
	        return path;
	    }
	   
	   public static String getNome(int indice) {
		   if(indice!=-1) {
			   return Node.cidades[indice];
		   }
		   
		   return " ";
	   }
}

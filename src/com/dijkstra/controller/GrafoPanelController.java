package com.dijkstra.controller;

import java.util.ArrayList;

import com.dijkstra.EstruturaEstatica.NodoStatic;
import com.dijkstra.display.GrafoPanel;
import com.dijkstra.model.NodoData;

import prefuse.data.Edge;
/*
 * Controlador do painel grafo
 * @author Valdemir Praxedes Trindade
 */
public class GrafoPanelController {

	private GrafoPanel g;
	
	public GrafoPanelController(GrafoPanel g){
		this.g = g;
	}
	public GrafoPanel getG() {
		return g;
	}

	
	public void addNodo(String nome, ArrayList<Integer> indexNode, ArrayList<Integer> valorPath){
		
		NodoData nodoData = new NodoData();
		
		nodoData.nodo = g.getGrafo().addNode();
		nodoData.nodo.setString("nome", nome);
		if(indexNode.size() > 0){
			for(int x = 0; x < indexNode.size();x++){
				
				Edge e = g.getGrafo().addEdge(nodoData.nodo, NodoStatic.nodoDataLista.get(indexNode.get(x)).nodo);
			    e.setString("valor", valorPath.get(x)+"");
			    nodoData.aresta.add(e);
			}	
		}
		NodoStatic.nodoDataLista.add(nodoData);
		g.Renderizar();
	}
	
	public void uptadeNodo(NodoData nodoData,String nome, ArrayList<Integer> indexNode, ArrayList<Integer> valorPath){
		
		for(Edge a : nodoData.aresta)
		nodoData.nodo.getGraph().removeEdge(a);
		
		nodoData.aresta.clear();
		nodoData.nodo.setString("nome", nome);
		
		if(indexNode.size() > 0){
			for(int x = 0; x < indexNode.size();x++){
				
				Edge e = g.getGrafo().addEdge(nodoData.nodo, NodoStatic.nodoDataLista.get(indexNode.get(x)).nodo);
			    e.setString("valor", valorPath.get(x)+"");
			    nodoData.aresta.add(e);
			}	
		}
		g.Renderizar();
	}
	
	public void removerNodo(NodoData nodoData){
		g.getGrafo().removeNode(nodoData.nodo);
		NodoStatic.nodoDataLista.remove(nodoData);
	}

}

package com.dijkstra.model;

import java.util.ArrayList;

/*
 * 
 */

 public class Nodo  {
		
		private String nome;
		private int valor = Integer.MAX_VALUE;
		private Nodo Anterior;
		private ArrayList<Path> path = new ArrayList<>();
		
		
		public Nodo(Object nome){
			setNome(nome);
		}
		/**
		 * Cria um novo caminho 
		 * @param nodo referencia do proximo nodo
		 * @param valor valor de distancia entre os nodos
		 */
		public  void createPath(Nodo nodo, int valor){
			this.path.add(new Path(nodo,valor));
		}
		public void setPath(ArrayList<Path> path){
			this.path = (ArrayList<Path>) path.clone();
		}
		public int getValor() {
			return valor;
		}
		public void setValor(int valor) {
			this.valor = valor;
		}

		public ArrayList<Path> getPath() {
			return path;
		}
		
		public Path getPath(int i){
			return path.get(i);
		}

		public String getNome() {
			return nome;
		}
		
		public Nodo getAnterior() {
			return Anterior;
		}

		public void setAnterior(Nodo anterior) {
			Anterior = anterior;
		}

		public void setNome(Object nome) {
			this.nome = String.format("%s",nome.toString());
		}
		
		public boolean noPath(){
			return path.isEmpty();
		}
		
		public Nodo clone(){
			Nodo nodo = new Nodo(this.nome);
			nodo.setPath(this.path);
			nodo.setAnterior(this.Anterior);
			nodo.setValor(valor);
			return nodo;
		}

}

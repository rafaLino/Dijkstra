package com.dijkstra.model;


 public class Path  {
		
		private int distancia;
		private Nodo nodo;
		
		
		public Path(Nodo nodo,int valor){
			setDistancia(valor);
			setNodo(nodo);
		}
		public int getDistancia() {
			return distancia;
		}
		public Nodo getNodo() {
			return nodo;
		}
		public void setDistancia(int distancia) {
			this.distancia = distancia;
		}
		private void setNodo(Nodo nodo) {
			this.nodo = nodo;
		}
		
		
}

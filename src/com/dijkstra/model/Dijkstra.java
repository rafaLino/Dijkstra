package com.dijkstra.model;


import java.util.Collection;
import java.util.LinkedList;


public  class Dijkstra {
			/**
			 * calcula as menores distancias de nodos a partir do nodo raiz.
			 * @param e Nodo  origem
			 * @param f Nodo  destino
			 * @return String contendo menor caminho
			 * @author lino
			 */
		public static String findPath(Nodo e,Nodo f){
			Nodo i = e.clone();
			i.setValor(0); 
			LinkedList<Nodo> list = new LinkedList<>();
			list.add(i);    //adiciona nodo inicial
			while(!list.isEmpty()){
				Nodo nodo = list.poll(); // retira 1º item da lista
				
				for(Path path : nodo.getPath()){ // anda por todos os caminhos do nodo
					Nodo aux = path.getNodo();
					int distancia = path.getDistancia() + nodo.getValor();
					if(distancia < aux.getValor()){ // compara as distancias e atualiza
						aux.setValor(distancia); //atualiza distancia caminho que liga o proximo nodo
							aux.setAnterior(nodo); // atualiza referencia do nodo anterior do proximo nodo
							list.add(aux); // adiciona o proximo nodo a lista
					}
				}
			}
			return shortestPath(f.clone());
			
		}
			/**
			 * Determina o menor caminho
			 * @param f Nodo Destino.
			 * @return String contendo o caminho
			 */
			public static String shortestPath(Nodo f){
				LinkedList<Nodo> path = new LinkedList<>();
					for(Nodo nodo = f; nodo != null; nodo = nodo.getAnterior()){ // só para quando termina
						path.addFirst(nodo);
					}
					StringBuilder sb = new StringBuilder("[");
					if(f.getValor()==Integer.MAX_VALUE){
						return null;
					}
					else{
					for (Nodo nodo : path) {
						sb.append(nodo.getNome());
						sb.append(" ");
					}
					}
					sb.append("]");
				return sb.toString();
			}
		
		
		
		
}

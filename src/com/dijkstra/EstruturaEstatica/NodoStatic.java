package com.dijkstra.EstruturaEstatica;

import java.util.ArrayList;
import java.util.List;
import com.dijkstra.model.NodoData;

/**
 * Fornece uma lista de nodos que reprensenta as informações que estão no grafo
 * @author Valdemir Praxedes Trindade
 *
 */
public class NodoStatic {
		
	/**
	 * Lista que contém todos os nodos e arestas
	 */
	public static ArrayList<NodoData> nodoDataLista = new ArrayList<NodoData>();
		
		
		/**
		 * 
		 * @param nome procura pelo nome
		 * @return NodoData se estiver na lista
		 * @return null se não encontrar nada
		 */
		public static NodoData getNodoController(String nome){
			for(NodoData nodoData : nodoDataLista){
				if(nodoData.pesNodo(nome) != null){
					return nodoData;
				}
			}
			return null;
		}
}

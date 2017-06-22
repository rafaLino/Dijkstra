package com.dijkstra.view;


import java.awt.FlowLayout;
import java.awt.Label;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.dijkstra.EstruturaEstatica.NodoStatic;
import com.dijkstra.display.GrafoPanel;
import com.dijkstra.handler.Calc_Handler;
import com.dijkstra.model.Nodo;
import prefuse.data.Node;

/**
 *Tela responsável pelo uso do algoritmo de dijkstra 
 * @author Valdemir Praxedes Trindade
 *
 */
public class Calc_view extends template_tela {


	private Nodo[] nodo;
	private JButton btn_calc;
	private JComboBox comboBoxNodosListaInicio, comboBoxNodosListaDestino;
	private GrafoPanel grafo;
	
	public Calc_view(GrafoPanel grafoPanel)  {
	  super("Calcular");
	  this.grafo = grafo;
	}
    /**
     * Cria uma lista com os nomes dos nodos
     *@return String[] - Lista de nomes 
     */
	private String[] nodosToString(){
		
		String[] s = new String[NodoStatic.nodoDataLista.size()];
		for(int x = 0; x < s.length;x++){
			s[x] = NodoStatic.nodoDataLista.get(x).nodo.getString("nome");
		}
		return s;
	}
	public void gerarNodo(){
		nodo = factoryNodos();
	}
	/**
	 * Lista de nodos para se usado no algoritmo de dijkstra, reconstrói os nodos
	 * com base na implementação no framework prefuse 
	 * @return Nodo[]
	 */
	private Nodo[] factoryNodos(){
		//map é usando para conseguir fazer a implementação da aresta
		Map<Node, Nodo> mapnodo = new HashMap<Node, Nodo>();
		
		Nodo[] n = new Nodo[NodoStatic.nodoDataLista.size()];
		//Criando os nodos
		for(int x = 0; x < n.length;x++){
			n[x] = new Nodo(NodoStatic.nodoDataLista.get(x).nodo.getString("nome"));
			mapnodo.put(NodoStatic.nodoDataLista.get(x).nodo, n[x]);
		}
		//Criando as arestas
		for(int x = 0; x < n.length;x++){
			for(int y = 0; y < NodoStatic.nodoDataLista.get(x).aresta.size();y++)
			n[x].createPath(mapnodo.get(NodoStatic.nodoDataLista.get(x).aresta.get(y).getAdjacentNode(NodoStatic.nodoDataLista.get(x).nodo)), Integer.parseInt(NodoStatic.nodoDataLista.get(x).aresta.get(y).getString("valor")));
		}
		return n;
	}

	@Override
	protected void Init_Configuracoes() {
		// TODO Auto-generated method stub
		setLayout(new FlowLayout());
	}

	@Override
	protected void Init_Instancias() {
		// TODO Auto-generated method stub
		
		String[] nodoslista = nodosToString();
		
		comboBoxNodosListaInicio = new JComboBox(nodoslista);
		comboBoxNodosListaDestino = new JComboBox(nodoslista);
		btn_calc = new JButton("Calcular");
		
		add(new Label("Inicio: "));
		add(comboBoxNodosListaInicio);
		add(new Label("Destino: "));
		add(comboBoxNodosListaDestino);
		add(btn_calc);	
	}

	@Override
	protected void Init_Eventos() {
	   new Calc_Handler(this);
		
	}

	public Nodo[] getNodo() {
		return nodo;
	}

	public JButton getBtn_calc() {
		return btn_calc;
	}

	public JComboBox getComboBoxNodosListaInicio() {
		return comboBoxNodosListaInicio;
	}

	public JComboBox getComboBoxNodosListaDestino() {
		return comboBoxNodosListaDestino;
	}
	public GrafoPanel getGrafo(){
		return grafo;
	}
}

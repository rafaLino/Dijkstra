package com.dijkstra.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.dijkstra.display.GrafoPanel;
import com.dijkstra.handler.Main_Handler;

/**
 * Tela principal do projeto
 * @author Valdemir Praxedes Trindade
 *
 */
public class Main_view extends template_tela{

	private JButton btn_calc, btn_add;
	private GrafoPanel grafo;


	public Main_view() {	
		super("Algoritmo de Dijkstra");	
	}

	public static void main(String... arvs){
		
		Main_view main = new Main_view();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.pack();
		
		main.setVisible(true);
	}



	@Override
	protected void Init_Configuracoes() {
		setLayout(new BorderLayout());
		setSize(800, 600);
		
	}

	@Override
	protected void Init_Instancias() {
		
		grafo = new GrafoPanel(); 
		btn_calc = new JButton("Calcular");
		btn_add = new JButton("Adicionar");
		Panel rodape = new Panel();
		rodape.setBackground(Color.WHITE);
		rodape.add(btn_add, BorderLayout.CENTER);
		rodape.add(btn_calc, BorderLayout.CENTER);
		this.add(rodape, BorderLayout.PAGE_END);
		this.add(grafo);
	}

	@Override
	protected void Init_Eventos() {
		new Main_Handler(this);
	}
	
	
	public JButton getBtn_calc() {
		return btn_calc;
	}

	public JButton getBtn_add() {
		return btn_add;
	}

	public GrafoPanel getGrafo() {
		return grafo;
	}
	
	
	
	
}

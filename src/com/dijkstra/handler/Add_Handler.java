package com.dijkstra.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.dijkstra.EstruturaEstatica.NodoStatic;
import com.dijkstra.controller.GrafoPanelController;
import com.dijkstra.view.Add_view;
/**Classe responsável por tratar os eventos da tela Add_view    
 * 
 * @author Valdemir Praxedes Trindade
 * 
 *
 */
public class Add_Handler implements ActionListener {

	private final Add_view add;
	public Add_Handler(Add_view add) {
		// TODO Auto-generated constructor stub
		this.add = add;
		
		this.add.getBtnOk().addActionListener(this);
		this.add.getBtnCancela().addActionListener(this);
	}
	 /**
     * Captura de eventos
     */
	@Override
	public void actionPerformed(ActionEvent Evento) {
		
		if(Evento.getSource() == add.getBtnOk())btnOk_Click();
		else if(Evento.getSource() == add.getBtnCancela())btnCancela_Click();
			
		
	}
	/**
	 * Fecha a janela
	 */
	private void btnCancela_Click(){
		add.dispose();
	}
	/**
	 * Adiciona um novo nodo no grafo
	 */
	private void btnOk_Click(){
		String nome = add.getTfldNome().getText().trim().toString();
		if(NodoStatic.getNodoController(nome) != null || nome.equals("")){
			JOptionPane.showMessageDialog(null, "Já existe um nodo com esse nome ou o nome esta vazio");
			return;
		}
		try{
		ArrayList<Integer> indexNode = new ArrayList<Integer>();
		ArrayList<Integer> valorPath = new ArrayList<Integer>();
		
		
		for(int x = 0; x < add.getCheckBox().length;x++){
			if(add.getCheckBox()[x].isSelected()){
				int valor = Integer.parseInt(add.getTfldvalor()[x].getText().toString());
				if(valor <= 0)   throw new Exception("O valor do campo "+add.getCheckBox()[x].getText()+" esta menor ou igual a zero");
				
				indexNode.add(x);
				valorPath.add(valor);
			}
		}
		
		new GrafoPanelController(add.getGp()).addNodo(nome, indexNode, valorPath);
		add.dispose();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}

package com.dijkstra.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.dijkstra.EstruturaEstatica.NodoStatic;
import com.dijkstra.controller.GrafoPanelController;
import com.dijkstra.view.Add_view;
import com.dijkstra.view.Update_view;
/**Classe responsável por tratar os eventos da tela update_view    
 * 
 * @author Valdemir Praxedes Trindade
 * 
 *
 */
public class Update_Handler implements ActionListener {

	private final Update_view update;
	public Update_Handler(Update_view Update) {
		// TODO Auto-generated constructor stub
		this.update = Update;
		
		this.update.getBtnOk().addActionListener(this);
		this.update.getBtnExcluir().addActionListener(this);
	}
	 /**
     * Captura de eventos
     */
	@Override
	public void actionPerformed(ActionEvent Evento) {
		
		if(Evento.getSource() == update.getBtnOk())btnAtualizar_Click();
		else if(Evento.getSource() == update.getBtnExcluir())btnExcluir_Click();
			
		
	}
	/**
	 * Fecha a janela
	 */
	private void btnExcluir_Click(){
		int resultado = JOptionPane.showConfirmDialog(null, "Tem certeza se quer excluir o nodo "+update.getNodo().nodo.getString("nome")+"?","Alert",JOptionPane.YES_OPTION);
		
		
		if(resultado == 0){
		  new GrafoPanelController(update.getGp()).removerNodo(update.getNodo());
		  update.dispose();
		}
	}
	/**
	 * Adiciona um novo nodo no grafo
	 */
	private void btnAtualizar_Click(){
		
		String nome = update.getTfldNome().getText().trim().toString();
		if(!update.getNodo().nodo.get("nome").equals(nome)){
		if(NodoStatic.getNodoController(nome) != null || nome.equals("")){
			JOptionPane.showMessageDialog(null, "Já existe um nodo com esse nome ou o nome esta vazio");
			return;
				}
		}
		try{
		ArrayList<Integer> indexNode = new ArrayList<Integer>();
		ArrayList<Integer> valorPath = new ArrayList<Integer>();
		
		for(int x = 0; x < update.getCheckBox().length;x++){
			if(update.getCheckBox()[x].isSelected()){
				int valor = Integer.parseInt(update.getTfldvalor()[x].getText().toString());
				if(valor <= 0)   throw new Exception("O valor do campo "+update.getCheckBox()[x].getText()+" esta menor ou igual a zero");
				indexNode.add(x);
				valorPath.add(valor);
			}
		}
		new GrafoPanelController(update.getGp()).uptadeNodo(this.update.getNodo(), nome, indexNode, valorPath);
		update.dispose();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}

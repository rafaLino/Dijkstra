package com.dijkstra.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.dijkstra.EstruturaEstatica.NodoStatic;
import com.dijkstra.model.Dijkstra;
import com.dijkstra.view.Calc_view;

/**
 * Classe responsável por tratar os eventos da tela Calc_view   
 * @author Valdemir Praxedes Trindade
 *
 */
public class Calc_Handler implements ActionListener, ItemListener {
	private final Calc_view calc;
	private int indexInicio;
	private int indexDestino;
	public Calc_Handler( Calc_view calc) {
		// TODO Auto-generated constructor stub
		this.calc = calc;
		this.calc.getBtn_calc().addActionListener(this);
		this.calc.getComboBoxNodosListaInicio().addItemListener(this);
		this.calc.getComboBoxNodosListaDestino().addItemListener(this);
	}
	 /**
     * Captura de eventos
     */
	@Override
	public void itemStateChanged(ItemEvent Evento) {
		// TODO Auto-generated method stub
		if(Evento.getSource() == calc.getComboBoxNodosListaInicio())ComboBoxInicio_Changed();
		else if(Evento.getSource() == calc.getComboBoxNodosListaDestino())ComboBoxDestino_Changed();
	}
	 /**
     * Captura de eventos
     */
	@Override
	public void actionPerformed(ActionEvent Evento) {
		// TODO Auto-generated method stub
		btnCalc_Click();
	}
	/**
	 * Inicia o calculo e mostra na tela
	 */
	private void btnCalc_Click(){
		StringBuilder s;
		calc.gerarNodo();
		String caminho = Dijkstra.findPath(calc.getNodo()[indexInicio], calc.getNodo()[indexDestino]);
		if(caminho == null)s = new StringBuilder("Não existe caminho de "+calc.getNodo()[indexDestino].getNome()+" para "+calc.getNodo()[indexDestino].getNome());
		else{
			
			s = new StringBuilder("Caminho: ");
			s.append(caminho);
			s.append("\nValor: "+calc.getNodo()[indexDestino].getValor());
		}
		JOptionPane.showMessageDialog(null, s.toString());
		
	}
	
	/**
	 * Seleciona o nodo de inicio
	 */
	private void ComboBoxInicio_Changed(){
		indexInicio = calc.getComboBoxNodosListaInicio().getSelectedIndex();
	}
	/**
	 * Seleciona o nodo de destino
	 */
	private void ComboBoxDestino_Changed(){
		indexDestino = calc.getComboBoxNodosListaDestino().getSelectedIndex();
	}

}

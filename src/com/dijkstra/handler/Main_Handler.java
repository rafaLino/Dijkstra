package com.dijkstra.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.dijkstra.EstruturaEstatica.NodoStatic;
import com.dijkstra.view.Add_view;
import com.dijkstra.view.Main_view;
import com.dijkstra.view.Calc_view;

/**Classe responsável por tratar os eventos da tela principal    
 * 
 * @author Valdemir Praxedes Trindade
 * 
 *
 */
public class Main_Handler implements ActionListener {
	
	private final Main_view tela;
	
	
	public Main_Handler(Main_view tela) {
		
		//Usei a tela como paramento, para facilitar a escrita de código. 
		//A ideia que pensei era fazer a classe handler  uma classe interna,
		//mas por organização preferir dividir em arquivo separados   
		
		this.tela = tela;
		this.tela.getBtn_add().addActionListener(this);
		this.tela.getBtn_calc().addActionListener(this);
		
	}
	
    /**
     * Captura de eventos
     */
	@Override
	public void actionPerformed(ActionEvent Evento) {
		// TODO Auto-generated method stub
		if(Evento.getSource() == tela.getBtn_add())btnAdicionar_Click();
		else if(Evento.getSource() == tela.getBtn_calc())btnCalcular_Click();
	}
	/**
	 * Exibe a janela add_view
	 */
	private void btnAdicionar_Click(){
		Add_view add = new Add_view(tela.getGrafo());
		add.pack();
		add.setSize(450, 300);
		add.setVisible(true);
	}
	/**
	 * Exibe a janela calc_view
	 */
	private void btnCalcular_Click(){
		
		if(!NodoStatic.nodoDataLista.isEmpty()){
			Calc_view calc = new Calc_view(tela.getGrafo());
			calc.pack();
			calc.setSize(200, 200);
			calc.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "Não existe nenhum nodo adicionado");
		}
	}

}

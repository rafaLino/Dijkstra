package com.dijkstra.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.dijkstra.EstruturaEstatica.NodoStatic;
import com.dijkstra.display.GrafoPanel;
import com.dijkstra.handler.Add_Handler;

/**
 * Tela responsável pela criação de nodos e arestas.
 * @author Valdemir Praxedes Trindade
 *
 */
public class Add_view extends template_tela {
	
	private JButton btnOk, btnCancela;
	private JTextField tfldNome;
	private GrafoPanel gp;
	private JCheckBox[] checkBox;
	private JTextField[] tfldvalor;
	
	
	public Add_view(GrafoPanel gp)  {
		super("Adicionar Nodo");
		this.gp = gp;  
	}
	
	/**
	 * Cria um painel com todos os checkBoxes e textFields  
	 * @return JPanel
	 */
	private JPanel addCheckBoxAndTldvalor(){
	
		JPanel combopainel = new JPanel();
		combopainel.setLayout(new BoxLayout(combopainel, BoxLayout.Y_AXIS));
		
		for(int x = 0; x < checkBox.length;x++){
			JPanel panelAresta = new JPanel();
			panelAresta.add((new Label("Nodo:")));
			panelAresta.add(checkBox[x]);
			panelAresta.add((new Label("Valor:")));
			panelAresta.add(tfldvalor[x]);
			
			combopainel.add(panelAresta);
		}
		return combopainel;
	}
	/**
	 * Inicia todos os checkBoxes com a mesma quantidade de nodos criado
	 */
	private void Instancias_checkBox(){
		 checkBox = new JCheckBox[NodoStatic.nodoDataLista.size()];
		for(int x = 0; x < NodoStatic.nodoDataLista.size();x++){
			checkBox[x] = new JCheckBox(NodoStatic.nodoDataLista.get(x).nodo.getString("nome"));
		}
	}
	/**
	 * Inicia todos os textFields com a mesma quantidade de nodos criado
	 */
	private void Instancias_tfldvalor(){
		tfldvalor = new JTextField[NodoStatic.nodoDataLista.size()];
		for(int x = 0; x < NodoStatic.nodoDataLista.size();x++){
			tfldvalor[x] = new JTextField(2);
			tfldvalor[x].setText("0");
		}
	}

	@Override
	protected void Init_Configuracoes() {
		// TODO Auto-generated method stub
		setLayout(new FlowLayout());
		setLocation(100,100);
	}

	@Override
	protected void Init_Instancias() {
		// TODO Auto-generated method stub
		
		Instancias_checkBox();
		Instancias_tfldvalor();
		
		btnOk = new JButton("OK");
		btnCancela = new JButton("Cancela");
		
		tfldNome = new JTextField(10);
		JScrollPane scpainel = new JScrollPane(addCheckBoxAndTldvalor());
		
		scpainel.setPreferredSize(new Dimension(230,110));
		
		this.add(new JLabel("Nome "));
		this.add(tfldNome);
		this.add(btnOk);
		this.add(btnCancela);
		this.add(scpainel);
		
	}

	@Override
	protected void Init_Eventos() {
		new Add_Handler(this);
		
	}
	
	public JButton getBtnOk() {
		return btnOk;
	}
	public JButton getBtnCancela() {
		return btnCancela;
	}
	public JTextField getTfldNome() {
		return tfldNome;
	}
	public GrafoPanel getGp() {
		return gp;
	}
	public JCheckBox[] getCheckBox() {
		return checkBox;
	}
	public JTextField[] getTfldvalor() {
		return tfldvalor;
	}

}

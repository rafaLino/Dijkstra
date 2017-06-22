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
import com.dijkstra.handler.Update_Handler;
import com.dijkstra.model.NodoData;

import prefuse.data.Edge;

/**
 * Tela responsável pela atualização e exclusão de nodos e arestas.
 * @author Valdemir Praxedes Trindade
 *
 */
public class Update_view extends template_telaUpdate {
	
	private JButton btnOk, btnExcluir;
	private JTextField tfldNome;

	private JCheckBox[] checkBox;
	private JTextField[] tfldvalor;
	
	public Update_view(GrafoPanel gp,NodoData nodoData)  {
		
		super("Alterar Nodo",  gp, nodoData);
		
	}

	
	public NodoData getNodo(){
		return nodoData;
	}
	public GrafoPanel getGrafo(){
		return gp;
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
			if(NodoStatic.nodoDataLista.get(x).nodo.equals(nodoData.nodo)){
				checkBox[x].setEnabled(false);
				continue;
			}
				
			for(Edge a : nodoData.aresta){
				if(a.getAdjacentNode(nodoData.nodo).equals(NodoStatic.nodoDataLista.get(x).nodo)){
					checkBox[x].setSelected(true);
				}
			}
		}
	}
	/**
	 * Inicia todos os textFields com a mesma quantidade de nodos criado
	 */
	private void Instancias_tfldvalor(){
		
		tfldvalor = new JTextField[NodoStatic.nodoDataLista.size()];
		for(int x = 0; x < NodoStatic.nodoDataLista.size();x++){
			tfldvalor[x] = new JTextField(2);
			
			if(NodoStatic.nodoDataLista.get(x).nodo.equals(nodoData.nodo)){
				tfldvalor[x].setEnabled(false);
				continue;
			}
			
			tfldvalor[x].setText("0");
			for(Edge a : nodoData.aresta){
			if(a.getAdjacentNode(nodoData.nodo).equals(NodoStatic.nodoDataLista.get(x).nodo)){
				tfldvalor[x].setText(a.getString("valor"));
			}
			}
			
		}
	}

	@Override
	protected void Init_Configuracoes() {
		// TODO Auto-generated method stub
		setLayout(new FlowLayout());
		setLocation(100,100);
	}



	
	
	public JButton getBtnOk() {
		return btnOk;
	}
	public JButton getBtnExcluir() {
		return btnExcluir;
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

	@Override
	protected void Init_InstanciasUP() {
		// TODO Auto-generated method stub
		Instancias_checkBox();
		Instancias_tfldvalor();
		
		btnOk = new JButton("Atualizar");
		btnExcluir = new JButton("Excluir");
		
		tfldNome = new JTextField(10);
		tfldNome.setText(nodoData.nodo.getString("nome"));
		JScrollPane scpainel = new JScrollPane(addCheckBoxAndTldvalor());
		
		scpainel.setPreferredSize(new Dimension(230,110));
		
		this.add(new JLabel("Nome "));
		this.add(tfldNome);
		this.add(btnOk);
		this.add(btnExcluir);
		this.add(scpainel);
	}

	@Override
	protected void Init_EventosUP() {
		// TODO Auto-generated method stub
		new Update_Handler(this);
	}
}

abstract class template_telaUpdate extends template_tela{

	protected GrafoPanel gp;
	protected NodoData nodoData;
	public template_telaUpdate(String titulo,GrafoPanel gp,NodoData nodoData) {
		super(titulo);
		this.gp = gp;
		this.nodoData = nodoData;
		Init_InstanciasUP();
		Init_EventosUP();
		
	}

	protected abstract void Init_InstanciasUP();
	
	@Override
	protected void Init_Instancias() {
		
		
	}
	
	@Override
	protected void Init_Eventos() {
		// TODO Auto-generated method stub
		
	}
	protected abstract void Init_EventosUP();

	
	
}

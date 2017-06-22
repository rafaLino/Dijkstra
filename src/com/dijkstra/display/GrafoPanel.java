package com.dijkstra.display;

import java.awt.event.MouseEvent;
import com.dijkstra.EstruturaEstatica.NodoStatic;
import com.dijkstra.EventPrefuse.FinalDecoratorEdgeLayout;
import com.dijkstra.EventPrefuse.FinalDecoratorLayout;
import com.dijkstra.model.NodoData;
import com.dijkstra.view.Update_view;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.controls.DragControl;
import prefuse.controls.FocusControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.data.Table;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.render.Renderer;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
/**
 * Painel responsável por toda a parte visual do grafo
 * @author Valdemir Praxedes Trindade
 *
 */
public class GrafoPanel extends Display {


	/**
	 * Um grafo modela uma rede de nós conectados por uma coleção de arestas.
	 * Ambos os nós e as arestas podem ter qualquer número de campos de dados
	 * associados. Além disso, as arestas são direcionadas ou não direcionadas, 
	 * indicando uma direcionalidade possível da conexão. 
	 * Cada vantagem tem um nó de origem e um nó de destino, para uma aresta direcionada,
	 * isso indica a direcionalidade, para uma aresta não direcionada, 
	 * isso é apenas um artefato da ordem em que os nós foram especificados 
	 * quando adicionados ao grafo.
	 */
	private Graph grafo;

	public GrafoPanel() {
		
		super(new Visualization());
		Init_Instancia();
        Init_Tamanho();
        Init_Eventos();
        setHighQuality(true);
    
	}
	
	public Graph getGrafo(){
		return grafo;
	}

	private void Init_Instancia(){
		//Criando uma tabela de dados de nodos e arestas
		Table nodo = new Table();
		Table aresta = new Table(0,1);
		
		//Configurando as colunas
		nodo.addColumn("nome", String.class);
		nodo.addColumn("cor", String.class);
		aresta.addColumn(Graph.DEFAULT_SOURCE_KEY, int.class);
        aresta.addColumn(Graph.DEFAULT_TARGET_KEY, int.class);
        aresta.addColumn("valor", String.class);
        
        
        grafo = new Graph(nodo, aresta,true);
       
        m_vis.addGraph("grafo", grafo);
        m_vis.setInteractive("grafo.edges", null, true);
       
       
        final Schema DECORATOR_SCHEMA = PrefuseLib.getVisualItemSchema();
        
        DECORATOR_SCHEMA.setDefault(VisualItem.INTERACTIVE, false); 
        DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, 
                                    ColorLib.rgb(0, 0, 0)); 
        DECORATOR_SCHEMA.setDefault(VisualItem.FONT, 
                                    FontLib.getFont("Tahoma",12));
        
        m_vis.addDecorators("nodedec", "grafo.nodes", DECORATOR_SCHEMA);
        m_vis.addDecorators("edgedec", "grafo.edges", DECORATOR_SCHEMA);
	}
	
	private void Init_Tamanho(){
		  setSize(700, 500);
	        pan(260, 250);
	}
	private void Init_Eventos(){
		
			
	        addControlListener(new DragControl(){
	        	@Override
	    		public void itemClicked(VisualItem item, MouseEvent e) {
	    			// TODO Auto-generated method stub
	    			super.itemClicked(item, e);
	    			String nome = item.getString("nome");
	    			NodoData nodoData = NodoStatic.getNodoController(nome);
	    			
							if(nodoData !=  null){
			    				Update_view view = new Update_view(GrafoPanel.this, nodoData);
			    				view.setSize(450, 300);
			    				view.pack();
			    				view.setVisible(true);
			    			}
	    		
	        	}
	        	
	        });
	        
	        addControlListener(new PanControl());
	        addControlListener(new ZoomControl());
	        addControlListener(new FocusControl());
	}
	
	
	public void Renderizar(){
		  Renderer nodeR = new ShapeRenderer(50);
		
	        EdgeRenderer edgeR = new EdgeRenderer(prefuse.Constants.EDGE_TYPE_CURVE, prefuse.Constants.EDGE_ARROW_FORWARD);
	        
	        DefaultRendererFactory drf = new DefaultRendererFactory();
	        drf.setDefaultRenderer(nodeR);
	        drf.setDefaultEdgeRenderer(edgeR);
	        
	        drf.add(new InGroupPredicate("nodedec"),  new LabelRenderer("nome"));
	        drf.add(new InGroupPredicate("edgedec"), new LabelRenderer("valor"));
	        
	        m_vis.setRendererFactory(drf);
	        m_vis.setValue("grafo.nodes", null, VisualItem.SHAPE,
	                new Integer(Constants.SHAPE_ELLIPSE));
	    
	        int[] palette = new int[] {
	            ColorLib.rgb(255,180,180), ColorLib.rgb(190,190,255)
	        };
	        
	             

	        ColorAction nStroke = new ColorAction("grafo.nodes", VisualItem.STROKECOLOR);
	        nStroke.setDefaultColor(ColorLib.gray(100));
	        DataColorAction nFill = new DataColorAction("grafo.nodes", "cor",
	                Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
	            ColorAction edges = new ColorAction("grafo.edges",
	                VisualItem.STROKECOLOR, ColorLib.gray(10));
	            ColorAction arrow = new ColorAction("grafo.edges",
	               VisualItem.FILLCOLOR, ColorLib.gray(20));
	            ActionList color = new ActionList();
	            color.add(nStroke);
	            color.add(nFill);
	            color.add(edges);
	            color.add(arrow);
	        

	        ActionList layout = new ActionList(ActionList.INFINITY);
	        
	        layout.add(new FinalDecoratorLayout("nodedec"));
	        layout.add(new FinalDecoratorEdgeLayout("edgedec"));
	        layout.add(new RepaintAction());
	    

	        m_vis.putAction("color", color);
	        m_vis.putAction("layout", layout);
	        
	        m_vis.run("color");
	        m_vis.run("layout");
	        
	}
	


}

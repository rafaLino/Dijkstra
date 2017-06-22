package com.dijkstra.view;

import javax.swing.JFrame;


/**
 * Fornece um modelo para as telas, Isso significa que todas as telas tem um modelo que obrigatoriamente tem que ser seguido. 
 * São especificado métodos que serão começado com o prefixo “init_”, cada método tem seu objetivo.
 * Init_Configuracoes: O tipo de layout, tamanho da tela e informações que diz respeito a configuração são definida aqui;
 * Init_Instancias: Aqui todas as instancias dos componente serão criadas e adicionada no painel.
 * Init_Eventos: Define o tratamento de eventos.   
 *
 * @author Valdemir Praxedes Trindade
 *
 */
public abstract class template_tela extends JFrame{

	/**
	 * Configura o titulo e define a ordem que os metodos serão chamados
	 * @param titulo String - titulo da tela
	 */
	public template_tela(String titulo) {
		super(titulo);
		
		Init_Configuracoes();
		Init_Instancias();
		Init_Eventos();
	}

	/**
	 * O tipo de layout, tamanho da tela e informações que diz respeito a configuração são definida aqui.
	 */
	protected abstract void Init_Configuracoes();
	
	/**
	 * Aqui todas as instancias dos componente serão criadas e adicionada no painel.
	 */
	protected abstract void Init_Instancias();
	/**
	 * Define o tratamento de eventos.
	 */
	protected abstract void Init_Eventos();
	
}
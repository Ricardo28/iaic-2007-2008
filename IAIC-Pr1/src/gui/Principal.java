package gui;

import programa.Controlador;

public class Principal {
	
	/**
	 * Instancia por defecto de la clase principal
	 */
	public Principal() {		
	}
	
	/**
	 * Metodo main que inicia el programa
	 * @param args
	 */
	public static void main(String args[]){
		Visual vista = new Visual();
		Controlador controlador = new Controlador();
		vista.asociarControlador(controlador);
		controlador.asociarVista(vista);
	}

}

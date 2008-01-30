package gui;

import programa.*;

public class Principal {
	
	/**
	 * instancia por defecto de la calse principal
	 *
	 */
	public Principal() {		
	}
	
	/**
	 * metodo main que inicializa el programa
	 * @param args
	 */
	public static void main(String args[]){
		Vista vista = new Vista();
		Controlador controlador = new Controlador();
		vista.asociarControlador(controlador);
		controlador.asociarVista(vista);			
	}

}

package gui;

import programa.Controlador;
import Ventana.*;

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
		Visual visual = new Visual();
		Controlador controlador = new Controlador();
		visual.asociarControlador(controlador);
		controlador.asociarVista(visual);
	}

}

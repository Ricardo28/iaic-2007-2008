package juegos;

import aima.search.Successor;

import java.util.Enumeration;
import java.util.Vector;

public class Palillos extends Juego{
	
	private int palillos;
	
	private int turno;
	
	/**
	 * Constructora con parametros del estado de los palillos correspondiente
	 * @param palillos numero de palillos que hay en el juego
	 * @param turno turno en que se encuentra el juego
	 */
	public Palillos(int palillos, int turno){
		this.palillos = palillos;
		this.turno = turno;
	}
	
	/**
	 * Heuristica del juego
	 * @return la heuristica diseñada para este juego
	 */
	public float h() {
	     return palillos+turno;
	}
	
	/**
	 * Indica si un estado es solucion del problema o no
	 * @return true si ha llegado a solucion o false en caso contrario
	 */
	public boolean isGoal() {
	      return  (palillos == 0 && (turno%2 == 0));
	      
	   }
	
	/**
	 * Genera los sucesores del nodo actual
	 * @return los sucesores de ese nodo
	 */
	public Enumeration<Successor> successors() { 
	    Vector<Successor> successorVec = new Vector<Successor>();
	    String operador = "";
	    nodosExpandidos++;
	    for (int i=1; i<=3; i++) {
			 if (palillos-i >= 0) {
				 operador = "Se quitan " + i + " palillos";
		         Palillos nuevoEstado = new Palillos(palillos-i, turno+1);
		         successorVec.addElement(new Successor(nuevoEstado, operador, 1));
		      }
	    }
		return successorVec.elements();
	}
	
	/**
	 * crea un string de mensaje del estado en el que se encuentra
	 * @return un String
	 */
	public String toString() {
	      return "Quedan "+ palillos +" palillos.";
	}
	
	/**
	 * Prueba el problema con todas las estrategias
	 * @param args
	 */
	public static void main(String[] args) {
		Palillos m = new Palillos(6,1);
		System.out.println(m);
		for (int i = 1; i <= 6; i++)
			m.resolver(i);
	}
	
}

package juegos;

import aima.search.Heuristic;
import aima.search.State;
import aima.search.Successor;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 
 * @author Usuario
 *
 */
public class Palillos implements State, Heuristic{
	
	private int palillos;
	
	private int nivel;
	
	static int nodosExpandidos = 0;
	
	/**
	 * constructora con parametros del estado de los palillos correspondiente
	 * @param palillos numero de palillos que hay en el juego
	 * @param nivel nivel en que se encuentra de expansion
	 */
	public Palillos(int palillos,int nivel){
		this.palillos=palillos;
		this.nivel=nivel;
	}
	
	/**
	 * heuristica del juego
	 * @return la heuristica diseñada para este juego
	 */
	public float h() {

	     return 6-palillos;
	            
	  }
	
	/**
	 * dice si un numero nivel es par
	 * @param nivel numero a evaluar 
	 * @return devuelve true si es par sino false
	 */
	private boolean multiploDos(int nivel){
		int mul=nivel;
		while(mul>=2){
			mul=mul-2;
		}
		if (mul==0) return true;
		return false;
	}
	
	/**
	 * indica si un estado es solucion del problema o no
	 * @return true si ha llegado a solucion o false en caso contrario
	 */
	public boolean isGoal() {
	      return  ((palillos==0)&&(multiploDos(nivel)));
	      
	   }
	
	/**
	 * dice si un estado es valido
	 * @return true si es valido false en caso contrario
	 */
	private boolean isValid(){
		return true;
	}
	
	/**
	 * genera los sucesores del nodo actual
	 * @return devuelve los sucesores de ese nodo en un Enumeration
	 */
	public Enumeration<Successor> successors() { 

	    Vector<Successor> successorVec = new Vector<Successor>();
	    int i=0;
	    int nivel2=nivel+1;
	    String operador="";
	    nodosExpandidos++;
	    for (i=1; i<=3; i++) {
		  if ( palillos-i>=0 ) {
			     operador = "Quito " + i + "palillos";
	             Palillos tryState = new Palillos(palillos-i,nivel2);
	             if (tryState.isValid()) {
	            	 successorVec.addElement(new Successor(tryState, operador, 1 ));
	             }
		  }
	    }
		return successorVec.elements();
	  
	}
	
	/**
	 * devuelve el numero de nodos expandidos
	 * @return devuelve el numero de nodos expandidos
	 */
	public int dameNodosExpandidos(){
		 return nodosExpandidos;
	}
	
	/**
	 * pone el numero de nodos expandidos
	 * @param n indica el numero de nodos expandidos que se quiere poner en dicha clase
	 */ 
	public void ponNodosExpandidos(int n){
		 nodosExpandidos = n;
	}
	
	/**
	 * crea un string de mensaje del estado en el que se encuentra
	 * @return un String
	 */
	public String toString() {
	      return "Quedan "+ palillos +" palillos.";
	}
	
}

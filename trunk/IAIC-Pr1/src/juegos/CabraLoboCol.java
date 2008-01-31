package juegos;

import aima.search.*;
import java.util.*;

/**
 * 
 * @author Usuario
 *
 */
public class CabraLoboCol implements State,Heuristic{
	
	private int lobo;
	
	private int cabra;
	
	private int col;
	
	private int granjero;
	
	private static int nodosExpandidos = 0;
	
	/**
	 * crea una instancia del juego de la cabra el lobo y la col
	 * @param lobo indica la posicion del lobo
	 * @param cabra indica la posicion de la cabra
	 * @param col indica la posicion de la col
	 * @param granjero indica la posicion del granjero
	 */
	public CabraLoboCol(int lobo, int cabra, 
		   int col, int granjero){
		this.lobo = lobo;
		this.cabra = cabra;
		this.col = col;
		this.granjero = granjero;
	}
	
	/**
	 * indica si un estado es valido
	 * @return true si es valido false en caso contrario
	 */
	protected boolean isValid(){
		if (lobo == cabra && lobo == (1 - granjero))
			return false;
		if (cabra == col && cabra == (1 - granjero))
		  	return false;
		
		return true;		
	}
	
	/**
	 * indica si un estado es solucion o no
	 * @return true en caso de ser solucion false ne caso contrario
	 */
	public boolean isGoal(){
		return lobo ==0 && cabra == 0 &&
				col == 0 && granjero == 0;
	}
	
	/**
	 * genera el numero de posibles sucesores de ese estado actual
	 * @return los sucesores del estado actual
	 */
	 public Enumeration<Successor> successors(){
	 	
	 	 Vector<Successor> successorVec = new Vector<Successor>();
	 	 
	 	 int operadores;
	 	 int iLobo=1;
	 	 int iCabra=1;
	 	 int iCol=0;
	 	 int iGranjero=0;
	 	 String operador = "";
	 	 nodosExpandidos++;
	 	 for(operadores = 0; operadores <=3; operadores++){
	 	 	if(operadores == 0 && lobo == granjero){
	 	 		iLobo = (1 - this.lobo);
	 	 		iCabra = this.cabra;
	 	 		iCol = this.col;
	 	 		iGranjero = (1 - this.granjero);
	 	 		operador = "cruzaLobo";
	 	 	}
	 	 	if(operadores == 1 && cabra == granjero){
	 	 		iLobo = this.lobo;
	 	 		iCabra = (1 - this.cabra);
	 	 		iCol = this.col;
	 	 		iGranjero = (1 - this.granjero);
	 	 		operador = "cruzaCabra";
	 	 	}
	 	 	if(operadores == 2 && col == granjero){
	 	 		iLobo = this.lobo;
	 	 		iCabra = this.cabra;
	 	 		iCol = (1 - this.col);
	 	 		iGranjero = (1 - this.granjero);
	 	 		operador = "cruzaCol";
	 	 	}
	 	 	if(operadores == 3){
	 	 		iLobo = this.lobo;
	 	 		iCabra = this.cabra;
	 	 		iCol = this.col;
	 	 		iGranjero = (1 - this.granjero);
	 	 		operador = "cruzaGranjero";
	 	 	}
	 	 	
	 	 	CabraLoboCol nuevoEstado = 
	 	 			new CabraLoboCol(iLobo,iCabra,iCol,iGranjero);
	 	 			
	 	 	if(nuevoEstado.isValid()){
	 	 		successorVec.addElement(new Successor(nuevoEstado, operador, 1 )); 
	 	 	}
	 	 }//end del for
	 	 
	 	 return successorVec.elements();
	 	 
	 }// end del metodo
	 
	 /**
	  * genera la heuristica dle estado actual
	  * @return la heuristica del estado actual
	  */
	 public float h() {
	 	return (float)lobo + (float)cabra + (float)granjero + (float)col;
	 }
	 
	/**
	 * genera un mensaje indicando el estado actual
	 * @return devuelve un String que indica el estado actual
	 */
	public String toString() {
		return "(" + lobo + "," + cabra + "," + col + "," + granjero + ")";
	}
	 
	/**
	 * devuelve el numero de nodos exapndidos
	 *
	 */
	public int dameNodosExpandidos(){
		return nodosExpandidos;
	}
	
	/**
	 * actualiza el numero de nodos expandidos al indicado por el parametro
	 */
	public void ponNodosExpandidos(int n){
		nodosExpandidos = n;
	}
	
}
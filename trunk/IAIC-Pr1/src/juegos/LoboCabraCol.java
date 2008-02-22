package juegos;

import aima.search.Successor;

import java.util.Vector;
import java.util.Enumeration;

public class LoboCabraCol extends Juego{
	
	private int lobo;
	
	private int cabra;
	
	private int col;
	
	private int granjero;
	
	/**
	 * Crea una instancia del juego de la cabra, el lobo y la col
	 * @param lobo indica la posicion del lobo
	 * @param cabra indica la posicion de la cabra
	 * @param col indica la posicion de la col
	 * @param granjero indica la posicion del granjero
	 */
	public LoboCabraCol(int lobo, int cabra, int col, int granjero){
		this.lobo = lobo;
		this.cabra = cabra;
		this.col = col;
		this.granjero = granjero;
	}
	
	/**
	 * Indica si un estado es valido
	 * @return true si es valido
	 */
	protected boolean isValid(){
		if (lobo == cabra && lobo == (1 - granjero))
			return false;
		if (cabra == col && cabra == (1 - granjero))
		  	return false;
		return true;		
	}
	
	/**
	 * Indica si un estado es solucion
	 * @return true en caso de ser solucion
	 */
	public boolean isGoal(){
		return lobo ==0 && cabra == 0 && col == 0 && granjero == 0;
	}
	
	/**
	 * Genera el numero de posibles sucesores de ese estado actual
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
	 	 	else if(operadores == 1 && cabra == granjero){
	 	 		iLobo = this.lobo;
	 	 		iCabra = (1 - this.cabra);
	 	 		iCol = this.col;
	 	 		iGranjero = (1 - this.granjero);
	 	 		operador = "cruzaCabra";
	 	 	}
	 	 	else if(operadores == 2 && col == granjero){
	 	 		iLobo = this.lobo;
	 	 		iCabra = this.cabra;
	 	 		iCol = (1 - this.col);
	 	 		iGranjero = (1 - this.granjero);
	 	 		operador = "cruzaCol";
	 	 	}
	 	 	else if(operadores == 3){
	 	 		iLobo = this.lobo;
	 	 		iCabra = this.cabra;
	 	 		iCol = this.col;
	 	 		iGranjero = (1 - this.granjero);
	 	 		operador = "cruzaGranjero";
	 	 	}
	 	 	
	 	 	LoboCabraCol nuevoEstado = 
	 	 			new LoboCabraCol(iLobo,iCabra,iCol,iGranjero);
	 	 		
	 	 	if (nuevoEstado.isValid()){
	 	 		successorVec.addElement(new Successor(nuevoEstado, operador, 1 )); 
	 	 	}
	 	 }
	 	 return successorVec.elements();
	 }
	 
	 /**
	  * Genera la heuristica dle estado actual
	  * @return la heuristica del estado actual
	  */
	 public float h() {
	 	return (float)lobo + (float)cabra + (float)granjero + (float)col;
	 }
	 
	/**
	 * Genera un mensaje indicando el estado actual
	 * @return devuelve un String que indica el estado actual
	 */
	public String toString() {
		return "(L:" + lobo + ", Ca:" + cabra + ", Co:" + col + ", G:" + granjero +
              ")   ~~~~   (L:" + (1-lobo) + ", Ca:" + (1-cabra) + ", Co:" + (1-col) +
              ", G:" + (1-granjero) + ")";
	}
	
	/**
	 * Prueba el problema con todas las estrategias
	 * @param args
	 */
	public static void main(String[] args) {
		LoboCabraCol m = new LoboCabraCol(1,1,1,1);
		System.out.println(m);
		for (int i = 1; i <= 6; i++)
			m.resolver(i);
	}
	 
}
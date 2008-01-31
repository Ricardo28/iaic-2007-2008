package juegos;

import aima.search.Heuristic;
import aima.search.State;
import aima.search.Successor;
import java.util.*;

/* Problema del Mono:
*  0: el mono o caja se encuentran en la puerta.
*  1: el mono o caja se encuentran en la ventana.
*  2: el mono o caja se encuentran en el centro.
*/
/**
 * @author Usuario
 */
public class Mono implements State,Heuristic{

	private static int nodosExpandidos = 0;
	
	private int posH;
	
	private int caja;
	
	private boolean posV;
	
	private boolean platano;
	
	/**
	 * crea un estado del juego del mono segun el avance del juego
	 * 
	 */
	public Mono(int posH, boolean posV, int caja, boolean platano){
		this.posH = posH;
		this.posV = posV;
		this.caja = caja;
		this.platano = platano;
	}	
	
	/**
	 * modicfica la posicion del mono encima de la caja o en el suelo
	 * @param p indica la posicion del mono
	 */
	 public void ponPosH(int p){
		this.posH = p;
	 }	
	 
	 /**
	  * indica la posicion de la ventana
	  * @param p
	  */
	public void ponPosV(boolean p){
		this.posV = p;
	}
	
	public void ponCaja(int p){
		this.caja = p;
	}	
	
	public void ponPlatano(boolean p){
		this.platano = p;
	}
	
	public int damePosH(){
		return this.posH;
	}
	
	public boolean damePosV(){
		return this.posV;
	}
	
	public int damePosCaja(){
		return this.caja;
	}
	
	public boolean damePlatano(){
		return this.platano;
	}

	//Métodos
	/**
	 * indica si un estado es solucion
	 * @return true si es final el estado false en caso contrario
	 */
	public boolean isGoal(){
		return platano == true;
	}
	
	/**
	 * genera los sucesores de ese estado
	 * @return Enumeration con los sucesores generados
	 */
	public Enumeration<Successor> successors(){

	 	 Vector<Successor> successorVec = new Vector<Successor>();

	 	 int operadores;
	 	 int iPosH=0;
	 	 boolean iPosV=false;
	 	 int iCaja=1;
	 	 boolean iPlatano=false;
	 	 String operador = "";
	 	 nodosExpandidos++;

	 	 for(operadores = 0; operadores <=7; operadores++){
	 		if(operadores == 0 && posH != caja &&
	 				posV == false && posH !=1){
	 			iPosH=1;
	 			iPosV=this.posV;
	 			iCaja=this.caja;
	 			iPlatano=this.platano;
	 			operador ="andaAVentana";
	 		}

	 		if(operadores == 1 && posH != caja &&
	 				posV == false && posH !=2){
	 			iPosH=2;
	 			iPosV=this.posV;
	 			iCaja=this.caja;
	 			iPlatano=this.platano;
	 			operador ="andaACentro";
	 		}

	 		if(operadores == 2 && posH != caja &&
	 				posV == false && posH !=0){
	 			iPosH=0;
	 			iPosV=this.posV;
	 			iCaja=this.caja;
	 			iPlatano=this.platano;
	 			operador ="andaAPuerta";
	 		}

	 		if(operadores == 3 && posH == caja &&
	 				posV == false && posH !=1){
	 			iPosH=1;
	 			iPosV=this.posV;
	 			iCaja=1;
	 			iPlatano=this.platano;
	 			operador ="empujaCajaAVentana";
	 		}

	 		if(operadores == 4 && posH == caja &&
	 				posV == false && posH !=2){
	 			iPosH=2;
	 			iPosV=this.posV;
	 			iCaja=2;
	 			iPlatano=this.platano;
	 			operador ="empujaCajaACentro";
	 		}

	 		if(operadores == 5 && posH == caja &&
	 				posV == false && posH !=0){
	 			iPosH=0;
	 			iPosV=this.posV;
	 			iCaja=0;
	 			iPlatano=this.platano;
	 			operador ="empujaCajaAPuerta";
	 		}

	 		if(operadores == 6 && posH == caja &&
	 				posV == false){
	 			iPosH=this.posH;
				iPosV=true;
				iCaja=this.caja;
				iPlatano=this.platano;
				operador ="subeCaja";
	 		}

	 		if(operadores == 7 && posV == true &&
	 				caja == 2 && platano == false){
	 			iPosH=this.posH;
				iPosV=this.posV;
				iCaja=this.caja;
				iPlatano=true;
				operador ="cojePlatano";
	 		}

	 		Mono nuevoEstado = new Mono(iPosH,iPosV,iCaja,iPlatano);

	 		successorVec.addElement(new Successor(nuevoEstado, operador, 1 ));
	 	}

	 	return successorVec.elements();
	}
	
	/**
	 * genera la heuristica del estado en el que se encuentra
	 * @return devuelve la heuristica correspondiente al estado actual
	 */
	public float h() {
		float h=0;

		if(posH != 2)
			h++;
		if(posV == false)
			h++;
		if(caja != 2)
			h++;
		if(platano == false)
			h++;

		return h;

	}
	
	/**
	 * genera un mensaje del estado en el que se encuentra
	 * @return un string con el mensaje que especifica en el estado que se encuentra
	 */
	public String toString() {
		int v,p;

		if(posV == true) v=1; else v=0;
		if(posV == true) p=1; else p=0;

	      return "(" + posH + "," +
	                 + v + "," +
	                 + caja + "," +
	                 +  p + ")";

	  }
	
	/**
	 * devuelve el numero de nodos expandidos
	 * @return  devuelve el numero de nodos expandidos
	 */
	public int dameNodosExpandidos(){
		 return nodosExpandidos;
	 }
	
	 /**
	  * actualiza el numero de nodos expandidos
	  * @param n actualiza el numero de nodos expandidos a n
	  */
	 public void ponNodosExpandidos(int n){
		 nodosExpandidos = n;
	 }
	 
}


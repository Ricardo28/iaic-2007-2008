package juegos;

import aima.search.*;
import java.util.*;

/**
 * 
 * @author Usuario
 *
 */
public class OchoPuzzle implements State,Heuristic{
	
	private static int nodosExpandidos = 0;
	
	private int x; //coordenada x de la casilla libre
	
	private int y; //coordenada y de la casilla libre
	
	private int [][] tablero; //tablero que representa el puzzle;

	/**
	 * genera un nodo de ocho puzzle con el estado del juego en ese momentop
	 * @param tablero estado actual del problema
	 * @param x situacion y del blanco
	 * @param y situacion y del hueco
	 */
	public OchoPuzzle(int [][] tablero, int x, int y){
		this.tablero = new int [3][3];
		for(int i = 0; i<=2; i++)
			for(int j = 0; j<=2; j++)
				this.tablero[i][j] = tablero[i][j];
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * indica si un estado es valido
	 * @return true si es valido false en caso contrario
	 */
	protected boolean isValid(){
		return true;
	}
	
	/**
	 * indica si el estado es el estado final
	 * @return true en caso de ser el final de la busqueda false en caso contraio
	 */
	public boolean isGoal(){
		return 	tablero[0][0] == 1 &&
			 	tablero[0][1] == 2 && 
			 	tablero[0][2] == 3 &&
			 	tablero[1][0] == 8 &&
			 	tablero[1][1] == 0 &&  
			 	tablero[1][2] == 4 &&  
			 	tablero[2][0] == 7 &&
			 	tablero[2][1] == 6 &&   
			 	tablero[2][2] == 5;
	}
	
	 /**
	  * genera los sucesores de ese estado en el que nos encontramos segun los distintos operadores
	  * @return devuelve los sucesores generados
	  */
	 public Enumeration successors(){
	 	
	 	Vector successorVec = new Vector<OchoPuzzle>();
	 	
	 	String operador = "";
	 	nodosExpandidos++;
	 	int miX = this.x;
	 	int miY = this.y;
	 	for(int operadores = 0; operadores<=3; operadores++){
	 		boolean opAplicado = false;
	 		OchoPuzzle nuevoEstado = 
	 	 			new OchoPuzzle(this.tablero,this.x,this.y);
	 		//Mover hueco hacia arriba
	 		if (operadores == 0 && miX>0){
	 			int temp = nuevoEstado.tablero[miX-1][miY];
	 			nuevoEstado.tablero[miX-1][miY] = 0;
	 			nuevoEstado.tablero[miX][miY] = temp;
	 			nuevoEstado.x = miX - 1;
	 			operador = "Arriba";
	 			opAplicado = true;
	 		}
	 		//Mover hueco hacia abajo
	 		if (operadores == 1 && miX<2){
	 			int temp = nuevoEstado.tablero[miX+1][miY];
	 			nuevoEstado.tablero[miX+1][miY] = 0;
	 			nuevoEstado.tablero[miX][miY] = temp;
	 			nuevoEstado.x = miX + 1;;
	 			operador = "Abajo";	
	 			opAplicado = true;
	 		}
	 		//Mover hueco hacia la izquierda
	 		if (operadores == 2 && miY>0){
	 			int temp = nuevoEstado.tablero[miX][miY-1];
	 			nuevoEstado.tablero[miX][miY-1] = 0;
	 			nuevoEstado.tablero[miX][miY] = temp;
	 			nuevoEstado.y = miY - 1;
	 			operador = "Izquierda";	
	 			opAplicado = true;
	 		}
	 		//Mover hueco hacia la derecha
	 		if (operadores == 3 && miY<2){
	 			int temp = nuevoEstado.tablero[miX][miY+1];
	 			nuevoEstado.tablero[miX][miY+1] = 0;
	 			nuevoEstado.tablero[miX][miY] = temp;
	 			nuevoEstado.y = miY + 1;
	 			operador = "Derecha";
	 			opAplicado = true;
	 		}
	 		
	  	 	if(opAplicado && nuevoEstado.isValid()){ // && !contains(successorVec,(OchoPuzzle)nuevoEstado)){
	  	 		/*System.out.println("Padre:" + this.toString());
	 	 		System.out.println(nuevoEstado.toString());
	 	 		System.out.println(nuevoEstado.h());*/
	  	 		successorVec.addElement(
			  		new Successor(
                          (OchoPuzzle)nuevoEstado, operador, 1 )
			 );
			} 
	 	}// end del for
	 	
	 	return successorVec.elements();
	 }// end del metodo.
	 
	 boolean contains(Vector<OchoPuzzle> v, OchoPuzzle o){
		 boolean contiene = false;
		 int tam = v.size();
		 for (int i = 0; i<tam; i++){
			 if(this.iguales(v.elementAt(i))){
				 contiene = true;
				 break;
			 }
		}
		return contiene;
	 }
	 
	 /**
	  * dice si dos ocho puzzles son iguales en el estado en el que estan
	  * @param o ochopuzzle correspondiente
	  * @return true en caso de ser iguales false en caso contrario
	  */
	 boolean iguales(OchoPuzzle o){
		 boolean igual = false;
		 for (int i=0;i<3;i++){
			 for(int j=0;j<3;j++){
				 igual = tablero[i][j]==o.tablero[i][j];
				 if(igual)break;
			 }
		 }
		 return igual;
	 }
	 
	 /**
	  * genera la heuristica para este problema
	  * @return devuelve la heuristica correpondiente float
	  */
	 public float h() {
	 	float hVal = 0;
	 	if(tablero[0][0]!=1)
	 		hVal++;
	 	if(tablero[0][1]!=2)
	 		hVal++;
	 	if(tablero[0][2]!=3)
	 		hVal++;
	 	if(tablero[1][0]!=8)
	 		hVal++;
	 	if(tablero[1][2]!=4)
	 		hVal++;
	 	if(tablero[2][0]!=7)
	 		hVal++;
	 	if(tablero[2][1]!=6)
	 		hVal++;
	 	if(tablero[2][2]!=5)
	 		hVal++;
	 		
	 	return hVal;
	 }
	 
	 /**
	  * genera el mensaje del estado en el que nos encontramos
	  * @return String con el mensaje del estado en el que se encuentra
	  */
	 public String toString(){
	 	String tabla = "\n(";
	 	for(int i = 0; i<=2; i++){
	 		for(int j = 0; j<=2; j++){
	 			tabla+=" "+tablero[i][j] + " ";
	 			if (j==2 && i!=2)
	 			 	tabla+=")"+ "\n"+ "(";
	 			if (j==2 && i == 2)
	 					tabla+=")"+ "\n";
	 		}
	 	}
	 	return tabla;	
	} 
	 
	 /**
	  * devuelve el numero de nodos expandidos
	  * @return int
	  */
	 public int dameNodosExpandidos(){
		 return nodosExpandidos;
	 }
	 
	 /**
	  * pone el numero de nodos expandidos segun el numero indicado en parametros
	  * @param n int numero de nodos expandidos
	  */
	 public void ponNodosExpandidos(int n){
		 nodosExpandidos = n;
	 }
	
}
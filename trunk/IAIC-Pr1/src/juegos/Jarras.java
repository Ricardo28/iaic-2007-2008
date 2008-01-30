package juegos;

import aima.search.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 
 * @author Usuario
 *
 */
public class Jarras implements State,Heuristic{

	private static int nodosExpandidos = 0;
	
	private int j3;
	
	private int j4;
	
	/**
	 * crea una instancia de un estado de las jarras
	 * @param g3 indica la cantidad que contiene la jarra de 3
	 * @param g4 indica la cantidad que contiene la jarra de 4
	 */
	public Jarras(int g3, int g4){
		this.j3= g3;
		this.j4= g4;
	}

	/**
	 * indica si un estado es solucion
	 * @return true si es solucion false en caso contrario
	 */
	public boolean isGoal(){
		return j4 == 2;
	}
	
	/**
	 * genera los sucesores del estado actual
	 * @return los sucesores del estado actual
	 */
	public Enumeration successors(){

	 	Vector successorVec = new Vector();
	 	int operadores;
	 	int iJ3 = 0;
	 	int iJ4 = 0;
	 	String operador = "";
	 	nodosExpandidos++;

	 	for (operadores = 0; operadores <=5; operadores++){

	 		//vaciar garrafa de 3L
	 		//llenar garrafa de 4L
	 		if (operadores == 1 && j4<4 ){
	 			iJ4 = 4;
	 			iJ3 = this.j3;
	 			operador ="LLenar jarra de 4 L";
	 		}

	 		//vaciar garrafa de 3L
	 		if (operadores == 3 && j3>0 ){
	 			iJ3 = 0;
	 			iJ4 = this.j4;
	 			operador ="Vaciar jarra de 3 L";
	 		}

	 		//vaciar garrafa de 4L
	 		if (operadores == 2 && j4>0 ){
	 			iJ4 = 0;
	 			iJ3 = this.j3;
	 			operador ="Vaciar jarra de 4 L";
	 		}

	 		//llenar garrafa de 3L
	 		if (operadores == 0 && j3<3 ){
	 			iJ3 = 3;
	 			iJ4 = this.j4;
	 			operador ="LLenar jarra de 3 L";
	 		}

	 		//verter garrafa de 4L sobre garrafa de 3L
	 		if (operadores == 5 && j4>0 && j3<3 ){
	 			if (j3+j4 <= 3){
	 				iJ3=this.j3+this.j4;
	 				//iJ4=0;
	 			}
	 			else {
	 				//iJ4 = this.j4 - (3-this.j4);
	 				iJ3 = 3;
	 			}
	 			iJ4 = this.j4-(iJ3-this.j3);
	 			operador ="Verter jarra de 4 L sobre la de 3 L";
	 		}

	 		//verter garrafa de 3L sobre garrafa de 4L
	 		if (operadores == 4 && j3>0 && j4<4 ){
	 			if (j3+j4 <= 4){
	 				iJ4=this.j3+this.j4;
	 				//iJ3=0;
	 			}
	 			else {
	 				//iJ3 = this.j3 - (4-this.j3);
	 				iJ4 = 4;
	 			}
	 			iJ3 = this.j3 -(iJ4-this.j4);
	 			operador ="Verter jarra de 3 L sobre la de 4 L";
	 		}

	 		Jarras nuevoEstado = new Jarras(iJ3,iJ4);
	 		if (nuevoEstado.isValid())
	 			successorVec.addElement(new Successor(nuevoEstado, operador, 1 ));
	 	}
	 	return successorVec.elements();
	}
	
	/**
	 * dice si un estado es valido y no procude conflicto
	 * @return true si es valido false en caso contrario
	 */
	protected boolean isValid() {
		return (nodosExpandidos<50000);
	}
	 
	/**
	 * devuelve la heuristica de ese estado
	 */
	public float h() {
		float h = 0;
		h = (2-this.j4);
		if (h>0)
			return h;
		else
			return (this.j4-2);
	}
	
	/**
	 * devuelve un mensaje representativo del estado en el que se encuentra
	 */
	public String toString() {
		return "( Jarra de 3:" + j3 + "; Jarra de 4:" + j4 +" )";
	}
	
	/**
	 * devuelve el numero de nodos exapndidos
	 * @return devuelve el numero de nodos expandidos
	 */
	public int dameNodosExpandidos(){
		return nodosExpandidos;
	}
	
	/**
	 * actualiza el numero de nodos exandidos
	 * @param n es el numero de nodos expandidos al que se va a actualizar
	 */
	public void ponNodosExpandidos(int n){
		nodosExpandidos = n;
	}
	 
}


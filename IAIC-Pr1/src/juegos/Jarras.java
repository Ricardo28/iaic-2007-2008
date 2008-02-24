package juegos;

import aima.search.Successor;

import java.util.Enumeration;
import java.util.Vector;

import programa.Controlador;

/**
 * Juego de las jarras de 3 y 4 litros, objetivo 2 litros en la garrafa de 4
 * @author Miguel Angel Díaz
 * @author David Martín
 * @author Alberto Vaquero
 */
public class Jarras extends Juego{

	/**
	 * Litros en la jarra de capacidad 3
	 */
	private int j3;
	
	/**
	 * Litros en la jarra de capacidad 4
	 */
	private int j4;
	
	/**
	 * Crea una instancia de un estado de las jarras
	 * @param j3 indica la cantidad que contiene la jarra de 3
	 * @param j4 indica la cantidad que contiene la jarra de 4
	 * @param c Controlador. Poner null para imprimir por pantalla
	 */
	public Jarras(int j3, int j4, Controlador c){
		this.j3 = j3;
		this.j4 = j4;
		cont = c;
	}

	/**
	 * Indica si un estado es solucion
	 * @return true si es solucion false en caso contrario
	 */
	public boolean isGoal(){
		return j4 == 2;
	}
	
	/**
	 * Genera los sucesores del estado actual
	 * @return los sucesores del estado actual
	 */
	public Enumeration<Successor> successors(){

	 	Vector<Successor> successorVec = new Vector<Successor>();
	 	int operadores;
	 	int iJ3 = 0;
	 	int iJ4 = 0;
	 	String operador = "";
	 	nodosExpandidos++;

	 	for (operadores = 0; operadores <=5; operadores++){

	 		//llenar garrafa de 3L
	 		if (operadores == 0 && j3<3 ){
	 			iJ3 = 3;
	 			iJ4 = j4;
	 			operador ="LLenar jarra de 3 L";
	 		}
	 		//llenar garrafa de 4L
	 		if (operadores == 1 && j4<4 ){
	 			iJ4 = 4;
	 			iJ3 = j3;
	 			operador ="LLenar jarra de 4 L";
	 		}
	 		//vaciar garrafa de 4L
	 		if (operadores == 2 && j4>0 ){
	 			iJ4 = 0;
	 			iJ3 = j3;
	 			operador ="Vaciar jarra de 4 L";
	 		}
	 		//vaciar garrafa de 3L
	 		if (operadores == 3 && j3>0 ){
	 			iJ3 = 0;
	 			iJ4 = j4;
	 			operador ="Vaciar jarra de 3 L";
	 		}
	 		//verter garrafa de 3L sobre garrafa de 4L
	 		if (operadores == 4 && j3>0 && j4<4 ){
	 			if (j3+j4 <= 4){
	 				iJ4=j3+j4;
	 				iJ3=0;
	 			}
	 			else {
	 				iJ4 = 4;
	 				iJ3 = (j3+j4)-4;
	 			}
	 			operador ="Verter jarra de 3 L sobre la de 4 L";
	 		}
	 		//verter garrafa de 4L sobre garrafa de 3L
	 		if (operadores == 5 && j4>0 && j3<3 ){
	 			if (j3+j4 <= 3){
	 				iJ3=j3+j4;
	 				iJ4=0;
	 			}
	 			else {
	 				iJ3 = 3;
	 				iJ4 = j4-(iJ3-j3);
	 			}
	 			operador ="Verter jarra de 4 L sobre la de 3 L";
	 		}
	 		Jarras nuevoEstado = new Jarras(iJ3,iJ4,cont);
	 		if (nuevoEstado.isValid())
	 			successorVec.addElement(new Successor(nuevoEstado, operador, 1 ));
	 	}
	 	return successorVec.elements();
	}
	
	/**
	 * Dice si un estado es valido y no produce conflicto
	 * @return true si es valido false en caso contrario
	 */
	protected boolean isValid() {
		return nodosExpandidos < 5000;
	}
	 
	/**
	 * Devuelve la heuristica de ese estado
	 */
	public float h(){
		return (Math.abs(j4-2));
	}
	
	/**
	 * Devuelve un mensaje representativo del estado en el que se encuentra
	 */
	public String toString() {
		return "\t( Jarra de 3:" + j3 + "; Jarra de 4:" + j4 +" )";
	}
	
	/**
	 * Prueba el problema con todas las estrategias
	 * @param args
	 */
    public static void main(String[] args){
		Jarras m = new Jarras(0, 0, null);
		System.out.println(m);
		for (int i=1; i<=6; i++)
			m.resolver(i);
	}
	 
}


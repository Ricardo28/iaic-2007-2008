package juegos;

import aima.search.Successor;

import java.util.Enumeration;
import java.util.Vector;

import programa.Controlador;

public class MisionerosYCanibales extends Juego {

	private int misionerosIzq;
   
	private int canibalesIzq;
   
	private int barcaIzq;
   
	/**
     * Crea una instancia del estado actual del problema de los misioneros y los canivales
     * @param misionerosIzq numero de misioneros en la izquierda
     * @param canibalesIzq numero de canibales en la izquierda
     * @param barcaIzq si esta la barca en la izquierda o derecha
     * @param c Controlador. Poner null para imprimir por pantalla
	 */
    public MisionerosYCanibales(int misionerosIzq, int canibalesIzq, int barcaIzq, Controlador c) {
    	this.misionerosIzq = misionerosIzq;
    	this.canibalesIzq = canibalesIzq;
    	this.barcaIzq = barcaIzq;
    	cont = c;
    } 
   
    /**
	 * Indica si un estado es valido
	 * @return true si el estado es valido false en caso contrario
	 */
	protected boolean isValid() {
    	if (barcaIzq<0 || barcaIzq>1 || misionerosIzq<0 || misionerosIzq>3
    								 || canibalesIzq<0 	|| canibalesIzq>3)
    		return false;
    	if (misionerosIzq > 0 && canibalesIzq > misionerosIzq)
    		return false;
    	if ((3-misionerosIzq > 0) && (3-canibalesIzq > 3-misionerosIzq))
        	return false;
        return true;
	} 
   
   	/**
   	 * Indica si un estado es solucion o no
   	 * @return true si es final o false en caso contrario
   	 */
   	public boolean isGoal() {
   		return  misionerosIzq == 0 && canibalesIzq == 0 && barcaIzq == 0 ;
   	}

	/**
	 * Genera los sucesores del esatdo actual
	 * @return devuelve los sucesores del estado actual en Enumeration
	 * 
	 */
	public Enumeration<Successor> successors() { 

		int iMisioneros, iCanibales, iBarco;

		Vector<Successor> successorVec = new Vector<Successor>();
		nodosExpandidos++;
    
		for (iBarco= -1; iBarco<=1; iBarco+=2){
			for (iMisioneros=0; iMisioneros<=2; iMisioneros++){
				for (iCanibales=0; iCanibales+iMisioneros<=2; iCanibales++){
					if ((iMisioneros+iCanibales>0) && (barcaIzq+iBarco>=0 && barcaIzq+iBarco<=1)) {
						MisionerosYCanibales nuevoEstado = 
	  						new MisionerosYCanibales(misionerosIzq+iBarco*iMisioneros,
	  								canibalesIzq+iBarco*iCanibales,
	  								barcaIzq+iBarco, cont);
						if (nuevoEstado.isValid()) {
    	  					successorVec.addElement(new Successor(
    	  								nuevoEstado, 
    	  							    "("+iMisioneros+","+iCanibales+","+iBarco+")",
    	  							    1)); 
    	  				}
					}  
				}
			}
		}
		return successorVec.elements();
	}
   
	/**
     * Genera la heuristica del estado actual
     * @return un float con la heuristica del estado actual
     */
	public float h() {
	   float hVal = (float) misionerosIzq + (float) canibalesIzq - (float) barcaIzq;
	   return hVal;
    }

   	/**
   	 * Genera un mensaje que representa el estado actual del problema
   	 * @return un String con el mensaje del estado actual
   	 */
   	public String toString() {
    	return "\t(M:" + misionerosIzq + ", C:" + canibalesIzq + ", B:" + barcaIzq + ")   ~~~~   (M:" + 
      		   (3-misionerosIzq) + ", C:" + (3-canibalesIzq) + ", B:" + (1-barcaIzq) + ")";
	}
   	
   	/**
	 * Prueba el problema con todas las estrategias
	 * @param args
	 */
    public static void main(String[] args){
		MisionerosYCanibales m = new MisionerosYCanibales(3,3,1,null);
		System.out.println(m);
		for (int i=1; i<=6; i++)
			m.resolver(i);
	}
   	
}

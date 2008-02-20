package juegos;

import aima.search.*;

import java.util.*;

/**
 * 
 * @author Usuario
 *
 */
public class MisionerosYCanibales implements State, Heuristic {

   private int misionerosIzq;
   
   private int canibalesIzq;
   
   private int barcaIzq;
   
   private int misionerosDer;
   
   private int canibalesDer;
   
   private int barcaDer;
   
   private static int nodosExpandidos;
   
   /**
    * crea una instancia del estado actual del problema de los misioneros y los canivales
    * @param misionerosIzq numero de misioneros en la izquierda
    * @param canibalesIzq numero de canibales en la izquierda
    * @param barcaIzq si esta la barca en la izquierda o derecha
    * @param misionerosDer numero de misioneros en la derecha 
    * @param canibalesDer numero de canibales en la derecha
    * @param barcaDer indica si la barca esta en la derecha
    */
   public MisionerosYCanibales(int misionerosIzq, int canibalesIzq, int barcaIzq,
                  int misionerosDer, int canibalesDer, int barcaDer) {

       this.misionerosIzq = misionerosIzq;
       this.canibalesIzq = canibalesIzq;
       this.barcaIzq = barcaIzq;

       this.misionerosDer = misionerosDer;
       this.canibalesDer = canibalesDer;
       this.barcaDer = barcaDer;
   } 
   
   /**
    * crea una instancia del estado actual del problema de los misioneros y los canivales
    * @param misionerosIzq numero de misioneros en la izquierda
    * @param canibalesIzq numero de canibales en la izquierda
    * @param barcaIzq si esta la barca en la izquierda o derecha
    */
   public MisionerosYCanibales(int misionerosIzq, int canibalesIzq, int barcaIzq) {
       this(misionerosIzq, canibalesIzq, barcaIzq, 0, 0, 0);
   }

   /**
    * indica si un estado es valido
    * @return true si el estado es valido false en caso contrario
    */
   protected boolean isValid() {

         if (barcaIzq < 0 || barcaDer < 0 ||
             misionerosIzq < 0 || misionerosDer < 0 ||
             canibalesIzq < 0 || canibalesDer < 0) return false;

         if (misionerosIzq > 0 
                 && canibalesIzq > misionerosIzq) return false;

         if (misionerosDer > 0 
                 && canibalesDer > misionerosDer) return false;

         return true;
   } 
   
   	/**
   	 * indica si un estado es solucion o no
   	 * @return true si es final o false en caso contrario
   	 */
   	public boolean isGoal() {
   		return  misionerosIzq == 0 && canibalesIzq == 0 && barcaIzq == 0 ;
   	}

	/**
	 * genera los sucesores del esatdo actual
	 * @return devuelve los sucesores del estado actual en Enumeration
	 * 
	 */
	public Enumeration<Successor> successors() { 

		int iMisioneros, iCanibales, iBarco;

		Vector<Successor> successorVec = new Vector<Successor>();
		nodosExpandidos++;
    
		for (iBarco= -1; iBarco<=1; iBarco+=2) {
			for (iMisioneros=0; iMisioneros<=2; iMisioneros++) {
				for (iCanibales=0; iCanibales+iMisioneros<=2; iCanibales++) {
					if ( iMisioneros+iCanibales>0 ) {

    	  				MisionerosYCanibales nuevoEstado = 
    	  						new MisionerosYCanibales(misionerosIzq+iBarco*iMisioneros,
    	  								canibalesIzq+iBarco*iCanibales,
    	  								barcaIzq+iBarco,
    	  								misionerosDer-iBarco*iMisioneros,
    	  								canibalesDer-iBarco*iCanibales,
    	  								barcaDer-iBarco);
                                
    	  				if (nuevoEstado.isValid()) {
    	  					successorVec.addElement(new Successor(
    	  								   nuevoEstado, 
    	  								   "(" + iMisioneros + "," 
    	  								   + iCanibales + "," 
    	  								   + iBarco + ")", 1 )
    	  				   ); 
    	  				}
					}  
				}
			}
		}
		return successorVec.elements();
	}
   
   /**
    * genera la heuristica del estado actual
    * @return un float con la heuristica del estado actual
    */
   	public float h() {
	   float hVal = (float) misionerosIzq + (float) canibalesIzq - (float) barcaIzq;
	   return hVal;
    }

   	/**
   	 * genera un mensaje que representa el estado actual del problema
   	 * @return un String con el mensaje del estado actual
   	 */
   	public String toString() {
      return "(" + misionerosIzq + ":" + misionerosDer + "," + + canibalesIzq + ":" + 
                        canibalesDer + "," + + barcaIzq + ":" + barcaDer + ")";
	}
   	
   	/**
   	 * devuelve el numero de nodos expandidos
   	 * 
   	 */
   	public int dameNodosExpandidos(){
		return nodosExpandidos;
   	}
   	
   	/**
   	 * actualiza el numero de nodos expandidos
   	 * @param n es el numero de nodos expandidos al que se tiene que actualizar
   	 */
   	public void ponNodosExpandidos(int n){
		nodosExpandidos = n;
   	}

}
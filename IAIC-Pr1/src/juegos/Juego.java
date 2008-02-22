package juegos;

import java.util.ArrayList;
import java.util.Enumeration;

import aima.search.AStarSearch;
import aima.search.BreadthFirstSearch;
import aima.search.DepthBoundedSearch;
import aima.search.GreedySearch;
import aima.search.Heuristic;
import aima.search.IteratedDeepeningSearch;
import aima.search.SearchNode;
import aima.search.State;
import aima.search.Successor;
import aima.search.UniformCostSearch;

import programa.Controlador;

/**
 * 
 * Juego generico
 *
 */
public abstract class Juego implements State, Heuristic{
	
	protected Controlador cont;
	
	protected static int nodosExpandidos = 0;
	
	public abstract boolean isGoal();
	
	public abstract Enumeration<Successor> successors();
	
	public abstract float h();
	
	public abstract String toString();
	
	/**
	 * Devuelve el numero de nodos expandidos
	 * @return  devuelve el numero de nodos expandidos
	 */
	public int dameNodosExpandidos(){
		 return nodosExpandidos;
	}
	
	/**
	 * Actualiza el numero de nodos expandidos
	 * @param n actualiza el numero de nodos expandidos a n
	 */
	public void ponNodosExpandidos(int n){
		nodosExpandidos = n;
	}
	
	/**
	 * Consulta la referencia al controlador
	 * @return  Devuelve la referencia al controlador
	 */
	public Controlador getCont() {
		return cont;
	}

	/**
	 * Actualiza la referencia al controlador
	 * @param cont referencia nueva
	 */
	public void setCont(Controlador cont) {
		this.cont = cont;
	}
	
	/**
 	 * Resuelve el problema
 	 * @param e indica la estrategia a usar para resolverlo
	 * @return true en caso de tener solucion, false en caso contrario
 	 */
    public boolean resolver(int e){
		   
 		boolean resuelto = true;
 		switch(e){
	 		case 1:
	 			System.out.println("Primero en profundidad (profunidad máxima 7):\n");
	 			resuelto = listPath((new DepthBoundedSearch(this,7)).search());
	 			System.out.println("NodosExpandidos: "+nodosExpandidos+"\n");
	 			nodosExpandidos = 0;
				System.out.println("\n");break;
	 	
	 		case 2:
	 			System.out.println("Primero en anchura:\n");
	 			resuelto = listPath((new BreadthFirstSearch(this)).search());
	 			System.out.println("NodosExpandidos: "+nodosExpandidos+"\n");
	 			nodosExpandidos = 0;
				System.out.println("\n");break;
	 	
	 		case 4:
	 			System.out.println("Coste Uniforme:\n");
	 			resuelto = listPath((new UniformCostSearch(this)).search());
	 			System.out.println("NodosExpandidos: "+nodosExpandidos+"\n");
	 			nodosExpandidos = 0;
				System.out.println("\n");break;
	 	
	 		case 5:
	 			System.out.println("Profundidad iterativa:\n");
	 			resuelto = listPath((new IteratedDeepeningSearch(this)).search());
	 			System.out.println("NodosExpandidos: "+nodosExpandidos+"\n");
	 			nodosExpandidos = 0;
				System.out.println("\n");break;
	 	
	 		case 3:
	 			System.out.println("Busqueda A*:\n");
	 			resuelto = listPath((new AStarSearch(this)).search());
	 			System.out.println("NodosExpandidos: "+nodosExpandidos+"\n");
	 			nodosExpandidos = 0;
				System.out.println("\n");break;
	 	
	 		case 6:
	 			System.out.println("Escalada:\n");
	 			resuelto = listPath((new GreedySearch(this)).search());
	 			System.out.println("NodosExpandidos: "+nodosExpandidos+"\n");
	 			nodosExpandidos = 0;
				System.out.println("\n");break;
	 		}
 		
 		return resuelto;
 	}
	
	/**
     * Muestra el camino calculado por el algoritmo
     * @param node contiene el camino
     * @return 
     */
    private boolean listPath(SearchNode node) {
        ArrayList<String> camino = new ArrayList<String>();
 	    if (node == null) {
 		    System.out.println("No hay solución");
 		    return false;
 	    }
 	    String linea = "";
 	    while (node.getParent() != null) {
 		    linea =  "Estado: " + node.getState() +
            				  " Profundidad: " + node.getDepth() +
            				  " Coste: " + node.getPathCost() +
            				  " Operador: " + node.getAppliedOp();
 		    camino.add("\n"+linea);
 		    node = node.getParent();
 	    }
 	  
 	    linea = ( "\nESTADO INICIAL: " + node.getState());  
 	    camino.add(linea);
 	    for (int j=camino.size()-1; j>=0;j--){
 	    	System.out.print((String)camino.get(j));
 	    }
 	    //System.out.println("\n");
 	    return true;
    }

}

package juegos;

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

import java.util.ArrayList;
import java.util.Enumeration;

import programa.Controlador;

/**
 * Juego generico. Define los atributos y metodos comunes a todos los juegos
 * @author Miguel Angel Díaz
 * @author David Martín
 * @author Alberto Vaquero
 */
public abstract class Juego implements State, Heuristic{
	
	/**
	 * Referencia al controlador para poder escribir en su consola
	 */
	protected Controlador cont;
	
	/**
	 * Numero de nodos expandidos para resolver el problema con una determinada heuristica
	 */
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
		String mensaje="";   
 		boolean resuelto = true;
 		switch(e){
	 		case 1:
	 			mensaje+="Primero en profundidad (profunidad máxima 7):\n";
	 			resuelto = listPath((new DepthBoundedSearch(this,7)).search());
	 			break;
	 	
	 		case 2:
	 			mensaje+="Primero en anchura:\n";
	 			resuelto = listPath((new BreadthFirstSearch(this)).search());
	 			break;
	 	
	 		case 4:
	 			mensaje+="Coste Uniforme:\n";
	 			resuelto = listPath((new UniformCostSearch(this)).search());
	 			break;
	 	
	 		case 5:
	 			mensaje+="Profundidad iterativa:\n";
	 			resuelto = listPath((new IteratedDeepeningSearch(this)).search());
	 			break;
	 	
	 		case 3:
	 			mensaje+="Busqueda A*:\n";
	 			resuelto = listPath((new AStarSearch(this)).search());
	 			break;
	 	
	 		case 6:
	 			mensaje+="Escalada:\n";
	 			resuelto = listPath((new GreedySearch(this)).search());
	 			break;
	 		}
 		mensaje += "NodosExpandidos: "+nodosExpandidos+"\n";
		nodosExpandidos = 0;
		mensaje += "\n";
 		if (cont != null){
 			cont.mostrar(mensaje);
 		} else {
 			System.out.println(mensaje);
 		}
 		return resuelto;
 	}
	
	/**
     * Muestra el camino calculado por el algoritmo
     * @param node contiene el camino
     * @return <i>cierto</i> si el camino lleva a una solucion
     */
    private boolean listPath(SearchNode node) {
        ArrayList<String> camino = new ArrayList<String>();
        String mensaje = "";   
 		if (node == null) {
 		    mensaje += "No hay solución";
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
 	    
 	    linea = "JUEGO: ";
 	    if (this instanceof OchoPuzzle)
 	    	linea += "OchoPuzzle";
 	    else if (this instanceof MisionerosYCanibales)
	    	linea += "Misioneros y canibales";
 	    else if (this instanceof LoboCabraCol)
	    	linea += "Lobo, cabra y col";
 	    else if (this instanceof Mono)
	    	linea += "Mono";
 	    else if (this instanceof Palillos)
	    	linea += "Palillos";
 	    else if (this instanceof Jarras)
 	    	linea += "Jarras";
 	    
        linea += ( "\nESTADO INICIAL: " + node.getState());  
 	    camino.add(linea);
 	    for (int j=camino.size()-1; j>=0;j--){
 	    	mensaje += (String)camino.get(j);
 	    }
 	    mensaje += "\n";
 	    if (cont != null){
			cont.mostrar(mensaje);
		}
 	    else {
			System.out.println(mensaje);
		}
 	    return true;
    }

}

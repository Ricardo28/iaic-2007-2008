package programa;

import aima.search.AStarSearch;
import aima.search.BreadthFirstSearch;
import aima.search.DepthBoundedSearch;
import aima.search.GreedySearch;
import aima.search.IteratedDeepeningSearch;
import aima.search.SearchNode;
import aima.search.UniformCostSearch;

import gui.Visual;

import java.util.ArrayList;

import micromundo.EdificioCubico;

/**
 * Controlador de la aplicacion
 * @author Miguel Angel Diaz
 * @author David Martin
 * @author Alberto Vaquero
 */
public class Controlador {
	
	/**
	 * Referencia a la visual 
	 */
	private static Visual vista;
	
	/**
	 * EdificioCubico leido 
	 */
	private EdificioCubico edificio;
	
	/**
	 * Consulta el edificioCubico 
	 * @return edificio
	 */
	public EdificioCubico getEdificio() {
		return edificio;
	}

	/**
	 * Actualiza el edificio 
	 * @param edificio Nuevo edificio
	 */
	public void setEdificio(EdificioCubico edificio) {
		this.edificio = edificio;
	}

	/**
	 * Constructor por defecto
	 */
	public Controlador(){		
	}
	
	/**
	 * Asocia una vista al controlador
	 * @param vist Vista asociada al controlador
	 */
	public void asociarVista(Visual vist){
		vista = vist;
	}
	
	/**
	 * Inicia el juego con el numero de busqueda indicado por el parametro n
	 * @param n indica el tipo de busqueda a utilizar en el problema del laberinto (1-6)
	 */
	public void jugar(int n){
        
		edificio.ponNodosExpandidos(0);
		vista.mostrar("\n");
		if (n==1){
			vista.mostrar("Primero en profundidad (profundidad máxima: 7):\n");
			listPath((new DepthBoundedSearch(edificio,7)).search());
		}
		else if (n==2){
			vista.mostrar("Primero en anchura:\n");
			listPath((new BreadthFirstSearch(edificio)).search());
		}
		else if (n==3){
			vista.mostrar("Busqueda A*:\n");
			listPath((new AStarSearch(edificio)).search());
		}
		else if (n==4){
			vista.mostrar("Coste uniforme:\n");
			listPath((new UniformCostSearch(edificio)).search());
		}
		else if (n==5){
			vista.mostrar("Profundidad Iterativa:\n");
			listPath((new IteratedDeepeningSearch(edificio)).search());
		}
		else if (n==6){
			vista.mostrar("Escalada:\n");
			listPath((new GreedySearch(edificio)).search());
		}
		vista.mostrar("NodosExpandidos: "+edificio.dameNodosExpandidos()+"\n");
	}
	
	/**
	 * Muestra la salida del edificio calculada 
	 * @param node Resultado de la busqueda
	 */
	protected void listPath(SearchNode node) {
    	ArrayList<String> camino = new ArrayList<String>();
    	ArrayList<int[]> salida = new ArrayList<int[]>();
    	if (node == null) {
		    vista.mostrar("No hay solución\n");
		    return;
	    }
	    String linea = "";
	    while (node.getParent()!=null) {
	    	linea =  "Estado: " + node.getState() +
           				   " Profundidad: " + node.getDepth() +
           				   " Coste: " + node.getPathCost() +
           				   " Operador: " + node.getAppliedOp();
	    	int[] posicion = new int[3];
	    	posicion[0] = ((EdificioCubico)node.getState()).getActX();
	    	posicion[1] = ((EdificioCubico)node.getState()).getActY();
	    	posicion[2] = ((EdificioCubico)node.getState()).getActZ();
	    	salida.add(posicion);
	    	linea+="\n";
	    	camino.add(linea);
	    	node = node.getParent();
	    }
	  
	    linea = ( "ESTADO INICIAL: " + node.getState());  
	    camino.add(linea);
	    vista.mostrar("\n");
	    vista.mostrar("\n");
	    vista.mostrar("CAMINO A LA SOLUCION DEL EDIFICIO:\n");
	    for(int j=camino.size()-1; j>=0;j--){
	    	vista.mostrar((String)camino.get(j));
	    }
	    vista.rellena(salida);
    }
	 
	/**
	 * Asocia un vector que representa el tablero del laberinto al controlador
	 * @param edificio vector con las habitaciones del laberinto
	 */
	public void cargar(EdificioCubico edificio){
		this.edificio = edificio;
	}
	
	/**
	 * Notifica a la vista un mensaje a mostrar por pantalla
	 * @param s mensaje que quieres mostrar
	 */
	public void mostrar(String s){
		vista.mostrar(s);
	}
	
	/**
	 * Notifica a la Vista asociada que muestre un menu de solicitud de algoritmo de busqueda
	 * @return devuelve el numero de estrategia elegida
	 */
	public int solicitud(){
		return vista.solicitud();
	}
	
	
}

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

public class Controlador {
	
	private static Visual vista;
	
	private EdificioCubico edificio;
	
	public EdificioCubico getEdificio() {
		return edificio;
	}

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
	 * @param vista Vista asociada al controlador
	 */
	public void asociarVista(Visual vist){
		vista = vist;
	}
	
	/**
	 * Inicia el juego con el numero de busqueda indicado por el parametro n
	 * @param n indica el tipo de busqueda a utilizar en el problema del laberinto (1-6)
	 */
	public void jugar(int n){
        
		if (n==1){
			edificio.ponNodosExpandidos(0);
			vista.mostrar("Primero en profundidad (profundidad máxima: 7):\n");
			listPath((new DepthBoundedSearch(edificio,7)).search());
			vista.mostrar("NodosExpandidos: "+edificio.dameNodosExpandidos()+"\n");
		}
		else if (n==2){
			edificio.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Primero en anchura:\n");
			listPath((new BreadthFirstSearch(edificio)).search());
			vista.mostrar("NodosExpandidos: "+edificio.dameNodosExpandidos()+"\n");
		}
		else if (n==3){
			edificio.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Busqueda A*:\n");
			listPath((new AStarSearch(edificio)).search());
			vista.mostrar("NodosExpandidos: "+edificio.dameNodosExpandidos()+"\n");
		}
		else if (n==4){
			edificio.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Coste uniforme:\n");
			listPath((new UniformCostSearch(edificio)).search());
			vista.mostrar("NodosExpandidos: "+edificio.dameNodosExpandidos()+"\n");
		}
		else if (n==5){
			edificio.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Profundidad Iterativa:\n");
			listPath((new IteratedDeepeningSearch(edificio)).search());
			vista.mostrar("NodosExpandidos: "+edificio.dameNodosExpandidos()+"\n");
		}
		else if (n==6){
			edificio.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Escalada:\n");
			listPath((new GreedySearch(edificio)).search());
			vista.mostrar("NodosExpandidos: "+edificio.dameNodosExpandidos()+"\n");
			vista.mostrar("\n");
		}
	}
	
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
	 * @param hab vector con las habitaciones del laberinto
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
	 * @param bol si se trata del juego de las jarras o no 
	 * @return devuelve el numero de estrategia elegida
	 */
	public int solicitud(){
		return vista.solicitud();
	}
	
	
}

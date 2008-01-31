package programa;

import Busquedalaberinto.*;
import gui.*;
import aima.search.AStarSearch;
import aima.search.BreadthFirstSearch;
import aima.search.DepthBoundedSearch;
import aima.search.GreedySearch;
import aima.search.IteratedDeepeningSearch;
import aima.search.SearchNode;
import aima.search.UniformCostSearch;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 * @author Usuario
 *
 */
public class Controlador {
	
	private Vista vista;
	
	private Vector<Habitacion> habitaciones;
	
	private Laberinto inicial;
	
	/**
	 * constructor por defecto
	 *
	 */
	public Controlador(){		
	}
	
	/**
	 * asocia una vista al controlador
	 * @param vista Vista asociada al controlador
	 */
	public void asociarVista(Vista vista){
		this.vista = vista;
	}
	
	/**
	 * inicia el juego del laberinto con el numero de busqueda indicado por el parametro n
	 * n va de 1 a 6 segun las busquedas implementadas
	 * @param n indica el tipo de busqueda a utilizar en el problema del laberinto
	 */
	public void jugar(int n){
        
		Vector<Integer> sal = new Vector<Integer>();
        int ini = 0;
		for (int i=0; i<habitaciones.size(); i++){
			Habitacion hab = habitaciones.elementAt(i);
			if (hab.esSalida())
				sal.add(i+1);
			if (hab.esEntrada())
				ini = i+1;
		}
		
		inicial = new Laberinto(ini,sal,ini,habitaciones,this);
		vista.mostrar("\n");
		if (n==1){
			inicial.ponNodosExpandidos(0);
			vista.mostrar("Primero en profundidad (profundidad máxima: 7):\n");
			listPath( ( new DepthBoundedSearch(inicial,7) ).search() );
			vista.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
		}
		if (n==2){
			inicial.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Primero en anchura:\n");
			listPath( ( new BreadthFirstSearch(inicial) ).search() );
			vista.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
		}
		if (n==4){
			inicial.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Coste uniforme:\n");
			listPath( ( new UniformCostSearch(inicial) ).search() );
			vista.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
		}
		if (n==5){
			inicial.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Profundidad Iterativa:\n");
			listPath( ( 
					new IteratedDeepeningSearch(inicial) ).search() );
			vista.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
		}
		if (n==3){
			inicial.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Busqueda A*:\n");
			listPath( ( 
					new AStarSearch(inicial) ).search() );
			vista.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
		}
		if (n==6){
			inicial.ponNodosExpandidos(0);
			vista.mostrar("\n");
			vista.mostrar("Escalada:\n");
			listPath( ( 
					new GreedySearch(inicial) ).search() );
			vista.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
			vista.mostrar("\n");
		}
	}
	
	protected void listPath(SearchNode node) {
    	ArrayList<String> camino = new ArrayList<String>();
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
	    	linea+="\n";
	    	camino.add(linea);
	    	node = node.getParent();
	    }
	  
	    linea = ( "ESTADO INICIAL: " + node.getState());  
	    camino.add(linea);
	    vista.mostrar("\n");
	    vista.mostrar("\n");
	    vista.mostrar("CAMINO A LA SOLUCION DEL LABERINTO:\n");
	    for(int j=camino.size()-1; j>=0;j--){
	    	vista.mostrar((String)camino.get(j));
	    }
    }
	 
	/**
	 * asocia un vector que representa el tablero del laberinto al controlador
	 * @param hab vector con las habitaciones del laberinto
	 */
	public void cargar(Vector<Habitacion> hab){
		habitaciones = hab;
	}
	
	/**
	 * notifica a la vista un mensaje a mostrar por pantalla
	 * @param s mensaqje que quieres mostrar
	 */
	public void mostrar(String s){
		vista.mostrar(s);
	}
	
	/**
	 * notifica a la Vista asociada que muestre un menu de solicitud de algoritmo de busqueda
	 * @param bol si se trata del juego de las jarras o no 
	 * @return devuielve el numero de estrategia elegida
	 */
	public int solicitud(boolean bol){
		return vista.solicitud(bol);
	}
	
	
}

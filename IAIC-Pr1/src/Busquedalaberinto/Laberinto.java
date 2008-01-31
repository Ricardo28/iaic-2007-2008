package Busquedalaberinto;

import aima.search.*;
import java.util.*;
import javax.swing.JOptionPane;
import juegos.*;
import programa.Controlador;

/**
 * 
 * @author Usuario
 *
 */
public class Laberinto implements State,Heuristic {
	
    private int habInicio;
    
    private Vector<Integer> habSalida;
    
    private int habActual;
    
    private Vector<Habitacion> habitaciones;
    
    private Controlador cont;
    
    private static int nodosExpandidos = 0;    
    
    /** Creates a new instance of Laberinto */
    public Laberinto(int start,Vector<Integer> goal,int act, Vector<Habitacion> h,Controlador cont){
    	habInicio = start;
    	habSalida = goal;
    	habActual = act;
    	habitaciones = new Vector<Habitacion>(h.size());
    	for (int i=0;i<h.size();i++){
    	    Habitacion aux = (Habitacion)h.elementAt(i);
    	   	Habitacion hab = new Habitacion(aux.numero,aux.adyacentes,aux.esEntrada,aux.esSalida,aux.esExterior,aux.juego,aux.resuelto);
            habitaciones.add(hab);
    	}
        this.cont = cont;
    }
   
    /**
     * indica si un estado es valido o no
     * @return true si es valido false en caso contrario
     */
    protected boolean isValid(){
        return true;
    }
   
    /**
     * indica si un estado es solucion
     * @return true si es solucion false en caso contrario
     */
    public boolean isGoal(){
    	boolean sal = false;
    	int k=0;
    	while(k<habSalida.size()){
    		sal = this.habActual == (Integer)habSalida.elementAt(k);
    		k++;
    	}
    	return sal;
    }
   
    /**
     * genera los sucesores del estado actual
     */
    public Enumeration<Successor> successors(){

	 	Vector<Successor> successorVec = new Vector<Successor>();
	 	String operador = "";
	 	nodosExpandidos++;
	 	Habitacion hab = (Habitacion)habitaciones.elementAt(habActual-1);
	 	cont.mostrar("Está en la habitacion"+habActual+"\n");
	 	boolean resuelto = false;
	 	if (!hab.resuelto){
	 		switch(hab.juego){
	 			case 1: {cont.mostrar("\nJUEGO DEL OCHO PUZZLE\n");break;}
	 			case 2: {cont.mostrar("\nMISIONEROS Y CANIBALES\n");break;}
	 			case 3: {cont.mostrar("\nLA CABRA, EL LOBO Y LA COL\n");break;}
	 			case 4: {cont.mostrar("\nEL MONO, LA CAJA Y EL PLATANO\n");break;}
	 			case 5: {cont.mostrar("\nEL JUEGO DE LOS PALILLOS\n");break;}
	 			case 6: {cont.mostrar("\nEL PROBLEMA DE LAS JARRAS\n");break;}
	 		}
	 		while(!resuelto){
	 			boolean jarrillas = true;
	 			if (hab.juego == 6){
	 				jarrillas = false;
	 			}
	 			int estrategia=cont.solicitud(jarrillas);
		       	try {
		       		resuelto = resolverProblema(hab.juego,estrategia);
		       	}
		       	catch(Exception e){
		       		JOptionPane.showMessageDialog(null,"No se pudo solucionar el problema"
		                    ,"ERROR",JOptionPane.ERROR_MESSAGE);
		       		resuelto = false;
		       	}
		       	catch(Error e){
		       		JOptionPane.showMessageDialog(null,"No se pudo solucionar el problema"
		                    ,"ERROR",JOptionPane.ERROR_MESSAGE);
		       		resuelto = false;
		       	}
		       	Habitacion haux = new Habitacion(hab.numero,hab.adyacentes,hab.esEntrada,hab.esSalida,hab.esExterior,hab.juego,resuelto);
		       	habitaciones.set(habActual-1,haux);
	 		}
       	}
       	if(resuelto){
       		String msg = "";
       		cont.mostrar("Se han abierto las puertas a las habitaciones:  ");
       		for (int i=0;i<hab.dameAdyacentes().length;i++){
       			int [] lista = hab.dameAdyacentes();
       			if (i<hab.dameAdyacentes().length-1)
       				msg+=" Habitacion "+ lista[i] + ", ";
       			else
       				msg+=" Habitacion "+ lista[i] + "\n";
       		}
       		cont.mostrar(msg);
       	}

       	int[] lista = hab.dameAdyacentes();
       	for(int k=0;k<hab.dameAdyacentes().length;k++){
       		int e = lista[k];
       		Laberinto nuevoEstado = new Laberinto(habInicio,habSalida,e,habitaciones,cont);
       		operador = "Entra a la habitacion " + e;
       		if(nuevoEstado.isValid()){
       			successorVec.addElement(new Successor((Laberinto)nuevoEstado, operador, 1 ));
       		}
       	}
       	return successorVec.elements();
    }
   
   /**
    * genera la heuristica del estado actual
    */
   public float h() {
	    //Heuristica: Distancia minima entre la habitacion actual y una de salida 
	    //mas las distancias entre las adyacentes y una de salida
	    Habitacion hab = (Habitacion)habitaciones.elementAt(habActual-1);
	    int[] lista = hab.dameAdyacentes();
	    int heuristica = 0;
	    int min = habitaciones.size();
	    for(int i = 0; i<habSalida.size();i++){
            int termino = 0;
	    	int sal = (Integer)habSalida.elementAt(i);
	    	if(habActual-sal >=0)
	    		heuristica = habActual-sal;
	    	else
	    		heuristica = sal-habActual;
	    	if(heuristica >0){
	    		for(int j=0; j<lista.length;j++){
	    			if(sal-lista[j] >=0)
	    	    		termino+= sal-lista[j];
	    	    	else
	    	    		termino+= lista[j]-sal;
	    		}
	    	}
		heuristica = heuristica+termino;
		if(heuristica<min)
			min = heuristica;
	    }
	    return (min);
	 }
   
   /**
    * genera un mensaje que indica el estado actual 
    */
   public String toString(){
	   String msg = "\n Habitacion actual: "+ habActual + "\n";
	   Habitacion hab = (Habitacion)habitaciones.elementAt(habActual-1);
	   if(hab.dameAdyacentes().length>0){
                   if(!hab.resuelto)
                     msg+="Si se resuelve el problema se abriran:\n";
                   else
                     msg+="El problema ya se resolvio. Tiene acceso a:\n";
		   for(int i=0;i<hab.dameAdyacentes().length;i++){
			   int [] lista = hab.dameAdyacentes();
			   msg+="   Habitacion "+ lista[i] + "\n";
		   }
	   }else{
		   msg+="No hay ninguna habitacion accesible desde aqui.\n";
	   }
	   return msg;
   }
   
   /**
    * devuelve el numero de nodos exapndidos
    * @return devuelve el numero de nodos expandidos
    */
   public int dameNodosExpandidos(){
		 return nodosExpandidos;
   }
   
   /**
    * actualiza el numero de nodos exapandidos
    * @param n actualiza el numero de nodos expandidos con el parametro n
    */
   public void ponNodosExpandidos(int n){
		 nodosExpandidos = n;
   }
   
   /**
    * indica que tipo de juego hay que resolver 
    * @param numJuego indica el juego a resolver
    * @param estrategia indica la estrategia ocn la que hay que resolverlo
    * @return true si se ha resuleto false en caso contrario
    */
   public boolean resolverProblema(int numJuego,int estrategia){
	   switch(numJuego){
	   case 1: {return resolverOchoPuzzle(estrategia);}
	   case 2: {return resolverMisionerosyCanibales(estrategia);}
	   case 3: {return resolverCabraLoboCol(estrategia);}
	   case 4: {return resolverMono(estrategia);}
	   case 5: {return resolverPalillos(estrategia);}
	   case 6: {return resolverJarras(estrategia);}
	   default: return false;
	   }
   }
   
   /**
    * resuleve el problema de las jarras
    * @param e estrategia utilizada para solucionar el problema de las jarras
    * @return true si se resuleve false en caso contrario
    */
   public boolean resolverJarras(int e){
	   Jarras inicial = new Jarras(0,0);

	   boolean resuelto = true;
		switch(e){
		 case 1:
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("Primero en profundidad(profunidad máxima: 7):\n");
			 resuelto=listPath( ( new DepthBoundedSearch(inicial,7) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 2:
			 cont.mostrar("Primero en anchura:\n");
			 resuelto=listPath( ( new BreadthFirstSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 4:
			 cont.mostrar("Coste Uniforme:\n");
			 resuelto = listPath( ( new UniformCostSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 5:
			 cont.mostrar("Profundidad iterativa:\n");
			 resuelto=listPath( (
					 new IteratedDeepeningSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 3:
			 cont.mostrar("Busqueda A*:\n");
			 resuelto=listPath( (
					 new AStarSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 6:
			 cont.mostrar("Escalada:\n");
			 resuelto = listPath( (
					 new GreedySearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
		 }
      return resuelto;
   }
   
   /**
    * resuleve el problema de ochoPuzzle
    * @param e indica la estrategia usada para resilverlo
    * @return true en casa de tener solucion false en caso contrario
    */
   public boolean resolverOchoPuzzle(int e){
	   
	   	int [][] tabla = new int [3][3];
		 tabla[0][0] = 1;
		 tabla[0][1] = 3;
		 tabla[0][2] = 4;
		 tabla[1][0] = 8;
		 tabla[1][1] = 0;
		 tabla[1][2] = 2;
		 tabla[2][0] = 7;
		 tabla[2][1] = 6;
		 tabla[2][2] = 5;
		 
		 OchoPuzzle inicial = new OchoPuzzle(tabla,1,1);
		 boolean resuelto = true;
			switch(e){
			 case 1:
				 inicial.ponNodosExpandidos(0);
				 cont.mostrar("Primero en profundidad(profunidad máxima: 7):\n");
				 resuelto=listPath( ( new DepthBoundedSearch(inicial,7) ).search());
				 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

				 inicial.ponNodosExpandidos(0);
				 cont.mostrar("\n");break;

			 case 2:
				 cont.mostrar("Primero en anchura:\n");
				 resuelto=listPath( ( new BreadthFirstSearch(inicial) ).search());
				 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

				 inicial.ponNodosExpandidos(0);
				 cont.mostrar("\n");break;

			 case 4:
				 cont.mostrar("Coste Uniforme:\n");
				 resuelto = listPath( ( new UniformCostSearch(inicial) ).search());
				 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

				 inicial.ponNodosExpandidos(0);
				 cont.mostrar("\n");break;

			 case 5:
				 cont.mostrar("Profundidad iterativa:\n");
				 resuelto=listPath( (
						 new IteratedDeepeningSearch(inicial) ).search());
				 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

				 inicial.ponNodosExpandidos(0);
				 cont.mostrar("\n");break;

			 case 3:
				 cont.mostrar("Busqueda A*:\n");
				 resuelto=listPath( (
						 new AStarSearch(inicial) ).search());
				 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

				 inicial.ponNodosExpandidos(0);
				 cont.mostrar("\n");break;

			 case 6:
				 cont.mostrar("Escalada:\n");
				 resuelto = listPath( (
						 new GreedySearch(inicial) ).search());
				 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
				 inicial.ponNodosExpandidos(0);
				 cont.mostrar("\n");break;
			 }
	      return resuelto;
   }
   
   /**
    * resuleve el problema de olos misioneros y los canibales
    * @param e indica la estrategia usada para resilverlo
    * @return true en casa de tener solucion false en caso contrario
    */
   public boolean resolverMisionerosyCanibales(int e){
	   
	   MisionerosYCanibales inicial = new MisionerosYCanibales(3,3,1);
	   boolean resuelto = true;
		switch(e){
		 case 1:
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("Primero en profundidad(profunidad máxima: 7):\n");
			 resuelto=listPath( ( new DepthBoundedSearch(inicial,7) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 2:
			 cont.mostrar("Primero en anchura:\n");
			 resuelto=listPath( ( new BreadthFirstSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 4:
			 cont.mostrar("Coste Uniforme:\n");
			 resuelto = listPath( ( new UniformCostSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 5:
			 cont.mostrar("Profundidad iterativa:\n");
			 resuelto=listPath( (
					 new IteratedDeepeningSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 3:
			 cont.mostrar("Busqueda A*:\n");
			 resuelto=listPath( (
					 new AStarSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 6:
			 cont.mostrar("Escalada:\n");
			 resuelto = listPath( (
					 new GreedySearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
		 }
     return resuelto;
   }
   
   /**
    * resuleve el problema de de la cabra el lobo y la col
    * @param e indica la estrategia usada para resilverlo
    * @return true en casa de tener solucion false en caso contrario
    */  
   public boolean resolverCabraLoboCol(int e){
	   
		CabraLoboCol inicial = new CabraLoboCol(1,1,1,1);

		boolean resuelto = true;
		switch(e){
		 case 1:
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("Primero en profundidad(profunidad máxima: 7):\n");
			 resuelto=listPath( ( new DepthBoundedSearch(inicial,7) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 2:
			 cont.mostrar("Primero en anchura:\n");
			 resuelto=listPath( ( new BreadthFirstSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 4:
			 cont.mostrar("Coste Uniforme:\n");
			 resuelto = listPath( ( new UniformCostSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 5:
			 cont.mostrar("Profundidad iterativa:\n");
			 resuelto=listPath( (
					 new IteratedDeepeningSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 3:
			 cont.mostrar("Busqueda A*:\n");
			 resuelto=listPath( (
					 new AStarSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");

			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;

		 case 6:
			 cont.mostrar("Escalada:\n");
			 resuelto = listPath( (
					 new GreedySearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
		 }
      return resuelto;
   }

	/**
	 * resuleve el problema del mono
	 * @param e indica la estrategia usada para resilverlo
	 * @return true en casa de tener solucion false en caso contrario
	 */
	public boolean resolverMono(int e){
		   
		Mono inicial = new Mono(0,false,1,false);
		boolean resuelto = true;
		switch(e){
		 case 1:
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("Primero en profundidad(profunidad máxima: 7):\n");
			 resuelto=listPath( ( new DepthBoundedSearch(inicial,7) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 2:
			 cont.mostrar("Primero en anchura:\n");
			 resuelto=listPath( ( new BreadthFirstSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 4:
			 cont.mostrar("Coste Uniforme:\n");
			 resuelto = listPath( ( new UniformCostSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 5:
			 cont.mostrar("Profundidad iterativa:\n");
			 resuelto=listPath( (
					 new IteratedDeepeningSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 3:
			 cont.mostrar("Busqueda A*:\n");
			 resuelto=listPath( (
					 new AStarSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 6:
			 cont.mostrar("Escalada:\n");
			 resuelto = listPath( (
					 new GreedySearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
		 }
	  return resuelto;
	}
	
	/**
	 * resuleve el problema de los palillos
	 * @param e indica la estrategia usada para resilverlo
	 * @return true en casa de tener solucion false en caso contrario
	 */
	public boolean resolverPalillos(int e){
		   
		Palillos inicial = new Palillos(6,5);
		boolean resuelto = true;
		switch(e){
		 case 1:
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("Primero en profundidad(profunidad máxima: 7):\n");
			 resuelto=listPath( ( new DepthBoundedSearch(inicial,7) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 2:
			 cont.mostrar("Primero en anchura:\n");
			 resuelto=listPath( ( new BreadthFirstSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 4:
			 cont.mostrar("Coste Uniforme:\n");
			 resuelto = listPath( ( new UniformCostSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 5:
			 cont.mostrar("Profundidad iterativa:\n");
			 resuelto=listPath( (
					 new IteratedDeepeningSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 3:
			 cont.mostrar("Busqueda A*:\n");
			 resuelto=listPath( (
					 new AStarSearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
	
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
	
		 case 6:
			 cont.mostrar("Escalada:\n");
			 resuelto = listPath( (
					 new GreedySearch(inicial) ).search());
			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
			 inicial.ponNodosExpandidos(0);
			 cont.mostrar("\n");break;
		 }
	  return resuelto;
	}

   public boolean listPath(SearchNode node) {
       ArrayList<String> camino = new ArrayList<String>();
	   if (node == null) {
		   cont.mostrar("No hay solución");
		   return false;
	   }
	   String linea = "";
	   while (node.getParent()!=null) {
		   linea =  "Estado: " + node.getState() +
           				   " Profundidad: " + node.getDepth() +
           				   " Coste: " + node.getPathCost() +
           				   " Operador: " + node.getAppliedOp();
		   camino.add("\n"+linea);
		   node = node.getParent();
	   }
	  
	   linea = ( "\nESTADO INICIAL: " + node.getState());  
	   camino.add(linea);
	   for(int j=camino.size()-1; j>=0;j--){
		   cont.mostrar((String)camino.get(j));
	   }
	   cont.mostrar("\n");
	   cont.mostrar("\n");
	   return true;
   }
   
}

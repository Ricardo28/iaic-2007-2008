package micromundo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import juegos.*;

import programa.Controlador;
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

/**
 * 
 * @author Miguel Angel Diaz
 *
 */
public class EdificioCubico  implements State,Heuristic {
	
	private int dimension;
	
	private int iniX, iniY, iniZ;
	
	private int actX, actY, actZ;
	
	private HabitacionCubica[][][] edificio;
	
	private Controlador cont;
    
    private static int nodosExpandidos = 0; 
    
    public EdificioCubico(Controlador cont){
    	this.cont = cont;
    }
    
    public EdificioCubico(int n, int iniX, int iniY, int iniZ, int actX, int actY, int actZ, 
			HabitacionCubica[][][] edificio, Controlador cont){
    	dimension = n;
		this.iniX = iniX; this.iniY = iniY; this.iniZ = iniZ;
		this.actX = actX; this.actY = actY; this.actZ = actZ;
		this.edificio = edificio;
		this.cont = cont;
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
	 * Consulta si una habitacion es salida, si es una esquina del edificio cubico
	 */
	public boolean isGoal() {
		return edificio[actX][actY][actZ].esSalida();
	}
	
	public Enumeration<Successor> successors() {
		Vector<Successor> successorVec = new Vector<Successor>();
		nodosExpandidos++;
		int estrategia = cont.solicitud();
		if (edificio[actX][actY][actZ].puedeIncX() &&
                resolverProblema(edificio[actX][actY][actZ].getJuegos()[0], estrategia)){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX+1, actY, actZ, edificio, cont);
			String operador = "Incrementa X -> ("+(actX+1)+","+actY+","+actZ+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeDecX() &&
                resolverProblema(edificio[actX][actY][actZ].getJuegos()[1], estrategia)){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX-1, actY, actZ, edificio, cont);
			String operador = "Decrementa X -> ("+(actX-1)+","+actY+","+actZ+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeIncY() &&
                resolverProblema(edificio[actX][actY][actZ].getJuegos()[2], estrategia)){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX, actY+1, actZ, edificio, cont);
			String operador = "Incrementa Y -> ("+actX+","+(actY+1)+","+actZ+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeDecY() &&
                resolverProblema(edificio[actX][actY][actZ].getJuegos()[3], estrategia)){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX, actY-1, actZ, edificio, cont);
			String operador = "Decrementa Y -> ("+actX+","+(actY-1)+","+actZ+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeIncZ() &&
                  resolverProblema(edificio[actX][actY][actZ].getJuegos()[4], estrategia)){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX, actY, actZ+1, edificio, cont);
			String operador = "Incrementa Z -> ("+actX+","+actY+","+(actZ+1)+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeDecZ() &&
                  resolverProblema(edificio[actX][actY][actZ].getJuegos()[5], estrategia)){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX, actY, actZ-1, edificio, cont);
			String operador = "Decrementa Z -> ("+actX+","+actY+","+(actZ-1)+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		return successorVec.elements();
	}

	/**
     * genera la heuristica del estado actual
     */
    public float h() {
 	    //Heuristica: Distancia minima entre la habitacion actual y una de salida 
 	    //            mas las distancias entre las adyacentes y una de salida
 	    float heuristica = distanciaMinSalida(edificio[actX][actY][actZ]);
 	    if (edificio[actX][actY][actZ].puedeIncX())
			heuristica += distanciaMinSalida(edificio[actX+1][actY][actZ]);
 	    if (edificio[actX][actY][actZ].puedeDecX())
 	    	heuristica += distanciaMinSalida(edificio[actX-1][actY][actZ]);
 	    if (edificio[actX][actY][actZ].puedeIncY())
 	    	heuristica += distanciaMinSalida(edificio[actX][actY+1][actZ]);
 	    if (edificio[actX][actY][actZ].puedeDecY())
 	    	heuristica += distanciaMinSalida(edificio[actX][actY-1][actZ]);
 	    if (edificio[actX][actY][actZ].puedeIncZ())
 	    	heuristica += distanciaMinSalida(edificio[actX][actY][actZ+1]);
 	    if (edificio[actX][actY][actZ].puedeDecZ())
 	    	heuristica += distanciaMinSalida(edificio[actX][actY][actZ-1]);
 	    return heuristica;
 	}
    
    /**
     * Calcula la distancia minima entre la habitacion ini y la salida mas cercana
     * @param ini
     * @return distancia minima a una salida
     */
    private int distanciaMinSalida(HabitacionCubica ini){
    	int x = Math.min(ini.getX(), dimension-1-ini.getX());
    	int y = Math.min(ini.getY(), dimension-1-ini.getY());
    	int z = Math.min(ini.getZ(), dimension-1-ini.getZ());
        return x + y + z;
    }
	
	/**
     * genera un mensaje que indica el estado actual 
     */
	public String toString(){
	    String msg = "\n Habitacion actual: "+actX+","+actY+","+actZ+"\n";
	    HabitacionCubica hAct = edificio[actX][actY][actZ];
	    if (hAct.puedeIncX()){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[0] + 
	    		   " subes a la habitacion "+(actX+1)+","+actY+","+actZ+"\n";
	    }
	    if (hAct.puedeDecX()){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[1] + 
	    		   " bajas a la habitacion "+(actX-1)+","+actY+","+actZ+"\n";
	    }
	    if (hAct.puedeIncY()){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[2] +
	    	       " giras a la habitacion "+actX+","+(actY+1)+","+actZ+"\n";
	    }
	    if (hAct.puedeDecY()){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[3] +
	    		   " giras a la habitacion "+actX+","+(actY-1)+","+actZ+"\n";
	    }
	    if (hAct.puedeIncZ()){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[4] +
	    		   " avanzas a la habitacion "+actX+","+actY+","+(actZ+1)+"\n";
	    }
	    if (hAct.puedeDecZ()){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[5] +
	 	   		   " retrocedes a la habitacion "+actX+","+actY+","+(actZ-1)+"\n";;
	    }
	    return msg;
	}

	public int getIniX() {
		return iniX;
	}

	public void setIniX(int iniX) {
		this.iniX = iniX;
	}

	public int getIniY() {
		return iniY;
	}

	public void setIniY(int iniY) {
		this.iniY = iniY;
	}

	public int getIniZ() {
		return iniZ;
	}

	public void setIniZ(int iniZ) {
		this.iniZ = iniZ;
	}

	public int getActX() {
		return actX;
	}

	public void setActX(int actX) {
		this.actX = actX;
	}

	public int getActY() {
		return actY;
	}

	public void setActY(int actY) {
		this.actY = actY;
	}

	public int getActZ() {
		return actZ;
	}

	public void setActZ(int actZ) {
		this.actZ = actZ;
	}

	public HabitacionCubica[][][] getEdificio() {
		return edificio;
	}

	public void setEdificio(HabitacionCubica[][][] edificio) {
		int n = edificio.length;
		HabitacionCubica[][][] edi = new HabitacionCubica[n][n][n];
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++)
				for (int k=0; k<n; k++)
					edi[i][j][k] = edificio[i][j][k].clone();
		this.edificio = edi;
	}

	public Controlador getCont() {
		return cont;
	}

	public void setCont(Controlador cont) {
		this.cont = cont;
	} 
	
	public int getDimension(){
		return dimension;
	}
	
	public void setDimension(int dimension){
		this.dimension = dimension;
	}
	
	public void vuelveAlInicio(){
		actX = iniX;
		actY = iniY;
		actZ = iniZ;
	}
	
	public void imprime(){
		System.out.println("Dimension: " + dimension);
		System.out.println("Inicio:\t" + iniX + "," + iniY + "," + iniZ);
		System.out.println("Actual:\t" + actX + "," + actY + "," + actZ);
		System.out.println("Hab\tJuegos (arriba,\tabajo,\tizq,\tder,\tdel,\tdet)");
		for (int i=0; i<dimension; i++)
			for (int j=0; j<dimension; j++)
				for (int k=0; k<dimension; k++){
					System.out.println(i+","+j+","+k+"\t:\t"+
							edificio[i][j][k].getJuegos()[0]+",\t"+
							edificio[i][j][k].getJuegos()[1]+",\t"+
							edificio[i][j][k].getJuegos()[2]+",\t"+
							edificio[i][j][k].getJuegos()[3]+",\t"+
							edificio[i][j][k].getJuegos()[4]+",\t"+
							edificio[i][j][k].getJuegos()[5]);
				}
	}
	
	/**
     * indica si un estado es valido o no
     * @return true si es valido false en caso contrario
     */
    protected boolean isValid(){
        return true;
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
 	   case 3: {return resolverLoboCabraCol(estrategia);}
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
    private boolean resolverJarras(int e){
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
    private boolean resolverOchoPuzzle(int e){
 	   
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
    private boolean resolverMisionerosyCanibales(int e){
 	   
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
    private boolean resolverLoboCabraCol(int e){
 	   
 		LoboCabraCol inicial = new LoboCabraCol(1,1,1,1);

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
    private boolean resolverMono(int e){
 		   
 		Mono inicial = new Mono(0,false,2,false);
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
    private boolean resolverPalillos(int e){
 		   
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
 			 resuelto = listPath((new UniformCostSearch(inicial)).search());
 			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
 	
 			 inicial.ponNodosExpandidos(0);
 			 cont.mostrar("\n");break;
 	
 		 case 5:
 			 cont.mostrar("Profundidad iterativa:\n");
 			 resuelto=listPath((new IteratedDeepeningSearch(inicial)).search());
 			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
 	
 			 inicial.ponNodosExpandidos(0);
 			 cont.mostrar("\n");break;
 	
 		 case 3:
 			 cont.mostrar("Busqueda A*:\n");
 			 resuelto=listPath((new AStarSearch(inicial)).search());
 			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
 	
 			 inicial.ponNodosExpandidos(0);
 			 cont.mostrar("\n");break;
 	
 		 case 6:
 			 cont.mostrar("Escalada:\n");
 			 resuelto = listPath((new GreedySearch(inicial)).search());
 			 cont.mostrar("NodosExpandidos: "+inicial.dameNodosExpandidos()+"\n");
 			 inicial.ponNodosExpandidos(0);
 			 cont.mostrar("\n");break;
 		 }
 	  return resuelto;
 	}
 	
    private boolean listPath(SearchNode node) {
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
 	    for (int j=camino.size()-1; j>=0;j--){
 		    cont.mostrar((String)camino.get(j));
 	    }
 	    cont.mostrar("\n");
 	    cont.mostrar("\n");
 	    return true;
    }
   
}

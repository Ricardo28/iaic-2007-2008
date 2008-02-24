package micromundo;

import aima.search.Heuristic;
import aima.search.State;
import aima.search.Successor;

import java.util.Enumeration;
import java.util.Vector;

import juegos.OchoPuzzle;
import juegos.MisionerosYCanibales;
import juegos.LoboCabraCol;
import juegos.Mono;
import juegos.Palillos;
import juegos.Jarras;
import programa.Controlador;

/**
 * Representa un edificio cubico de dimension NxNxN
 * @author Miguel Angel Díaz
 * @author David Martín
 * @author Alberto Vaquero
 */
public class EdificioCubico  implements State,Heuristic {
	
	/**
	 * Dimension del edificio
	 */
	private int dimension;
	
	/**
	 * Coordenadas de la posicion inicial 
	 */
	private int iniX, iniY, iniZ;
	
	/**
	 * Coordenadas de la posicion actual
	 */
	private int actX, actY, actZ;
	
	/**
	 * Edificio
	 */
	private HabitacionCubica[][][] edificio;
	
	/**
	 * Referencia al controlador 
	 */
	private Controlador cont;
    
	/**
	 * Numero de nodos expandidos para resolver el problema con una determinada heuristica
	 */
	private static int nodosExpandidos = 0; 
    
    /**
     * Crea una nueva instancia de EdificioCubico 
     * @param cont referencia al controlador
     */
	public EdificioCubico(Controlador cont){
    	this.cont = cont;
    }
    
	/**
	 * Crea una nueva instancia de EdificioCubico a partir de todos sus atributos
	 * @param n dimension
	 * @param iniX coordenada inicial x
	 * @param iniY coordenada inicial y
	 * @param iniZ coordenada inicial z
	 * @param actX coordenada actual x
	 * @param actY coordenada actual y
	 * @param actZ coordenada actual z
	 * @param edificio edificio inicializado
	 * @param cont Referencia al controlador
	 */
    public EdificioCubico(int n, int iniX, int iniY, int iniZ, int actX, int actY, int actZ, 
			HabitacionCubica[][][] edificio, Controlador cont){
    	dimension = n;
		this.iniX = iniX; this.iniY = iniY; this.iniZ = iniZ;
		this.actX = actX; this.actY = actY; this.actZ = actZ;
		this.edificio = edificio;
		this.cont = cont;
	}
    
    /**
     * Crea una nueva instancia de EdificioCubico a partir de un archivo 
     * @param ruta del archivo
     * @param cont referencia al controlador
     */
    public EdificioCubico(String ruta, Controlador cont){
    	this.cont = cont;
    	CargarEdificio.cargarEdificio(this, ruta);
    }
	
	/**
	 * Devuelve el numero de nodos exapndidos
	 * @return devuelve el numero de nodos expandidos
	 */
	public int dameNodosExpandidos(){
		return nodosExpandidos;
	}
   
	/**
	 * Actualiza el numero de nodos exapandidos
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
	
	/**
	 * Genera los sucesores del estado actual
	 * @return los sucesores del estado actual
	 */
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
     * Genera la heuristica del estado actual<br>
     * Heuristica: Distancia minima entre la habitacion actual y una de salida 
 	 *            mas las distancias entre las adyacentes y una de salida
 	 */
    public float h() {
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
     * @param ini posicion inicial desde la que se calcula la distancia minima
     * @return distancia minima a una salida
     */
    private int distanciaMinSalida(HabitacionCubica ini){
    	int x = Math.min(ini.getX(), dimension-1-ini.getX());
    	int y = Math.min(ini.getY(), dimension-1-ini.getY());
    	int z = Math.min(ini.getZ(), dimension-1-ini.getZ());
        return x + y + z;
    }
	
	/**
     * Genera un mensaje que indica el estado actual 
     */
	public String toString(){
	    String msg = "\n Habitacion actual: "+actX+","+actY+","+actZ+"\n";
	    HabitacionCubica hAct = edificio[actX][actY][actZ];
	    if (hAct.puedeIncX() && hAct.getJuegos()[0]!=0){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[0] + 
	    		   " subes a la habitacion "+(actX+1)+","+actY+","+actZ+"\n";
	    }
	    if (hAct.puedeDecX() && hAct.getJuegos()[1]!=0){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[1] + 
	    		   " bajas a la habitacion "+(actX-1)+","+actY+","+actZ+"\n";
	    }
	    if (hAct.puedeIncY() && hAct.getJuegos()[2]!=0){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[2] +
	    	       " giras a la habitacion "+actX+","+(actY+1)+","+actZ+"\n";
	    }
	    if (hAct.puedeDecY() && hAct.getJuegos()[3]!=0){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[3] +
	    		   " giras a la habitacion "+actX+","+(actY-1)+","+actZ+"\n";
	    }
	    if (hAct.puedeIncZ() && hAct.getJuegos()[4]!=0){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[4] +
	    		   " avanzas a la habitacion "+actX+","+actY+","+(actZ+1)+"\n";
	    }
	    if (hAct.puedeDecZ() && hAct.getJuegos()[5]!=0){
	    	msg += "Si se resuelve el problema " + hAct.getJuegos()[5] +
	 	   		   " retrocedes a la habitacion "+actX+","+actY+","+(actZ-1)+"\n";;
	    }
	    return msg;
	}

	/**
	 * Consulta la posicion inicial<br>
	 * @return Coordenada x
	 */
	public int getIniX() {
		return iniX;
	}

	/**
	 * Actualiza la posicion inicial<br>
	 * @param iniX Coordenada x
	 */
	public void setIniX(int iniX) {
		this.iniX = iniX;
	}

	/**
	 * Consulta la posicion inicial<br>
	 * @return Coordenada y
	 */
	public int getIniY() {
		return iniY;
	}

	/**
	 * Actualiza la posicion inicial<br>
	 * @param iniY Coordenada y
	 */
	public void setIniY(int iniY) {
		this.iniY = iniY;
	}

	/**
	 * Consulta la posicion inicial<br>
	 * @return Coordenada z
	 */
	public int getIniZ() {
		return iniZ;
	}

	/**
	 * Actualiza la posicion inicial<br>
	 * @param iniZ Coordenada z
	 */
	public void setIniZ(int iniZ) {
		this.iniZ = iniZ;
	}

	/**
	 * Consulta la posicion actual<br>
	 * @return Coordenada x
	 */
	public int getActX() {
		return actX;
	}

	/**
	 * Actualiza la posicion actual<br>
	 * @param actX Coordenada x
	 */
	public void setActX(int actX) {
		this.actX = actX;
	}

	/**
	 * Consulta la posicion actual<br>
	 * @return Coordenada y
	 */
	public int getActY() {
		return actY;
	}

	/**
	 * Actualiza la posicion actual<br>
	 * @param actY Coordenada y
	 */
	public void setActY(int actY) {
		this.actY = actY;
	}

	/**
	 * Consulta la posicion actual<br>
	 * @return Coordenada z
	 */
	public int getActZ() {
		return actZ;
	}

	/**
	 * Actualiza la posicion actual<br>
	 * @param actZ Coordenada z
	 */
	public void setActZ(int actZ) {
		this.actZ = actZ;
	}

	/**
	 * Consulta el edificio
	 * @return edificio
	 */
	public HabitacionCubica[][][] getEdificio() {
		return edificio;
	}

	/**
	 * Actualiza el edificio
	 * @param edificio Nuevo edificio
	 */
	public void setEdificio(HabitacionCubica[][][] edificio) {
		int n = edificio.length;
		HabitacionCubica[][][] edi = new HabitacionCubica[n][n][n];
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++)
				for (int k=0; k<n; k++)
					edi[i][j][k] = edificio[i][j][k].clone();
		this.edificio = edi;
	}

	/**
	 * Consulta la referencia al controlador
	 * @return Referencia al controlador
	 */
	public Controlador getCont() {
		return cont;
	}

	/**
	 * Actualiza la referencia al controlador
	 * @return cont Nueva referencia al controlador
	 */
	public void setCont(Controlador cont) {
		this.cont = cont;
	} 
	
	/**
	 * Consulta la dimension
	 * @return dimension
	 */
	public int getDimension(){
		return dimension;
	}
	
	/**
	 * Actualiza la dimensicon
	 * @param dimension Nueva dimension
	 */
	public void setDimension(int dimension){
		this.dimension = dimension;
	}
	
	/**
	 * Cambia la posicion actual a la inicial 
	 */
	public void vuelveAlInicio(){
		actX = iniX;
		actY = iniY;
		actZ = iniZ;
	}
	
	/**
     * Indica si un estado es valido o no
     * @return true si es valido false en caso contrario
     */
    protected boolean isValid(){
        return true;
    }
    
    /**
     * Indica que tipo de juego hay que resolver 
     * @param numJuego indica el juego a resolver
     * @param estrategia indica la estrategia ocn la que hay que resolverlo
     * @return true si se ha resuleto false en caso contrario
     */
    public boolean resolverProblema(int numJuego, int estrategia){
 		switch(numJuego){
 			// Puerta no se puede abrir, cerrada
 			case 0: {
 				return false;
 			}
 			// OchoPuzzle
 			case 1: {
 				int [][] tabla = new int [3][3];
 		 		tabla[0][0] = 1; tabla[0][1] = 3; tabla[0][2] = 4;
 		 		tabla[1][0] = 8; tabla[1][1] = 0; tabla[1][2] = 2;
 		 		tabla[2][0] = 7; tabla[2][1] = 6; tabla[2][2] = 5;
 		 		OchoPuzzle op = new OchoPuzzle(tabla,1,1,cont);
 		 		return op.resolver(estrategia);
			}
 			// Misioneros y canibales
 			case 2: {
				MisionerosYCanibales myc = new MisionerosYCanibales(3,3,1,cont);
				return myc.resolver(estrategia);
			}
			// Lobo, Cabra y Col
 			case 3: {
				LoboCabraCol lcc = new LoboCabraCol(1,1,1,1,cont);
				return lcc.resolver(estrategia);
			}
			// Mono
 			case 4: {
				Mono m = new Mono(0,false,2,false,cont);
				return m.resolver(estrategia);
			}
			// Palillos
 			case 5: {
				Palillos p = new Palillos(6,5,cont);
				return p.resolver(estrategia);
			}
			// Jarras
 			case 6: {
				Jarras j = new Jarras(0,0,cont);
				return j.resolver(estrategia);
			}
			default: {
				cont.mostrar("No existe el juego " + numJuego);
				return false;
			}
 		}
    }
    
}

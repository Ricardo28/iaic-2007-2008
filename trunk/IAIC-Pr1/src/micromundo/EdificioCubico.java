package micromundo;

import java.util.Enumeration;
import java.util.Vector;

import programa.Controlador;
import aima.search.Heuristic;
import aima.search.State;
import aima.search.Successor;

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
    
    public EdificioCubico(){
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
	   
	public boolean isGoal() {
		return edificio[actX][actY][actZ].esSalida();
	}

	public Enumeration<Successor> successors() {
		Vector<Successor> successorVec = new Vector<Successor>();
		if (edificio[actX][actY][actZ].puedeIncX()){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX+1, actY, actZ, edificio, cont);
			String operador = "Incrementa X -> ("+(actX+1)+","+actY+","+actZ+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeDecX()){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX-1, actY, actZ, edificio, cont);
			String operador = "Decrementa X -> ("+(actX-1)+","+actY+","+actZ+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeIncY()){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX, actY+1, actZ, edificio, cont);
			String operador = "Incrementa Y -> ("+actX+","+(actY+1)+","+actZ+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeDecY()){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX, actY-1, actZ, edificio, cont);
			String operador = "Decrementa Y -> ("+actX+","+(actY-1)+","+actZ+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeIncZ()){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX+1, actY, actZ, edificio, cont);
			String operador = "Incrementa Z -> ("+actX+","+actY+","+(actZ+1)+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		if (edificio[actX][actY][actZ].puedeDecX()){
			EdificioCubico nuevoEstado = new EdificioCubico(dimension, iniX, iniY, iniZ, actX-1, actY, actZ, edificio, cont);
			String operador = "Decrementa Z -> ("+actX+","+actY+","+(actZ-1)+")";
			successorVec.add(new Successor((EdificioCubico)nuevoEstado,operador,1));
		}
		return successorVec.elements();
	}

	public float h() {
		// TODO hacer la heuristica
		return 0;
	}
	
	/**
	  * genera un mensaje que indica el estado actual 
	  */
	 public String toString(){
		 String msg = "\n Habitacion actual: ("+ actX + "," + actY + "," + actZ + ")\n";
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
		this.edificio = edificio;
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
		for (int i=0; i<dimension; i++)
			for (int j=0; j<dimension; j++)
				for (int k=0; k<dimension; k++){
					System.out.println((i+1)+","+(j+1)+","+(k+1)+"\t:\t"+
							edificio[i][j][k].getJuegos()[0]+","+
							edificio[i][j][k].getJuegos()[1]+","+
							edificio[i][j][k].getJuegos()[2]+","+
							edificio[i][j][k].getJuegos()[3]+","+
							edificio[i][j][k].getJuegos()[4]+","+
							edificio[i][j][k].getJuegos()[5]);
				}
	}

}

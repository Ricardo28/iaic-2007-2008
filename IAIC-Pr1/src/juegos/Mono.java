package juegos;

import java.util.Vector;
import java.util.Enumeration;

import aima.search.Successor;

/** <b>Problema del Mono:</b><br>
 * Hay un mono en la puerta de una habitación. En el centro de la habitación hay un
 * plátano colgado del techo. El mono está hambriento y quiere conseguir el plátano
 * pero no alcanza porque está muy alto. En la habitación también hay una ventana y
 * debajo de ella hay una caja que le permitiría alcanzar el plátano si se subiera a ella.
 * El mono puede realizar las siguientes acciones: andar por el suelo, subirse a la caja,
 * empujar la caja (si el mono está en la misma posición que la caja) y coger el plátano
 * (si está subido encima de la caja y la caja está justo debajo del plátano).<br>
 * \t0: el mono/caja se encuentran en la puerta<br>
 * \t1: el mono/caja se encuentran en la centro<br>
 * \t2: el mono/caja se encuentran en el ventana
 */
public class Mono extends Juego{

	/**
	 * Posicion del mono
	 */
	private int pos;
	
	/**
	 * Posicion de la caja
	 */
	private int caja;
	
	/**
	 * Si esta sobre la caja
	 */
	private boolean sobreCaja;
	
	/**
	 * Si ha cogido el platano
	 */
	private boolean platano;
	
	/**
	 * Crea un estado del juego del mono segun el avance del juego
	 */
	public Mono(int pos, boolean subido, int caja, boolean platano){
		this.pos = pos;
		this.sobreCaja = subido;
		this.caja = caja;
		this.platano = platano;
	}	
	
	/**
	 * Modifica la posicion del mono encima de la caja o en el suelo
	 * @param p indica la posicion del mono
	 */
	 public void ponPos(int p){
		this.pos = p;
	 }	
	 
	 /**
	  * Indica la posicion de la ventana
	  * @param p
	  */
	public void ponSubido(boolean s){
		this.sobreCaja = s;
	}
	
	public void ponCaja(int p){
		this.caja = p;
	}	
	
	public void ponPlatano(boolean p){
		this.platano = p;
	}
	
	public int damePos(){
		return this.pos;
	}
	
	public boolean dameSubido(){
		return this.sobreCaja;
	}
	
	public int damePosCaja(){
		return this.caja;
	}
	
	public boolean damePlatano(){
		return this.platano;
	}

	/**
	 * Indica si un estado es solucion
	 * @return true si es final el estado
	 */
	public boolean isGoal(){
		return platano == true;
	}
	
	/**
	 * Genera los sucesores del estado actual
	 * @return Enumeration con los sucesores generados
	 */
	public Enumeration<Successor> successors(){

	 	Vector<Successor> successorVec = new Vector<Successor>();

	 	String operador = "";
	 	nodosExpandidos++;

	 	if (pos != 2 && !sobreCaja){
	 		operador = "andaHaciaVentana";
	 		Mono nuevoEstado = new Mono(pos+1,sobreCaja,caja,platano);
		 	successorVec.addElement(new Successor(nuevoEstado, operador, 1));
		}

	 	if (pos != 0 && !sobreCaja){
	 		operador = "andaHaciaPuerta";
	 		Mono nuevoEstado = new Mono(pos-1,sobreCaja,caja,platano);
		 	successorVec.addElement(new Successor(nuevoEstado, operador, 1));
		}

	 	if (pos == caja && sobreCaja && pos != 2){
	 		operador = "empujaCajaHaciaVentana";
	 		Mono nuevoEstado = new Mono(pos+1,sobreCaja,caja+1,platano);
		 	successorVec.addElement(new Successor(nuevoEstado, operador, 1));
		}

	 	if (pos == caja && sobreCaja && pos !=0){
	 		operador = "empujaCajaHaciaPuerta";
	 		Mono nuevoEstado = new Mono(pos-1,sobreCaja,caja-1,platano);
		 	successorVec.addElement(new Successor(nuevoEstado, operador, 1));
		}

	 	if (pos == caja && !sobreCaja){
	 		operador ="subeCaja";
	 		Mono nuevoEstado = new Mono(pos,true,caja,platano);
		 	successorVec.addElement(new Successor(nuevoEstado, operador, 1));
		}
	 	
	 	if (pos == caja && sobreCaja){
	 		operador ="bajaCaja";
	 		Mono nuevoEstado = new Mono(pos,false,caja,platano);
		 	successorVec.addElement(new Successor(nuevoEstado, operador, 1));
		}
	 	
	 	if (sobreCaja && caja == 1 && !platano){
	 		operador ="cogePlatano";
	 		Mono nuevoEstado = new Mono(pos,sobreCaja,caja,true);
		 	successorVec.addElement(new Successor(nuevoEstado, operador, 1));
		}
	 		 
	 	return successorVec.elements();
	}
	
	/**
	 * Genera la heuristica del estado en el que se encuentra
	 * @return devuelve la heuristica correspondiente al estado actual
	 */
	public float h() {
		if (pos == 0) return 5;
		if (pos == 2 && caja != 2) return 5;
		if (pos == 1 && caja != 1) return 4;
		if (pos == 2 && caja == 2 && !sobreCaja) return 3;
		if (pos == 2 && caja == 2 && sobreCaja) return 2;
		if (pos == 1 && sobreCaja && !platano) return 1;
		if (pos == 1 && sobreCaja && platano) return 0;
		return 6;
	}
	
	/**
	 * Genera un mensaje del estado en el que se encuentra
	 * @return un string con el mensaje que especifica en el estado que se encuentra
	 */
	public String toString() {
		return "(Pos:" + pos + ", Subido:" + (sobreCaja?1:0) + ", C:" + caja + ", Pl:" + (platano?1:0) + ")";
	}
	
	/**
	 * Prueba el problema del mono con todas las estrategias
	 * @param args
	 */
    public static void main(String[] args){
		Mono m = new Mono(0,false,2,false);
		System.out.println(m);
		for (int i=1; i<=6; i++)
			m.resolver(i);
	}
	 
}


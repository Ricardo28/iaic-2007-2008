package micromundo;

/**
 * Representa una habitacion de un edificio cubico.<br>
 * Tiene puertas en las cuatro paredes, en el techo y en el suelo.<br>
 * Cada puerta puede tener un juego distinto. Puede haber puertas cerradas.<br>
 * @author Miguel Angel Díaz
 * @author David Martín
 * @author Alberto Vaquero
 */
public class HabitacionCubica {
	
	/**
	 * Coordenadas de la habitacion dentro del edificio cubico
	 */
	private int x, y, z;
	
	/**
	 * Dimensiones del edificio cubico 
	 */
	private int n;
	
	/**
	 * Juegos de cada puerta (arriba, abajo, izquierda, derecha, delante y detras) 
	 */
	private int[] juegos;
	
	/**
	 * Crea una nueva instancia de una habitacion cubica
	 * @param x Coordenada x
	 * @param y Coordenada y
	 * @param z Coordenada z
	 * @param n Dimension del edificio
	 * @param juegos Juego de cada puerta
	 */
	public HabitacionCubica(int x, int y, int z, int n, int[] juegos){
		this.x = x;
		this.y = y;
		this.z = z;
		this.n = n;
		this.juegos = juegos;
	}
	
	/**
	 * Devuelve una copia del objeto actual
	 * @return copia 
	 */
	public HabitacionCubica clone(){
		int[] j = new int[6];
		for (int i=0; i<6; i++)
			j[i] = juegos[i];
		return new HabitacionCubica(x, y, z, n, j);
	}

	/**
	 * Consulta la coordenada x
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Consulta la coordenada y
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Consulta la coordenada z
	 * @return z
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Actualiza las coordenadas de la habitacion
	 * @param x the x to set
	 * @param y the y to set
	 * @param z the z to set
	 */
	public void setXYZ(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Consulta la dimension
	 * @return la dimension
	 */
	public int getN() {
		return n;
	}

	/**
	 * Actualiza la dimension
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}
	
	/**
	 * Consulta los juegos
	 * @return juegos
	 */
	public int[] getJuegos() {
		return juegos;
	}

	/**
	 * Actualiza los juegos
	 * @param juegos the juego to set
	 */
	public void setJuego(int[] juegos) {
		this.juegos = juegos;
	}

	/**
	 * Consulta si la habitacion actual es una salida del edificio cubico (esquina)
	 * @return true si la habitacion es una esquina del edificio cubico
	 */
	public boolean esSalida(){
		int ceros = ((x==0)?1:0) + ((y==0)?1:0) + ((z==0)?1:0);
		int nMenosUnos = ((x==n-1)?1:0) + ((y==n-1)?1:0) + ((z==n-1)?1:0);
		return (ceros == 3) || (ceros == 2 && nMenosUnos == 1) || 
		       (ceros == 1 && nMenosUnos == 2) || (nMenosUnos == 3);
	}
	
	/**
	 * @return true si la x se puede incrementar sin salirse del edificio
	 */
	public boolean puedeIncX(){
		return x < n-1;
	}
	
	/**
	 * @return true si la x se puede decrementar sin salirse del edificio
	 */
	public boolean puedeDecX(){
		return x > 0;
	}
	
	/**
	 * @return true si la y se puede incrementar sin salirse del edificio
	 */
	public boolean puedeIncY(){
		return y < n-1;
	}
	
	/**
	 * @return true si la y se puede decrementar sin salirse del edificio
	 */
	public boolean puedeDecY(){
		return y > 0;
	}
	
	/**
	 * @return true si la z se puede incrementar sin salirse del edificio
	 */
	public boolean puedeIncZ(){
		return z < n-1;
	}
	
	/**
	 * @return true si la z se puede decrementar sin salirse del edificio
	 */
	public boolean puedeDecZ(){
		return z > 0;
	}
	
}

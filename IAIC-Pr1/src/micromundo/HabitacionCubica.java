package micromundo;

/**
 * 
 * @author Miguel Angel Diaz
 *
 */
public class HabitacionCubica {
	
	private int x, y, z;
	
	/**
	 * Dimensiones del edificio cubico 
	 */
	private int n; 
	
	private int juego;
	
	private boolean resuelto;
	
	public HabitacionCubica(int x, int y, int z, int n, int juego){
		this.x = x;
		this.y = y;
		this.z = z;
		this.n = n;
		this.juego = juego;
		resuelto = false;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	
	/**
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
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}
	
	/**
	 * @return the juego
	 */
	public int getJuego() {
		return juego;
	}

	/**
	 * @param juego the juego to set
	 */
	public void setJuego(int juego) {
		this.juego = juego;
	}

	/**
	 * @return the resuelto
	 */
	public boolean isResuelto() {
		return resuelto;
	}

	/**
	 * @param resuelto the resuelto to set
	 */
	public void setResuelto(boolean resuelto) {
		this.resuelto = resuelto;
	}

	/**
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

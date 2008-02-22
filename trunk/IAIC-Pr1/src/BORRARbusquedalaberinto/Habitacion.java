package BORRARbusquedalaberinto;

/**
 * 
 * @author Usuario
 *
 */
public class Habitacion {
	
    protected int numero;
	
    protected int[] adyacentes;
    
    protected boolean esEntrada;
    
    protected boolean esSalida;
    
    protected boolean esExterior;
    
    protected int juego;
    
    protected boolean resuelto;
	
    /**
     * crea una nueva instancia de habitacion
     * @param numero 
     * @param l lista de habiatciones adyacentes
     * @param entrada si es entrada 
     * @param salida  si es salida
     * @param exterior si es exterior
     * @param juego numero de juego
     * @param r indica si ya esta resuelta esa habitacion
     */
    public Habitacion(int numero,int[] l, boolean entrada, boolean salida, boolean exterior,int juego,boolean r){
        adyacentes = new int[l.length];
        for(int i =0;i<l.length;i++)
        	adyacentes[i] = l[i];
        esEntrada=entrada;
        esSalida=salida;
        esExterior=exterior;
        this.juego = juego;
        this.resuelto = r;
    }
    
    /**
     * devuelve el numero de la habitacion actual
     * @return el numero de la habitacion actual
     */
    public int getNumero(){
    	return numero;
    }
    
    /**
     * devuelve si es salida o no la habitacion actual
     * @return true si es salida false en caso contrario
     */
    public boolean esSalida(){
    	return esSalida;
    }
    
    /**
     * devuelve si es entrada o no la habitacion actual
     * @return true si es entrada false en caso contrario
     */
    public boolean esEntrada(){
    	return esEntrada;
    }
    
    /**
     * devuelve si es esterior o no la habitacion actual
     * @return true si es exterior false en caso contrario
     */
    public boolean esExterior(){
    	return esExterior;
    }
    
    /**
     * devuelve los adyacentes de la habitacion actual
     * @return devuelve la lista de adyacentes
     */
    public int[] dameAdyacentes(){
    	return (this.adyacentes);
    }
    
}

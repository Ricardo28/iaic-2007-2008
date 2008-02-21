package micromundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargarEdificio {
	/**
     *  Creates a new instance of CargarEdificio
     */
    public CargarEdificio() {
    }
    
    /**
     * Carga la informacion del fichero de entrada
     * @param ruta del fichero
     * @return HabitacionCubica[][][] con el edificio
     */
    public void cargarEdificio(EdificioCubico edi, String ruta){
    	FileReader archivo;
        BufferedReader buf;
        String linea;
        try {
	        archivo = new FileReader(ruta);
	        buf = new BufferedReader(archivo);
	        linea = buf.readLine();
	        int n = leerDimension(linea);
	        linea = buf.readLine();
	        int[] inicial = leerInicio(linea, n);
	        edi.setDimension(n);
	        edi.setIniX(inicial[0]);
	        edi.setIniY(inicial[1]);
	        edi.setIniZ(inicial[2]);
	        edi.vuelveAlInicio();
	        HabitacionCubica[][][] habs = new HabitacionCubica[n][n][n];
	        HabitacionCubica hab;
	        // Saltar la cabecera de titulos (Hab Juegos (arriba, abajo, izq, der, del, det)
	        buf.readLine();
	        for (int i=0; i<n*n*n; i++){
	            linea = buf.readLine();
	        	hab = procesar(linea, n);
	            habs[hab.getX()][hab.getY()][hab.getZ()] = hab;
	        }
	        edi.setEdificio(habs);
	        buf.close();
	    }
        catch (IOException e){
        	System.out.println("Error al cargar el archivo " + ruta);
        }
    }
    
    /**
	 * Esta función se encarga de leer cada linea y de ir creando cada habitación 
	 * @param linea del archivo de entrada
	 * @return Habitacion con la informacion de la linea
	 */
    public HabitacionCubica procesar(String linea, int n){
    	
    	linea = eliminaEspaciosTabs(linea);
		
		// Posicion
    	int dosPuntos = linea.indexOf(':');
    	int[] hab = trataLista(linea.substring(0, dosPuntos));
    	for (int i=0; i<n; i++)
    		hab[i] = hab[i] - 1;
    	// Juegos de cada puerta
    	int[] juegos = trataLista(linea.substring(dosPuntos+1));
		
		return new HabitacionCubica(hab[0], hab[1], hab[2], n, juegos);
	}
    
    private int leerDimension(String linea){
    	String n = linea.substring(11);
    	int dim;
    	try {
    		dim = Integer.valueOf(n);
    	}
    	catch(NumberFormatException e){
    		dim = -1;
    	}
    	return dim;
    }
    
    private int[] leerInicio(String linea, int n){
    	String aux = eliminaEspaciosTabs(linea);
    	aux = aux.substring(7);
    	int[] inicio = trataLista(aux);
    	for (int i=0; i<n; i++)
    		inicio[i] = inicio[i]-1;
    	return inicio;
    }
	
	/**
	 * Elimina espacios  y tabuladores de cadenas de caracteres
	 * @param linea entrada
	 * @return linea de entrada filtrada
	 */
    public String eliminaEspaciosTabs(String linea){
		String nueva = new String();
		for (int i=0; i<linea.length(); i++)
			if (linea.charAt(i)!=' ' && linea.charAt(i)!='\t')
				nueva += linea.charAt(i);
		return nueva;
	}
	
	/**
	 * Convierte cadenas de la forma "1,4,5" a arrays de entero: [1,4,5]
	 * @param cadena entrada
	 * @return array de enteros convertido
	 * @throws NumberFormatException si cadena contiene caract. distintos de digito o ','
	 */
	public int[] trataLista(String cadena) throws NumberFormatException{
		int[] puertas = new int[cadena.length()/2+1];
		int i=0, n=0;
		while (i<cadena.length()){
			if (cadena.charAt(i) != ','){
				puertas[n] = Integer.valueOf(""+cadena.charAt(i));
				n++;
			}
			i++;
		}
		return puertas;
	}
	
	public static void main(String[] args){
		EdificioCubico edi = new EdificioCubico(null);
		CargarEdificio ce = new CargarEdificio();
		ce.cargarEdificio(edi, "prueba.txt");
		edi.imprime();
		System.out.println(edi.h());
	}
	
}

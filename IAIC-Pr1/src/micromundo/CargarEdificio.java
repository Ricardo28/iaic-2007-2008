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
     * @throws IOException 
     */
    public static void cargarEdificio(EdificioCubico edi, String ruta){
    	FileReader archivo;
        BufferedReader buf;
        String linea;
        try {
	        archivo = new FileReader(ruta);
	        buf = new BufferedReader(archivo);
	        linea = buf.readLine();
	        int n = leerDimension(linea);
	        linea = buf.readLine();
	        // Posicion inicial y dimension
	        int[] inicial = leerInicio(linea);
	        edi.setDimension(n);
	        edi.setIniX(inicial[0]);
	        edi.setIniY(inicial[1]);
	        edi.setIniZ(inicial[2]);
	        edi.vuelveAlInicio();
	        // Habitaciones
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
	 * Este metodo se encarga de leer cada linea y de ir creando cada habitación 
	 * @param linea del archivo de entrada
	 * @param n dimension del edificio cubico
	 * @return Habitacion con la informacion de la linea
	 */
    private static HabitacionCubica procesar(String linea, int n){
    	
    	String linea2 = eliminaEspaciosTabs(linea);
		
		// Posicion
    	int dosPuntos = linea2.indexOf(':');
    	int[] hab = trataLista(linea2.substring(0, dosPuntos));
    	for (int i=0; i<3; i++)
    		hab[i] = hab[i] - 1;
    	// Juegos de cada puerta
    	int[] juegos = trataLista(linea2.substring(dosPuntos+1));
		
		return new HabitacionCubica(hab[0], hab[1], hab[2], n, juegos);
	}
    
    /**
     * Extrae la dimension de la primera linea de texto
     * @param linea Primera linea del archivo de entrada
     * @return dimension leida
     */
    private static int leerDimension(String linea){
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
    
    /**
     * Lee la posicion inicial
     * @param linea del archivo de entrada
     * @return [x,y,z] array de enteros
     */
    private static int[] leerInicio(String linea){
    	String aux = eliminaEspaciosTabs(linea);
    	aux = aux.substring(7);
    	int[] inicio = trataLista(aux);
    	for (int i=0; i<3; i++)
    		inicio[i] = inicio[i]-1;
    	return inicio;
    }
	
	/**
	 * Elimina espacios  y tabuladores de cadenas de caracteres
	 * @param linea entrada
	 * @return linea de entrada filtrada
	 */
    private static String eliminaEspaciosTabs(String linea){
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
	private static int[] trataLista(String cadena) throws NumberFormatException{
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
	
}

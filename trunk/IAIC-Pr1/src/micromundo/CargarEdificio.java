package micromundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import Busquedalaberinto.Habitacion;

public class CargarEdificio {
	/**
     *  Creates a new instance of fichero
     */
    public CargarEdificio() {
    }
    
    /**
     * Carga la informacion del fichero de entrada
     * @param ruta del fichero
     * @return Vector de Habitaciones con el laberinto
     * @throws IOException si se produce un error al leer el fichero
     */
    public Vector<HabitacionCubica> cargarTablero(String ruta) throws IOException{
        Vector<HabitacionCubica> vector = new Vector<HabitacionCubica>();
        FileReader archivo;
        BufferedReader buf;
        String linea;
        archivo = new FileReader(ruta);
        buf = new BufferedReader(archivo);
        linea = buf.readLine();
        while( linea != null){                
        	HabitacionCubica habitacion = procesar(linea);
            vector.add(habitacion);
            linea=buf.readLine();
        }    
        buf.close();        
        return vector;
    }
    
    /**
	 * Esta función se encarga de leer cada linea y de ir creando cada habitación 
	 * @param linea del archivo de entrada
	 * @return Habitacion con la informacion de la linea
	 */
    public HabitacionCubica procesar(String linea){
    	
		boolean entrada, salida, exterior;
		int[] puertas;
		int nHab, nProb;
		String entStr, salStr, exStr, puerStr;
		int x=0, y=0, z=0, n=0;
		
		linea = eliminaEspaciosTabs(linea);
		
		// Numero de habitacion
		nHab = Integer.parseInt(linea.substring(0, linea.indexOf(':')));
		linea = linea.substring(linea.lastIndexOf(':')+1);
		
		// Entrada
		entStr = linea.substring(0, linea.indexOf(';'));
		entrada = (Integer.parseInt(entStr) == 1) ? true : false; //si es 1 -> true, si no false
		linea = linea.substring(linea.indexOf(';')+1);
		
		// Salida
		salStr = linea.substring(0, linea.indexOf(';'));
		salida = (Integer.parseInt(salStr) == 1) ? true : false;  
		linea = linea.substring(linea.indexOf(';')+1);
		
		// Exterior
		exStr = linea.substring(0, linea.indexOf(';'));
		exterior = (Integer.parseInt(exStr) == 1) ? true : false;
		linea = linea.substring(linea.indexOf(';')+1);
		
		// Puertas
		puerStr = linea.substring(0, linea.indexOf(';'));
		puertas = trataPuertas(puerStr);
		
		// Numero del juego
		nProb = Integer.valueOf(""+linea.charAt(linea.length()-1));
		
		HabitacionCubica hab = new HabitacionCubica(x, y, z, n, nProb);
		
		return hab;
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
	public int[] trataPuertas(String cadena) throws NumberFormatException{
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

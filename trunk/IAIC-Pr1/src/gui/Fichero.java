package gui;

import Busquedalaberinto.*;
import java.io.*;
import java.util.Vector;

/**
 *
 * @author usuario
 */
public class Fichero {
    
    /** Creates a new instance of fichero */
    public Fichero() {
    }
    
    public Vector<Habitacion> cargarTablero(String ruta) throws IOException{
        Vector<Habitacion> vector = new Vector<Habitacion>();
        FileReader leer = new FileReader(ruta);
        BufferedReader buf = new BufferedReader(leer);
        String linea = buf.readLine();
        while (linea != null){                
            Habitacion habitacion = procesar(linea);
            vector.add(habitacion);
            linea = buf.readLine();
        }    
        buf.close();        
        return vector;
    }
    
    /*
	 * Esta función se encarga de leer cada linea y de
	 * ir creando cada habitación 
	 */
	public Habitacion procesar(String linea){
	
		boolean entrada, salida, exterior;
		int [] puertas;
		int nHab, nProb;
		String entStr, salStr, exStr, puerStr;
		
		linea = eliminaEspaciosTabs(linea);
		
		/*
		 * Divide cada linea en subcadenas, cada una de estas
		 * representa un atributo de la clase habitación
		 */
		nHab = Integer.parseInt(linea.substring(0, linea.indexOf(':')));
		linea = linea.substring(linea.lastIndexOf(':')+1);
		entStr = linea.substring(0, linea.indexOf(';'));
		linea = linea.substring(linea.indexOf(';')+1);
		salStr = linea.substring(0, linea.indexOf(';'));
		linea = linea.substring(linea.indexOf(';')+1);
		exStr = linea.substring(0, linea.indexOf(';'));
		linea = linea.substring(linea.indexOf(';')+1);
		puerStr = linea.substring(0, linea.indexOf(';'));
		nProb = Integer.valueOf(linea.charAt(linea.length()-1));
		
		entrada = (Integer.parseInt(entStr) == 1) ? true : false; //si es 1 -> true, si no false
		salida = (Integer.parseInt(salStr) == 1) ? true : false;  //si es 1 -> true, si no false
		exterior = (Integer.parseInt(exStr) == 1) ? true : false; //si es 1 -> true, si no false
		
		puertas = trataPuertas(puerStr+",;");
		Habitacion hab = new Habitacion(nHab,puertas,entrada,salida,exterior,nProb,false);
		
		return hab;
	}
	
	public String eliminaEspaciosTabs(String linea){
		String nueva = new String();
		for (int i=0; i<linea.length(); i++)
			if (linea.charAt(i)!=' ' && linea.charAt(i)!='\t')
				nueva += linea.charAt(i);
		return nueva;
	}
	
	public int [] trataPuertas(String cadena){
		
		int j = 0;
		int n = 0;
		String [] s = new String[cadena.length()];
		                       
		while (!cadena.contentEquals(";")){
			s[j] = cadena.substring(0, cadena.indexOf(','));
			cadena = cadena.substring(cadena.indexOf(',')+1);
			n++;
			j++;			
		}
		
		int [] puertas = new int[n];
		for(int i = 0;i<n;i++){
			puertas[i] = Integer.parseInt(s[i]);
		}
		
		return puertas;
		
	}  
    
}

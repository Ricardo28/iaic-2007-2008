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
    
    public Vector<Habitacion> cargarTablero(String ruta)throws IOException{
        Vector<Habitacion> vector = new Vector<Habitacion>();
        FileReader leer;
        BufferedReader filtro;
        String linea;
        leer=new FileReader(ruta);
        filtro=new BufferedReader(leer);
        linea=filtro.readLine();
        while(linea!=null){                
            Habitacion habitacion=procesar(linea);
            vector.add(habitacion);
            linea=filtro.readLine();
        }    
        filtro.close();        
        return vector;
    }
    
    /*
	 * Esta función se encarga de leer cada linea y de
	 * ir creando cada habitación 
	 */
	public Habitacion procesar(String linea){
	
		boolean entrada, salida, exterior;
		int [] puertas;
		int nHab, nProb,i;
		String habStr, entStr, salStr, exStr, puerStr, linea2;
		
		/*
		 * Divide cada linea en subcadenas, cada una de estas
		 * representa un atributo de la clase habitación
		 */
		habStr = linea.substring(0, linea.indexOf(':')-1);
		linea = linea.substring(linea.lastIndexOf(':')+1);
		entStr = linea.substring(1, linea.indexOf(';')-1);
		linea = linea.substring(linea.indexOf(';')+1);
		salStr = linea.substring(1, linea.indexOf(';')-1);
		linea = linea.substring(linea.indexOf(';')+1);
		exStr = linea.substring(1, linea.indexOf(';')-1);
		linea = linea.substring(linea.indexOf(';')+1);
		puerStr = linea.substring(1, linea.indexOf(';')-1);
		if (linea.charAt(linea.length()-1) != ' '){
			linea2 = linea.substring(linea.indexOf(';')+2,linea.length());
		}
		else {
			linea2 = linea.substring(linea.indexOf(';')+2,linea.length()-1);
		}
		
		/*
		 * Convierte cada subcadena en un entero
		 */
		nHab = Integer.parseInt(habStr);
		i = Integer.parseInt(entStr);
		if (i == 1){entrada=true;} //si es 1 -> true, si no false
		else{entrada=false;}	//se supone que sólo vale 1 o 0	
		i = Integer.parseInt(salStr);
		if (i == 1){salida=true;} //si es 1 -> true, si no false
		else{salida=false;}
		i = Integer.parseInt(exStr);
		if (i == 1){exterior=true;} //si es 1 -> true, si no false
		else{exterior=false;}
		char problema = linea2.charAt(0);
		nProb=6;
		if (problema=='1')
			nProb=1;
		if (problema=='2')
			nProb=2;
		if (problema=='3')
			nProb=3;
		if (problema=='4')
			nProb=4;
		if (problema=='5')
			nProb=5;

		puertas = trataPuertas(puerStr+",;");
		Habitacion hab = new Habitacion(nHab,puertas,entrada,salida,exterior,nProb,false);
		
		return hab;
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

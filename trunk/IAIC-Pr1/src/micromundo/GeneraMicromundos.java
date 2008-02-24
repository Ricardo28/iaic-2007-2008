package micromundo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase auxiliar para crear archivos de prueba
 * @author Miguel Angel Díaz
 * @author David Martín
 * @author Alberto Vaquero
 */
public class GeneraMicromundos {
	
	/**
	 * Generador de pruebas
	 * @param args atributos (no utilizado)
	 */
	public static void main(String[] args){
		try {
			// Pruebas fijas,edificios cubicos (3x3x3) con el mismo juego
			for (int h=1; h<=6; h++){
				FileWriter archivo = new FileWriter(new File("."+File.separator+"pruebas"+File.separator+"juego"+h+".txt"));
		        BufferedWriter buf = new BufferedWriter(archivo);
		        int n = 3;
		        int centro = n/2 + 1;
		        buf.write("Dimension:\t"+ n + "\n");
		        buf.write("Inicio:\t" + centro + "," + centro + "," + centro + "\n");
		        buf.write("Hab\t\tJuegos (arriba,\tabajo,\tizq,\tder,\tdel,\tdet)\n");
		        for (int i=1; i<=n; i++){
		        	for (int j=1; j<=n; j++){
		        		for (int k=1; k<=n; k++){
		        			buf.write(i+","+j+","+k+"\t:\t\t");
		        			buf.write(h+",\t\t"+h+",\t\t"+h+",\t\t"+h+",\t\t"+h+",\t\t"+h+"\n");
		        		}
		        	}
		        }
		        buf.close();
		        archivo.close();
			}
			// Pruebas aleatorias, edificios cubicos (3x3x3) con distintos juegos
			for (int h=1; h<=6; h++){
				FileWriter archivo = new FileWriter(new File("."+File.separator+"pruebas"+File.separator+"aleatoria"+h+".txt"));
		        BufferedWriter buf = new BufferedWriter(archivo);
		        int n = 3;
		        int centro = n/2 + 1;
		        buf.write("Dimension:\t"+ n + "\n");
		        buf.write("Inicio:\t" + centro + "," + centro + "," + centro + "\n");
		        buf.write("Hab\t\tJuegos (arriba,\tabajo,\tizq,\tder,\tdel,\tdet)\n");
		        Random r = new Random();
		        for (int i=1; i<=n; i++){
		        	for (int j=1; j<=n; j++){
		        		for (int k=1; k<=n; k++){
		        			buf.write(i+","+j+","+k+"\t:\t\t");
		        			// Mitad de las puertas cerradas
		        			for (int m=0; m<6; m++){
		        				// Genera numero entre 0 y 11
		        				int nr = r.nextInt(12);
		        				if (nr > 6)
		        					nr = 0;
		        				buf.write(Integer.toString(nr));
		        				if (m != 5)
		        					buf.write(",\t\t");
		        			}
		        			buf.write("\n");
		        		}
		        	}
		        }
		        buf.close();
		        archivo.close();
			}
			// Un edificio cubico aleatorio de 5x5x5
			FileWriter archivo = new FileWriter(new File("."+File.separator+"pruebas"+File.separator+"edificio.txt"));
	        BufferedWriter buf = new BufferedWriter(archivo);
	        int n = 5;
	        int centro = n/2 + 1;
	        buf.write("Dimension:\t"+ n + "\n");
	        buf.write("Inicio:\t" + centro + "," + centro + "," + centro + "\n");
	        buf.write("Hab\t\tJuegos (arriba,\tabajo,\tizq,\tder,\tdel,\tdet)\n");
	        Random r = new Random();
	        for (int i=1; i<=n; i++){
	        	for (int j=1; j<=n; j++){
	        		for (int k=1; k<=n; k++){
	        			buf.write(i+","+j+","+k+"\t:\t\t");
	        			// Mitad de las puertas cerradas
	        			for (int m=0; m<6; m++){
	        				// Genera numero entre 0 y 11
	        				int nr = r.nextInt(12);
	        				if (nr > 6)
	        					nr = 0;
	        				buf.write(Integer.toString(nr));
	        				if (m != 5)
	        					buf.write(",\t\t");
	        			}
	        			buf.write("\n");
	        		}
	        	}
	        }
	        buf.close();
	        archivo.close();
		}
		catch (IOException e) {
			
		}
    }

}

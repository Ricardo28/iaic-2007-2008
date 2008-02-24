package micromundo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneraMicromundos {
	
	public static void main(String[] args){
		try {
			// Pruebas fijas, un edificio cubico con el mismo juego
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
			}
			// Pruebas aleatorias, un edificio cubico con distintos juegos
			for (int h=1; h<=6; h++){
				FileWriter archivo = new FileWriter(new File("."+File.separator+"pruebas"+File.separator+"prueba"+h+".txt"));
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
		        				buf.write(nr+",\t\t");
		        			}
		        			buf.write("\n");
		        		}
		        	}
		        }
		        buf.close();
			}
		}
		catch (IOException e) {
			
		}
    }

}

package micromundo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneraMicromundos {
	
	public static void main(String[] args){
		try {
			FileWriter archivo = new FileWriter(new File("."+File.separator+"prueba.txt"));
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
	        			buf.write("1,\t\t1,\t\t1,\t\t1,\t\t1,\t\t1\n");
	        		}
	        	}
	        }
	        buf.close();
		}
		catch (IOException e) {
			
		}
    }

}

package gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author usuario_local
 */
public class Filtro extends FileFilter{
    
	/** Creates a new instance of Filtro */
    public Filtro() {
    }
    
    public boolean accept(File f){
        return f.getName().toLowerCase().endsWith(".zip")||f.isDirectory();
    }

    public String getDescription(){
        return "Ficheros xml" ;
    }
}

   

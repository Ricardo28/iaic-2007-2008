package Ventana;

public class Habi {//esta clase identifica al tipo de casillas que vamos a utilizar en la clase vista
    
    private int num_hab;//numero de habitacion 
    private boolean pasar;//indica si un algoritmo ha pasado por esta habitación
    /** Creates a new instance of Habi */
    public Habi() {//constructor por defecto
        num_hab=0;
        pasar=false;
    }
    
    public Habi(int n,boolean m){//otro constructor
        num_hab=n;
        pasar=m;
    }
    
    //getters y setters
    public int get_num_hab(){
        return num_hab;
    }
    public boolean get_pasar(){
        return pasar;
    }
    
}


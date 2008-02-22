package Ventana;

import gui.Solicitud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import micromundo.CargarEdificio;
import micromundo.EdificioCubico;
import programa.Controlador;

/**
 *
 * @author  Martinete
 */
public class Visual extends javax.swing.JFrame {
    
    /**
     * Creates new form Visual
     */
    int n;//numero de habitaciones
    Habi h[][];
    JTextField Edificio[][];//aqui se verá reflejado el edificio
    //EdificioCubico e; 
    int z;
    private static Controlador controlador;
    private String mensaje;//este string sirve para hacer print sobre el jTextArea1
    
    public Visual() {
        n=3;//dimension	
        z=1;//profundidad del edificio
        dibuja();//pintamos la interfaz
        this.setSize(1025,735);
        rellena();//esta funcion rellena de verde por donde hemos pasado
    }
    public void rellena(){//esta funcion colorea y rellena las habitaciones con sus respectivos numeros
        
        //actualizamos las casillas segun la profundiadad
    	/*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	h[i][j].setNum_hab(e.getEdificio()[i][j][z].getN());//cogemos el numero de la habitacion
            	//se supone que el color lo deben modificar los algoritmos, debemos hacer algo para que cada vez que pase 
            	//por una casilla cambie el boolean
            }
    	}*/
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(h[i][j].getPasar()){
                    Edificio[i][j].setBackground(new java.awt.Color(204, 255, 204));//pintamos la casilla
                    Edificio[i][j].setText(""+h[i][j].getNum_hab());
                }else{
                    Edificio[i][j].setText(""+h[i][j].getNum_hab());
                    Edificio[i][j].setForeground(new java.awt.Color(0,0,0));
                    
                }
                //miramos si estamos en la habitación actual
                /*if(h[i][j].get_num_hab()==hab_act){//si estamos en la habitacion actual pintamos su numero de rojo
                    Edificio[i][j].setForeground(new java.awt.Color(255, 0, 51));
                }*/
            }
        }
    }
    public boolean comprueba(){//esta funcion mira si se ha seleccionado solo una opcion
        boolean cierto=false;
        int cont=0;
        boolean[] vector=new boolean[6];
        for(int i=0;i<6;i++){//inicializamos vector
            vector[i]=false;
        } 
        vector[0]=jCheckBox1.isSelected();//cogemos los valores de los ckeck
        vector[1]=jCheckBox2.isSelected();
        vector[2]=jCheckBox3.isSelected();
        vector[3]=jCheckBox4.isSelected();
        vector[4]=jCheckBox5.isSelected();
        vector[5]=jCheckBox6.isSelected();
      
        for(int i=0;i<6;i++){
            if(vector[i]==true){
                cont++;
            }
        }
        if(cont==1){
            cierto=true;
        }else{
            cierto=false;
        }
        return cierto;
    }
    public void dibuja(){//Esta funcion pinta el edificio, es decir, el numero de casillas que tenga la matriz 
    	
    	jDesktopPane1 = new javax.swing.JDesktopPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1=new javax.swing.JCheckBox();
        jCheckBox2=new javax.swing.JCheckBox();
        jCheckBox3=new javax.swing.JCheckBox();
        jCheckBox4=new javax.swing.JCheckBox();
        jCheckBox5=new javax.swing.JCheckBox();
        jCheckBox6=new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        
        int x=20;//eje x
        int y=80;//eje y
        h=new Habi[n][n];//damos tamaño a las matrices
        Edificio=new JTextField[n][n];
        for (int i = 0; i < n; i++) {//matriz de habitaciones
            for(int j=0;j<n;j++){
                h[i][j]=new Habi();//hay que meterle el numero de habitacion y el booleano que indica si se ha pasado por ahí
            }
        }
        for (int i=0;i<n;i++) {//Creamos las habitaciones del edificio
            if(i!=0){
                y=y+30;
            }
            for(int j=0;j<n;j++){//colocamos las casillas
                Edificio[i][j]=new javax.swing.JTextField();//hay que meterle el numero de habitacion y el booleano que indica si se ha pasado por ahí
                Edificio[i][j].setBounds(x,y,30,30);//coloco la habitacion
                Edificio[i][j].setHorizontalAlignment(JTextField.CENTER);
                jDesktopPane1.add(Edificio[i][j],javax.swing.JLayeredPane.DEFAULT_LAYER);
                //actualizamos las variables de posicionamiento
                x=x+30;
                if (j==n-1){//si llegamos al final de la fila, reiniciamos la x
                    x=20;
                }
            }
        }
        //Ahora ponemos los botones zoom + y zoom -
        jButton1.setText("Zoom +");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton1.setBounds(Edificio[n-1][n-1].getX()+50,Edificio[0][n-1].getY(), 80, 23);
        jDesktopPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText("Zoom -");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton2.setBounds(Edificio[n-1][n-1].getX()+50, Edificio[n-1][n-1].getY()+4, 80, 23);
        jDesktopPane1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
         
        //Titulo principal
        jLabel1.setText("Estado de las Habitaciones");
        jLabel1.setBounds(20, 20, 170, 14);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jLabel3.setText("Profundidad del cubo");
        jLabel3.setBounds(jLabel1.getX(), jLabel1.getY()+30, 120, 14);
        jDesktopPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jTextField17.setText(""+z);
        jTextField17.setBounds(jLabel3.getX()+125,jLabel3.getY()-2, 20, 20);
        jDesktopPane1.add(jTextField17, javax.swing.JLayeredPane.DEFAULT_LAYER);

       
        
        //Resto de componentes
        jLabel4.setText("Algoritmo Actual");
        jLabel4.setBounds(jButton1.getX()+200, 50, 100, 14);
        jDesktopPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jTextField18.setBounds(jButton1.getX()+200, 70, 160, 20);
        jDesktopPane1.add(jTextField18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        
        jCheckBox1.setText("Profundidad");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox1.setBounds(jTextField18.getX(),jTextField18.getY()+35, 90, 15);
        jDesktopPane1.add(jCheckBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox2.setText("Anchura");
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox2.setBounds(jTextField18.getX(), jCheckBox1.getY()+20, 80, 15);
        jDesktopPane1.add(jCheckBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox3.setText("aEstrella");
        jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox3.setBounds(jTextField18.getX(),jCheckBox2.getY()+20, 80, 15);
        jDesktopPane1.add(jCheckBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox4.setText("Uniforme");
        jCheckBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox4.setBounds(jTextField18.getX(), jCheckBox3.getY()+20, 80, 15);
        jDesktopPane1.add(jCheckBox4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox5.setText("Profundidad iterativa");
        jCheckBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox5.setBounds(jTextField18.getX(), jCheckBox4.getY()+20, 150, 15);
        jDesktopPane1.add(jCheckBox5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox6.setText("Escalada");
        jCheckBox6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox6.setBounds(jTextField18.getX(), jCheckBox5.getY()+20, 80, 15);
        jDesktopPane1.add(jCheckBox6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jButton3.setText("Abrir");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.setBounds(Edificio[n-1][n-1].getX(),jButton2.getY()+60, 90, 23);
        jDesktopPane1.add(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton4.setText("Ejecutar");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.setBounds(jButton3.getX()+100, jButton2.getY()+60, 90, 23);
        jDesktopPane1.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jLabel2.setText("Consola");
        jLabel2.setBounds(20,jButton3.getY()+40, 140, 20);
        jDesktopPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane1.setBounds(20,jLabel2.getY()+20, 570, 280);
        jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        pack();
        
        
    }
    
    
    /**
	 * asocia un controlador a la vista actual para que haya una comunicacion entre ellos
	 * @param controlador que se asocia a la vista
	 */
	public void asociarControlador(Controlador cont){
		controlador = cont;
	}
	
	
	/**
	 * genera una solicitud de estrategia la usuarioa para resolver cada una de las habitaciones
	 * @param b
	 * @return el nuemro de estrategia
	 */
	public int solicitud(){//te devuelve el numero de algoritmo que has seleccionado
		int juego=0;
		if(jCheckBox1.isSelected()){
			juego=1;
		}else if(jCheckBox2.isSelected()){
			juego=2;
		}else if(jCheckBox3.isSelected()){
			juego=3;
		}else if(jCheckBox4.isSelected()){
			juego=4;
		}else if(jCheckBox5.isSelected()){
			juego=5;
		}else{juego=6;}
		return juego;
	}
	
	/**
	 * muestra un mensjae por pantalla
	 * @param s mensaje que se muestra por pantalla
	 */
	public void mostrar(String s){		
		mensaje+= s;
		jTextArea1.setText(mensaje);
	}
	
	/**
	 * limpia la consola de la interfaz
	 *
	 */
	public void limpiar(){//limpiamos la consola
		jTextArea1.cut();
	}
	
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTextField1.setBounds(20, 50, 30, 30);
        jDesktopPane1.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField2.setBounds(50, 50, 30, 30);
        jDesktopPane1.add(jTextField2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField3.setBounds(80, 50, 30, 30);
        jDesktopPane1.add(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField4.setBounds(110, 50, 30, 30);
        jDesktopPane1.add(jTextField4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField5.setBackground(new java.awt.Color(204, 255, 204));
        jTextField5.setText("1");
        jTextField5.setBounds(20, 80, 30, 30);
        jDesktopPane1.add(jTextField5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField6.setBackground(new java.awt.Color(204, 255, 204));
        jTextField6.setBounds(50, 80, 30, 30);
        jDesktopPane1.add(jTextField6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField7.setBounds(80, 80, 30, 30);
        jDesktopPane1.add(jTextField7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField8.setBounds(110, 80, 30, 30);
        jDesktopPane1.add(jTextField8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField9.setBounds(20, 110, 30, 30);
        jDesktopPane1.add(jTextField9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField10.setBounds(50, 110, 30, 30);
        jDesktopPane1.add(jTextField10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField11.setBackground(new java.awt.Color(204, 255, 204));
        jTextField11.setBounds(80, 110, 30, 30);
        jDesktopPane1.add(jTextField11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField12.setBounds(110, 110, 30, 30);
        jDesktopPane1.add(jTextField12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField13.setBounds(20, 140, 30, 30);
        jDesktopPane1.add(jTextField13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField14.setBounds(50, 140, 30, 30);
        jDesktopPane1.add(jTextField14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField15.setBounds(80, 140, 30, 30);
        jDesktopPane1.add(jTextField15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField16.setBackground(new java.awt.Color(204, 255, 204));
        jTextField16.setForeground(new java.awt.Color(255, 0, 51));
        jTextField16.setText("9");
        jTextField16.setBounds(110, 140, 30, 30);
        jDesktopPane1.add(jTextField16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("Zoom +");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton1.setBounds(160, 50, 69, 23);
        jDesktopPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText("Zoom -");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton2.setBounds(160, 150, 65, 23);
        jDesktopPane1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane1.setBounds(20, 250, 570, 260);
        jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setText("Estado de las Habitaciones");
        jLabel1.setBounds(20, 20, 170, 14);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("Consola");
        jLabel2.setBounds(20, 230, 140, 20);
        jDesktopPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField17.setText("0");
        jTextField17.setBounds(270, 80, 20, 20);
        jDesktopPane1.add(jTextField17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Profundidad del cubo");
        jLabel3.setBounds(160, 90, 120, 14);
        jDesktopPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField18.setBounds(340, 40, 160, 20);
        jDesktopPane1.add(jTextField18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Algoritmo Actual");
        jLabel4.setBounds(340, 20, 80, 14);
        jDesktopPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox1.setText("Profundidad");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox1.setBounds(340, 80, 80, 15);
        jDesktopPane1.add(jCheckBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox2.setText("Anchura");
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox2.setBounds(340, 100, 80, 15);
        jDesktopPane1.add(jCheckBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox3.setText("aEstrella");
        jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox3.setBounds(340, 120, 80, 15);
        jDesktopPane1.add(jCheckBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox4.setText("Uniforme");
        jCheckBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox4.setBounds(340, 140, 80, 15);
        jDesktopPane1.add(jCheckBox4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox5.setText("Profundidad iterativa");
        jCheckBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox5.setBounds(340, 160, 130, 15);
        jDesktopPane1.add(jCheckBox5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox6.setText("Escalada");
        jCheckBox6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox6.setBounds(340, 180, 80, 15);
        jDesktopPane1.add(jCheckBox6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton3.setText("Abrir");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton3.setBounds(110, 210, 70, 23);
        jDesktopPane1.add(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton4.setText("Ejecutar");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton4.setBounds(230, 210, 73, 23);
        jDesktopPane1.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>                        

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {                                      
// TODO add your handling code here:
    	int juego=0;
        if(!comprueba()){//comprobamos si no hay error
            JOptionPane.showMessageDialog(this,"Seleccione solo una opción","Error",JOptionPane.ERROR_MESSAGE);
            jCheckBox1.setSelected(false);//deseleccionamos todas las opciones para que vuelva elegir
            jCheckBox2.setSelected(false);
            jCheckBox3.setSelected(false);
            jCheckBox4.setSelected(false);
            jCheckBox5.setSelected(false);  
            jCheckBox6.setSelected(false); 
        }else{//miramos que opcion se ha seleccionado
            juego=solicitud();
            if (juego==1){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en profundidad\n");
				jTextField18.setText("Busqyeda en profundidad");
				controlador.jugar(1);
				
			//aplicacion ejecutar con busqueda 2
			} else if(juego==2){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en anchura\n");
				jTextField18.setText("Busqueda en Anchura");
				controlador.jugar(2);
			
			//aplicacion ejecutar con busqueda 3
			} else if(juego==3){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en A*\n");
				jTextField18.setText("Busqueda en A*");
				controlador.jugar(3);
			
			//aplicacion ejecutar con busqueda 4
			} else if(juego==4){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda uniforme\n");
				jTextField18.setText("Busqueda Uniforme");
				controlador.jugar(4);
				
			//aplicacion ejecutar con busqueda 5
			} else if(juego==5){
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en profundidad iterativa\n");
				jTextField18.setText("Busqueda en profunidad iterativa");
				controlador.jugar(5);
				
			//aplicacion ejecutar con busqueda 6
			} else {
				mostrar("EMPIEZA EL JUEGO\n");
				mostrar("Busqueda en escalada\n");
				jTextField18.setText("Busqueda en escalada");
				controlador.jugar(6);
			} 
            controlador.jugar(juego);
        }
    }                                     

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {                                      
// TODO add your handling code here:
    	limpiar();
    	//Abrimos el navegador de archivos
    	JFileChooser selector = new JFileChooser();
		selector.setCurrentDirectory(new File("."));
		selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opcion = selector.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION){
        	File archivo = selector.getSelectedFile();
        	String ruta = archivo.getAbsolutePath();	        	
        	CargarEdificio c = new CargarEdificio();
        	try  {
        		EdificioCubico edi = new EdificioCubico(controlador);
        		c.cargarEdificio(edi, ruta);
        		controlador.cargar(edi);
        	} catch(Exception ex){
        		mostrar("Imposible abrir el archivo");
        	}
        }
    	
    }                                     

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {                                      
// TODO add your handling code here:
        z=z+1;
        if(z>n){
            z=n;
        }
        jTextField17.setText(""+z);
        rellena();//actualizamos las casillas
    }                                     

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {                                      
// TODO add your handling code here:
        z=z-1;
        if(z==0){
            z=1;
        }
        jTextField17.setText(""+z);
        rellena();//actualizamos las casillas
    }                                     
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visual().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration                   
    
}

package Ventana;

import java.util.ArrayList;
import javax.swing.JTextField;
import micromundo.EdificioCubico;
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
    EdificioCubico e; 
    int z;
    
    public Visual() {
        n=10;
        z=0;
        dibuja();
        rellena();
    }
    public void rellena(){//esta funcion colorea y rellena las habitaciones con sus respectivos numeros
        
    	//actualizamos las casillas segun la profundiadad
    	for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	h[i][j].setNum_hab(e.getEdificio()[i][j][z].getN());//cogemos el numero de la habitacion
            	//se supone que el color lo deben modificar los algoritmos, debemos hacer algo para que cada vez que pase 
            	//por una casilla cambie el boolean
            }
    	}
    	
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
        int x=20;//eje x
        int y=50;//eje y
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

        jButton1.setBounds(Edificio[n-1][n-1].getX()+50, 50, 80, 23);
        jDesktopPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText("Zoom -");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton2.setBounds(Edificio[n-1][n-1].getX()+50, 150, 80, 23);
        jDesktopPane1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jLabel3.setText("Profundidad del cubo");
        jLabel3.setBounds(Edificio[n-1][n-1].getX()+50, 90, 120, 14);
        jDesktopPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jTextField17.setText(""+z);
        jTextField17.setBounds(Edificio[n-1][n-1].getX()+50, 110, 20, 20);
        jDesktopPane1.add(jTextField17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        //Titulo principal
        jLabel1.setText("Estado de las Habitaciones");
        jLabel1.setBounds(20, 20, 170, 14);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        //Resto de componentes
        jLabel4.setText("Algoritmo Actual");
        jLabel4.setBounds(jButton1.getX()+160, 50, 100, 14);
        jDesktopPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jTextField18.setBounds(jButton1.getX()+160, 70, 160, 20);
        jDesktopPane1.add(jTextField18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jLabel2.setText("Consola");
        jLabel2.setBounds(jButton1.getX()+160, 140, 140, 14);
        jDesktopPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane1.setBounds(jButton1.getX()+160, 160, 350, 300);
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

        jScrollPane1.setBounds(340, 160, 350, 300);
        jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setText("Estado de las Habitaciones");
        jLabel1.setBounds(20, 20, 170, 14);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("Consola");
        jLabel2.setBounds(340, 140, 140, 14);
        jDesktopPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField17.setText("0");
        jTextField17.setBounds(160, 110, 20, 20);
        jDesktopPane1.add(jTextField17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Profundidad del cubo");
        jLabel3.setBounds(160, 90, 120, 14);
        jDesktopPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField18.setBounds(340, 70, 160, 20);
        jDesktopPane1.add(jTextField18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Algoritmo Actual");
        jLabel4.setBounds(340, 50, 80, 14);
        jDesktopPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
    }// </editor-fold>                        

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {                                      
// TODO add your handling code here:
        z=z-1;
        if(z<0){
            z=0;
        }
        jTextField17.setText(""+z);
        rellena();//actualizamos las casillas
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

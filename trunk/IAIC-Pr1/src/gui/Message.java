package gui;

import javax.swing.*;

public class Message extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5893871526795451153L;
	
	private JLabel etiqueta;
	
	/**
	 * genera un panel para mostrar mensajes
	 * @param texto es el mensaje que se mostrar en esta ventana
	 */
	public Message(String texto) {
		etiqueta = new JLabel(texto,JLabel.RIGHT);
		add(etiqueta);
	}

}

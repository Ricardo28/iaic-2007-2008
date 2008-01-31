package gui;

import java.awt.*;
import javax.swing.*;

public class Solicitud extends JPanel{
	
	private static final long serialVersionUID = -2650836046654314404L;
	
	protected JRadioButton profundidad;
	
	protected JRadioButton anchura;
	
	protected JRadioButton aEstrella;
	
	protected JRadioButton uniforme;
	
	protected JRadioButton profIterativa;
	
	protected JRadioButton escalada;
	
	/**
	 * crea un instancia del panel que ofrece una estrategia a elegir al jugador
	 */
	public Solicitud() {
		ButtonGroup grupo=new ButtonGroup();
		setLayout(new GridLayout(6,1));
		profundidad = new JRadioButton("Profundidad",true);
		profundidad.setMnemonic('P');
		profundidad.setSelected(false);
		grupo.add(profundidad);
		add(profundidad);
		anchura = new JRadioButton("Anchura",true);
		anchura.setMnemonic('A');
		anchura.setSelected(false);
		grupo.add(anchura);
		add(anchura);		
		aEstrella = new JRadioButton("aEstrella",true);
		profundidad.setMnemonic('E');
		aEstrella.setSelected(false);
		grupo.add(aEstrella);
		add(aEstrella);		
		uniforme = new JRadioButton("Uniforme",true);
		uniforme.setMnemonic('U');
		uniforme.setSelected(false);
		grupo.add(uniforme);
		add(uniforme);
		profIterativa = new JRadioButton("Profundida Iterativa",true);
		profIterativa.setMnemonic('I');
		profIterativa.setSelected(false);
		grupo.add(profIterativa);
		add(profIterativa);
		escalada = new JRadioButton("Escalada",true);
		escalada.setMnemonic('s');
		escalada.setSelected(false);
		grupo.add(escalada);
		add(escalada);
		
	}
	
	/**
	 * crea una instancia de solicitud de estrategia si se trata del juego jarras
	 * @param jarras
	 */
	public Solicitud(boolean jarras) {		
		ButtonGroup grupo=new ButtonGroup();
		setLayout(new GridLayout(6,1));
		profundidad = new JRadioButton("Profundidad",true);
		profundidad.setMnemonic('P');
		profundidad.setSelected(false);
		grupo.add(profundidad);
		add(profundidad);
		anchura = new JRadioButton("Anchura",true);
		anchura.setMnemonic('A');
		anchura.setSelected(false);
		grupo.add(anchura);
		add(anchura);		
		aEstrella = new JRadioButton("aEstrella",true);
		profundidad.setMnemonic('E');
		aEstrella.setSelected(false);
		grupo.add(aEstrella);
		add(aEstrella);			
		profIterativa = new JRadioButton("Profundida Iterativa",true);
		profIterativa.setMnemonic('I');
		profIterativa.setSelected(false);
		grupo.add(profIterativa);
		add(profIterativa);
	}

}

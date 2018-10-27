package grafica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ajedrez.Ajedrez;


@SuppressWarnings("serial")
public class vTableroJuego extends JFrame implements MouseListener{
	private TableroDeAjedrez tableroDeAjedrez;
	private Ajedrez administradorAjezderz;
	
	public vTableroJuego(Ajedrez administradorAjederez) {
		this.administradorAjezderz=administradorAjezderz;
		this.setEnabled(true);
		this.setFocusTraversalKeysEnabled(true);
		this.setTitle("JUEGO FINAL 2018 A, CARLOS IRIGOYEN");
		this.tableroDeAjedrez= new TableroDeAjedrez(administradorAjezderz);

		Container contenedor = this.getContentPane(); //PANEL		
		contenedor.add(tableroDeAjedrez,null);
		tableroDeAjedrez.inicializar();
	}
	
	
	
	
	
/**
 * TODO METODOS DE MOUSE LISTENER Implementar Luego 
 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
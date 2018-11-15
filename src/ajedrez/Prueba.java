package ajedrez;

import java.awt.EventQueue;

import javax.swing.JFrame;

import excepciones.FueraDeTableroException;
import grafica.TableroGRAFICO;
import grafica.VentanaPrincipal;
import pieza.Pieza;

public class Prueba {

	public static void main(String[] args)  {
		Ajedrez ajedrez = Ajedrez.getSingletoneInstancia();
		Ajedrez.getSingletoneInstancia().inicarJuego();
		TableroGRAFICO tableroGui =new TableroGRAFICO();
		VentanaPrincipal ventanaPrincipal= new VentanaPrincipal(tableroGui);
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.setVentanaPrincipal(ajedrez);
		for(Pieza p : ajedrez.getBlancas().getPiezas()) {
			tableroGui.setPieza(p);
		}
		for(Pieza p : ajedrez.getNegras().getPiezas()) {
			tableroGui.setPieza(p);
		}
	

	
	

		
	}

}

package ajedrez;

import java.awt.EventQueue;

import javax.swing.JFrame;

import excepciones.FueraDeTableroException;
import grafica.TableroGrafico;
import grafica.VentanaPrincipal;
import pieza.Pieza;
/**
 * Clase Prueba es la que se encarga inicar el juego 
 * @author carlos
 *
 */
public class Prueba {

	public static void main(String[] args)  {
		Ajedrez ajedrez = Ajedrez.getSingletoneInstancia();
		Ajedrez.getSingletoneInstancia().inicarJuego();
		TableroGrafico tableroGui =new TableroGrafico();
		VentanaPrincipal ventanaPrincipal= new VentanaPrincipal(tableroGui);
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.setVentanaPrincipal(ajedrez);
		ventanaPrincipal.setResizable(false);
		for(Pieza p : ajedrez.getBlancas().getPiezas()) {
			tableroGui.setPieza(p);
		}
		for(Pieza p : ajedrez.getNegras().getPiezas()) {
			tableroGui.setPieza(p);
		}
		
	}

}

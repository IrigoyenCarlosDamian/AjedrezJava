package ajedrez;

import java.awt.EventQueue;

import javax.swing.JFrame;

import excepciones.FueraDeTableroException;
import grafica.TableroGui;
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
		TableroGui tableroGui =new TableroGui();
		VentanaPrincipal ventanaPrincipal= new VentanaPrincipal(tableroGui);
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.setAjedrez(ajedrez);
		ventanaPrincipal.setResizable(false);
	}
}

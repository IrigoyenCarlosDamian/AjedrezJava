package ajedrez;

import java.awt.EventQueue;

import javax.swing.JFrame;

import excepciones.FueraDeTableroException;
import grafica.VentanaPrincipal;

public class Prueba {

	public static void main(String[] args)  {
		Ajedrez ajedrez = Ajedrez.getSingletoneInstancia();
		ajedrez.crearTableroGui();
		
	}

}

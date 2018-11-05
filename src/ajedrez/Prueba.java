package ajedrez;

import java.awt.EventQueue;

import javax.swing.JFrame;

import excepciones.FueraDeTableroException;
import grafica.VentanaPrincipal;

public class Prueba {

	public static void main(String[] args) throws FueraDeTableroException {
		Ajedrez ajedrez = Ajedrez.getSingletoneInstancia();
		ajedrez.inicarJuego();
		/*
		 * Comenzar puede largar Fuera de Tablero (getCelda de tablero) pero esta
		 * previamente atrapada
		 */

		

		ajedrez.comenzar();

	}

}

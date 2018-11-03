package ajedrez;

import excepciones.FueraDeTableroException;

public class Prueba {

	public static void main(String[] args) throws FueraDeTableroException {
		Ajedrez ajedrez = Ajedrez.getSingletoneInstancia();
		ajedrez.inicarJuego();
		/* Comenzar puede largar Fuera de Tablero 
		 * (getCelda de tablero)
		 * pero esta previamente atrapada
		 * */
		ajedrez.comenzar();
		
		
	}

}

package pieza;

import java.util.ArrayList;

import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Tablero;

public class Rey extends Pieza {

	
	public Rey(Celda celda,Equipo equipo) {
		super(celda,equipo);

	}

	@Override
	public void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void piezaComida(Pieza pieza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		return listaCelda;
	}
	
	public String toString() {
		return super.toString()+"R";
	}


	/*Geters y Seters*/
	

}

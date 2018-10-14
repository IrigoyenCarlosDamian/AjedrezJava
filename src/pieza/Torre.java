package pieza;

import java.util.ArrayList;

import ajedrez.Celda;
import ajedrez.Equipo;

public class Torre extends Pieza {
	public Torre() {
		super();
	}
	public Torre(Celda celda) {
		super(celda);
		
		
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
		// TODO Auto-generated method stub
		return null;
	}
	public String toString() {
		return"T";
	}
}
package pieza;

import java.util.ArrayList;

import ajedrez.*;


public class Peon extends Pieza {
	// private boolean esPrimerMovimiento; podriamos tener una varaible es primer movimneto y en base a esa preguntamos para hacer la comparacion 
	
	
	/*Constructores*/

	public Peon(Celda celda,Equipo equipo) {
		super(celda,equipo);
	}
	
	/*Metodos*/
	@Override
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Celda c = this.getCelda();// Celda origen donde esta la pieza actualmente
		Tablero tablero = super.getEquipo().getAjedrez().getTablero();// Tramos al tablero
		Celda mov = new Celda(0, 0);//Celda a la que se mueve el Peon
		int avance1, avance2;
		if(this.getEquipo().getNombre()=="Blanca") {
			avance2= -2;
			avance1 = -1;
		} else {
			avance2 = 2;
			avance1 = 1;
		}
		//Movimiento Normal de a una celda		
		if (validarMovimiento(c.getFila() + avance1, c.getColumna())) {//Validamos que este dentro del tablero
			mov = tablero.getCelda(c.getFila() + avance1, c.getColumna());// Celda a donde se avanza
			if (mov.puedeIngresar(this)) {//Validamos si puede ingresar la celda
				listaCelda.add(mov);
				if ((primerMovimientoPeon())) {//POreguntamos si es su primer movimiento
					if (validarMovimiento(c.getFila() + avance2, c.getColumna())) {//idem
						mov = tablero.getCelda(c.getFila() + avance2, c.getColumna());// Celda a donde se avanza
						if (mov.puedeIngresar(this)) {//idem
							listaCelda.add(mov);
						}
					}
				}
			}
		}
		
		return listaCelda;
	}
	
	public boolean primerMovimientoPeon () {
		/* Consulta si es el primer movimiento del peon 
		 * */
		if(this.getEquipo().getNombre() == Ajedrez.BLANCA) {
			if(this.getCelda().getFila() == 6) 
				return true;
		} else {
			if(this.getCelda().getFila() == 1) 
				return true;
		}
		return false;
	}
	
	@Override
	public void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
	//		this.getMovimientosPosibles();
		
	}
	@Override
	public void piezaComida(Pieza pieza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return super.toString()+"P";
	}
	


}

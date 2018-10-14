package pieza;

import java.util.ArrayList;

import ajedrez.*;


public class Peon extends Pieza {
	// private boolean esPrimerMovimiento; podriamos tener una varaible es primer movimneto y en base a esa preguntamos para hacer la comparacion 
	
	
	/*Constructores*/

	public Peon(Celda celda) {
		super(celda);
	}
	
	/*Metodos*/
	@Override
	public ArrayList<Celda> getMovimientosPosibles(){
		
		/*
		 * Se podria hacer un arreglo para guardar las celdas posibles, en este caso peon
		 * son solo 2 celdas, pero en las demas piezas se podria generar en vez de dos celdas
		 * un arreglo de celdas y luego insertarlas en la lista???
		 * */
		/*habtria que analizar el equipo para ver si el  movimento es con menos o  mas*/
		ArrayList<Celda> listaCeldas = new ArrayList<Celda>();
		Celda c1 = new Celda(this.getCelda().getFila()-1, this.getCelda().getColumna());
		Celda c2 = new Celda(this.getCelda().getFila()-2, this.getCelda().getColumna());
		
		//Este es movimiento unicamente si es su primer movimiento
		if(this.getCelda().getFila()==6) {//Si es el primer movimiento
			listaCeldas.add(c2);
		}
		
		listaCeldas.add(c1);//Este es el movimiento normal
		return listaCeldas;
	
		
	}
	@Override
	public void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
			this.getMovimientosPosibles();
		
	}
	@Override
	public void piezaComida(Pieza pieza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "P";
	}
	


}

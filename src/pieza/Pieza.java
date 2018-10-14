package pieza;


import java.util.ArrayList;

import ajedrez.Celda;
import ajedrez.Equipo;


 public abstract class Pieza implements IPieza {
	private Celda celda;
	private Equipo equipo;
	private boolean estaViva;
	private ArrayList<IPiezaListener> piezaListener;
	
	/*Constructores*/
	public Pieza() {
		
	}
	public Pieza(Celda celda) {
		this.celda=celda;
		this.estaViva= true;
	}
	
	/*Metodos*/
	public abstract ArrayList<Celda> getMovimientosPosibles();		
	
	public void actualizarPosicion(int fila,int columna) {
	//	this.cleda.setFila(fila);	//aqui tengo que enviar el numero de fila de la celda
	//	this.cleda.setColumna(columna); //aqui tengo que enviar el numerode
	}
	/*Geters y Seters*/
	public Celda getCelda() {
		return celda;
	}

	public void setCleda(Celda celda) {
		this.celda = celda;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEstaViva(boolean estaViva) {
		this.estaViva = estaViva;
	}
	
	public void addPiezaListener(IPiezaListener listener) {
		this.piezaListener.add(listener);
		
	}
}

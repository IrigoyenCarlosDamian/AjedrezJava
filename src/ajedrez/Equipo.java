package ajedrez;

import java.util.ArrayList;
import java.util.Random;

import pieza.Pieza;
import pieza.Rey;

public class Equipo {
	private String nombre;
	private Ajedrez ajedrez;
	private boolean estaEnJaque;
	private  ArrayList<Pieza> piezas;
	
	/*Construcotres*/
	public Equipo(String nombre) {
		
		this.nombre=nombre;
		this.piezas=new ArrayList<Pieza>();
		
	}
	
	/*Metodos*/
	/*en esta primera instancia devolvemos un mnovimiento cualquiera de todos los disponibles  para una pieza dada*/
	//TODO[a  futuro ver de mejorar esta implementacion   ]
	 public Jugada jugar() {
		ArrayList<Jugada> jugadas = this.calcularJugadsPosibles();
		Random random = new Random(); // Instanciamos la clase Random
		int movimientoRandom = random.nextInt(jugadas.size()); // elegimos un movimiento al azar entre 0 y  la cantidad de movimientos posibles
		return jugadas.get(movimientoRandom); // devuelvo la jugada;
	}
	 	
	
	public Pieza getRey() {
		int j=-1;
		for(int i=0;i<this.piezas.size(); i++) {
			if (piezas.get(i) instanceof Rey) {
				j=j++;
				i=this.piezas.size();
			}
		}
		return piezas.get(j);
	}
	/*Retorna las jugadas posibles para una pieza dada (del arraylist)*/
	public ArrayList<Jugada> calcularJugadsPosibles() {
		ArrayList<Jugada>jugadasPosibles= new ArrayList<Jugada>();// arraylist con las jugadas para una pieza en particular 
		for (Pieza pieza : piezas) { // el array list de las piezas de un equipo
			for(Celda celda:pieza.getMovimientosPosibles()) {
				jugadasPosibles.add(new Jugada(pieza.getCelda().getPieza(),pieza.getCelda().getFila(),pieza.getCelda().getColumna())); 
				//agrego las jugadas al arraylist
			return jugadasPosibles;
			}		
		}
		return jugadasPosibles;	
	}
	/*Geters y Seters*/

	public Ajedrez getAjedrez() {
		return ajedrez;
	}
	
	public String getNombre() {
		return this.nombre; 
	}

	public ArrayList<Pieza> getPiezas() {
		return piezas;
	}
	
	
}
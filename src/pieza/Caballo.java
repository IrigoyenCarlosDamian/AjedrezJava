package pieza;

import java.util.ArrayList;

import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Tablero;

public class Caballo extends Pieza{
	
	/*Constructores*/
	
	public Caballo(Celda celda) {
		super(celda);
		
	}
	/*Metodos*/
	public ArrayList<Celda> getMovimientosPosibles() {
		ArrayList<Celda> listaCelda = new ArrayList<Celda>();
		Celda c = this.getCelda();//Celda origen donde esta la pieza actualmente
		Tablero tablero = this.getEquipo().getAjedrez().getTablero();// Tramos al tablero
		Celda mov = new Celda(c.getFila(), c.getColumna()); //Celda destinada a los posibles movimientos del caballo
		int origen = c.getColumna() + c.getFila(); //Sumatoria de las coordenadas origen de la celda
		boolean filaColumnaDistinta;
		//Se recorre la porcion de tablero donde se puede desplazar el caballo
		for (int i = (c.getFila() - 3); i < 6; i++) {
			for (int j = (c.getColumna() - 3); j < 6; j++) {
				mov = tablero.getCelda( i, j);//Celda a donde se mueve el caballo
				filaColumnaDistinta = (c.getColumna() != mov.getColumna() && (c.getFila() != mov.getFila()) ); /* Para que sea un movimiento posible del caballo
																												* tanto la fila como la columna deben ser
				 																								* DISINTAS
				 																								*/ 
				if (((i+j) == (origen + 1) ) || ((i+j) == (origen - 1) && filaColumnaDistinta)) { //Si es un movimiento aceptable para el caballo
					if(super.validarMovimiento(mov.getFila(), mov.getColumna()) && mov.puedeIngresar(this))
					listaCelda.add(mov); //Si esta dentro del tablero y se pude ingresar se enlista
				}
				if (((i+j) == (origen + 3) ) || ((i+j) == (origen - 3))) {//Si es un movimiento aceptable para el caballo
					if(super.validarMovimiento(mov.getFila(), mov.getColumna()) && mov.puedeIngresar(this))
					listaCelda.add(mov); //Si esta dentro del tablero y se pude ingresar se enlista
				}
			}
		}
		return listaCelda;
	}

	@Override
	public void piezaMovida(Pieza pieza, Celda celdaOrigen, Celda celdaDestino) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void piezaComida(Pieza pieza) {
		// TODO Auto-generated method stub
		
	}


	public String toString() {
		return("C");
	}

	
}

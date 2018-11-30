package equipos;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import ajedrez.Ajedrez;
import ajedrez.Celda;
import ajedrez.Equipo;
import ajedrez.Jugada;
import ajedrez.Tablero;
import excepciones.FueraDeTableroException;
import grafica.TableroGui;
import interfaces.IJugador;
import pieza.Pieza;

/**
 * Clase Equipo Humano viene definida por un nombre implemneta action performand
 * para el movimiento de las piezas
 * 
 * @author Carlos
 *
 */
public class EquipoHumano extends Equipo implements IJugador {

	private boolean segundoClick;
	private ArrayList<Celda> listaMov;
	private Pieza pieza;
	private Jugada jugada;
	private boolean esFinDeTurno;

	public EquipoHumano(String nombre) {
		super(nombre);
		this.segundoClick = false;
		this.listaMov = null;
		this.esFinDeTurno = false;
	}

	/**
	 * dada una fila y columna enviada por parametro setea la pieza en la celda
	 * correspondiente dentro del tablero grafico
	 */
	public void botonApretado(int fila, int columna) {
		Tablero tablero = Ajedrez.getSingletoneInstancia().getTablero();
		Equipo jugadorEnTurno = Ajedrez.getSingletoneInstancia().getEquipoEnTurno();
		if (jugadorEnTurno.getNombre() == this.getNombre()) {
			if (isSegundoClick()) {
				for (Celda celda : listaMov) {
					if ((celda.getFila() == fila) && (celda.getColumna() == columna)) {
						setJugada(new Jugada(getPieza(), celda.getFila(), celda.getColumna()));
						// tablero.mover(getPieza(), fila, columna);
						setSegundoClick(false);
						setEsFinDeTurno(true);
						return;
					}
				}
			} else {
				boolean hayPieza = false;
				boolean equipoEnTurno = false;
				try {
					hayPieza = tablero.getCelda(fila, columna).getPieza() != null;
					setPieza(tablero.getCelda(fila, columna).getPieza());
					equipoEnTurno = getPieza().getEquipo().getNombre() == jugadorEnTurno.getNombre();
				} catch (FueraDeTableroException e) {
				}
				
				if (hayPieza && equipoEnTurno) {
					setListaMov(pieza.getMovimientosPosibles());
					if (getListaMov().size() < 1) {
						JOptionPane.showMessageDialog(null, "Pieza no tiene movimientos");
					} else {
						setSegundoClick(true);
					}
				}
			}
		}
	}

	/* Getters and Setters */
	public boolean isSegundoClick() {
		return segundoClick;
	}

	public void setSegundoClick(boolean segundoClick) {
		this.segundoClick = segundoClick;
	}

	public String getNombre() {
		return super.getNombre();
	}

	public ArrayList<Celda> getListaMov() {
		return listaMov;
	}

	public void setListaMov(ArrayList<Celda> listaMov) {
		this.listaMov = listaMov;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public void setTablero(TableroGui tableroSignificativo) {
		tableroSignificativo.addIJugadorListener(this);
	}

	public Jugada getJugada() {
		return jugada;
	}

	public void setJugada(Jugada jugada) {
		this.jugada = jugada;
	}

	public boolean isEsFinDeTurno() {
		return esFinDeTurno;
	}

	public void setEsFinDeTurno(boolean esFinDeTurno) {
		this.esFinDeTurno = esFinDeTurno;
	}

	@Override
	/**
	 * implementeta una espera de medio segundo para la accion de boton
	 */
	public Jugada jugar() {
		while (!isEsFinDeTurno()) {
			// Se espera el movimiento
			try {
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
			}
		}
		setEsFinDeTurno(false);
		return getJugada();
	}

}

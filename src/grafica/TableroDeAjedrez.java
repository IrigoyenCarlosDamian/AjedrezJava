package grafica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import ajedrez.Ajedrez;
import excepciones.FueraDeTableroException;
import excepciones.NoHayPiezaException;
import pieza.Pieza;

public class TableroDeAjedrez extends Canvas implements MouseListener {

	private Ajedrez administradorAjederz;
	private HashMap<String, BufferedImage> listaImagenes;
	private int anchoPieza;
	private int altoPieza;
	private static int fila;
	private static int columna;

	/**
	 * Constructor de la clase tablero de Juego, donde se dibujan los elementos
	 * 
	 * @param administradorJuego
	 */
	public TableroDeAjedrez(Ajedrez ajedrez) {

		this.administradorAjederz = ajedrez;
		this.altoPieza = 10;
		this.anchoPieza = 10;
		TableroDeAjedrez.fila = 8;
		TableroDeAjedrez.columna = 8;

	}

	/**
	 * Crea un buffer para eliminar el parpadeo de la pantalla
	 */
	public void inicializar() {
		// Crea dos buffers para dibujar.
		// this.createBufferStrategy(2);
		this.addMouseListener(this);
	}

	/**
	 * Dibuja los {@link Elemento elementos} en el escenario del juego
	 */
	private void dibujar() {

		for (int i = 0; i < TableroDeAjedrez.fila; i++) {
			for (int j = 0; j < TableroDeAjedrez.columna; j++) {
				try {
					administradorAjederz.getTablero().getCelda(i, j).getPieza();// Lanza Fuera de Tablero
					Pieza p = administradorAjederz.getTablero().getCelda(i, j).getPieza(); // creo un elemento pieza,
																							// lanza FueraDeTablero
					int fila = p.getCelda().getFila(); // obtengo su fila
					int columna = p.getCelda().getColumna(); // obtengo su columna
					BufferedImage eImagen = getImagen(p.getClass().getName()); // busca el nombre de la clase con el
																				// nombre del archivo
					dibujarPieza(fila, columna, p);
				} catch (FueraDeTableroException e) {
					// Si la pieza no existe...
				}
			}
		}

	}

//		for (int i=0; i<dministradorAjederz.; i++){
//			//pinto cada elemento
//			Elemento e = administradorJuego.getListaElemento().get(i);//creo un elemento manipulable	
//			int x = uMovimiento.obtenerPosicionX(e);
//			int y = uMovimiento.obtenerPosicionY(e);

	/**
	 * TODO VER DE IMPLMENTAR MAS ADELANTE
	 */

	/* Metodos De Mouse Listener */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void dibujarPieza(int x, int y, Pieza p) {
		if (p instanceof Pieza) {
			Pieza pieza = (Pieza) p;
			((Graphics) this.getGrafico2D()).setColor(Color.BLACK);
			String nombre = p.toString();
			((Graphics2D) this.getGrafico2D()).drawString(nombre, x, y);
		}

	}

	private Object getGrafico2D() {
		if (this.getBufferStrategy() == null)
			return (Graphics2D) this.getGraphics();
		else
			return (Graphics2D) this.getBufferStrategy().getDrawGraphics();

	}

	/**
	 * Carga las imagenes desde el fichero de sistema
	 * 
	 * @param fileName
	 * @return new FileName or "no se encontro la imagen"
	 */
	private BufferedImage cargarImagen(String fileName) {
		try {
			return ImageIO.read(new File(fileName));
		} catch (Exception e) {
			System.out.println("No se encontro la imagen " + fileName);

			return null;
		}

	}

	/**
	 * Obtien el patch, o la ruta de la imagen dentro del proyecto
	 * 
	 * @param key
	 * @return
	 */

	private BufferedImage getImagen(String key) {
		if (listaImagenes == null)
			listaImagenes = new HashMap<String, BufferedImage>();
		BufferedImage img = listaImagenes.get(key);
		if (img == null) {
			img = cargarImagen(System.getProperty("user.dir") + File.separator + "img" + File.separator + key + ".png");

		}
		return img;
	}

}

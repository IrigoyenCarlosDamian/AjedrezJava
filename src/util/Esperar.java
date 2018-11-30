package util;
/**
 *  Clase que se ecaraga de hacer un delay de 2 segundos para los movimientos de las piezas 
 * @author Carlos
 */

public class Esperar{
	/**
	 * metodo que implementa un deley de dos segundos[ es a fines de poder apreciar de mejor los movimientos de
	 * las piezas en el tablero]
	 */
	  public static void esprerar(){
	     System.out.println("\n");
	     try{
	        Thread.sleep(2000);
	     }catch(InterruptedException e){}
	     System.out.println("\n");
	   }
	}

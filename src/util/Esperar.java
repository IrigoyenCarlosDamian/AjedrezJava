package util;
/**
 *  Clase que se ecaraga de hacer un delay de 2 segundos para los movimientos de las piezas 
 * @author Carlos
 */

public class Esperar{
	  public static void esprerar(){
	     System.out.println("\n");
	     try{
	        Thread.sleep(2000);
	     }catch(InterruptedException e){}
	     System.out.println("\n");
	   }
	}

package util;
public class Esperar{
	  public static void esprerar(){
	     System.out.println("\n");
	     try{
	        Thread.sleep(2000);
	     }catch(InterruptedException e){}
	     System.out.println("\n");
	   }
	}

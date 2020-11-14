package mx.edu.cbtis051.hraa.metodos;

public class Main {

	public static void main(String[] args) {
		// Creamos un objeto de la clase Perro
		Perro boby = new Perro();
		
		// Invocación de métodos
		boby.setNombre("Firulais");
		boby.ladrar();
		boby.caminar();
		boby.comer("croquetas");
		System.out.println("Perro: "+boby.getNombre());
		
		/*
		 * TODO: Modificar los métodos de la clase Perro
		 * para que incluya el nombre del perro en las 
		 * acciones de los métodos ladrar(), caminar() y comer().
		 * Boby está ladrando...
		 * Boby está caminando...
		 * Boby come ...
		 */

	}

}

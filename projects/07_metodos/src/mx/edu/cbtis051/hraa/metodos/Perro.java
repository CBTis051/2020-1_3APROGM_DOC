package mx.edu.cbtis051.hraa.metodos;

public class Perro {

	// Variables de clase
	private String nombre;
	
	public void ladrar() {
		System.out.println(nombre + " está ladrando... GUAU");
	}
	
	public void caminar() {
		System.out.println(nombre + " está caminando...");
	}
	
	public void comer(String comida) {
		System.out.println(nombre + " está comiendo " + comida);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

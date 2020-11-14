package mx.edu.cbtis051.hraa.estudiante;

public class Estudiante {

	// Variables de clase
	private int id;
	private String nombre;
	private String nss;
	private double promedio;
	public final String CCT = "25DCT0211U";
	
	// Constructor de la clase
	public Estudiante() {
		id = 0;
		nombre = null;
		nss = null;
		promedio = 0.0;
	}
	
	// Constructor con parámetros
	public Estudiante(int id, String nombre, String nss, double promedio) {
		this.id = id;
		this.nombre = nombre;
		this.nss = nss;
		this.promedio = promedio;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

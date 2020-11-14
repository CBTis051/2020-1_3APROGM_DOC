package mx.edu.cbtis051.hraa.figuras;

public class Cuadrado extends Figura {
	
	// Variable de clase
	private double lado;
	private String color;
	
	// Constructor predeterminado
	public Cuadrado() {
		lado = 0.0;
		color = null;
	}
	
	// Constructor con parámetros
	public Cuadrado(double lado, String color) {
		this.lado = lado;
		this.color = color;
	}

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public double calcularArea() {
		// Regresamos el valor del area del cuadrado
		return lado * lado;
	}
	
	@Override
	public double calcularPerimetro() {
		// Regresamos el valor del perímetro del cuadrado
		return lado * 4;
	}
	
	@Override
	public String toString() {
		// Regresamos la representación en cadena del cuadrado
		return "Cuadrado \n" +
			" > lado: " + lado + "\n" +
			" > color: " + color + "\n" +
			" > area: " + calcularArea() + "\n" +
			" > perimetro: " + calcularPerimetro();
	}
}

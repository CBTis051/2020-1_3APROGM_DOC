package mx.edu.cbtis051.hraa.figuras;

public class Circulo extends Figura {

	// Variables de clase
	private double radio;
	private String color;
	public final double PI = 3.14159;
	
	// Constructor predeterminado
	public Circulo() {
		radio = 0.0;
		color = null;
	}
	
	// Constructor con parámetros
	public Circulo(double radio, String color) {
		this.radio = radio;
		this.color = color;
	}
	
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}
	public void setRadio(Circulo c) {
		this.radio = c.radio;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setColor(Circulo c) {
		this.color = c.color;
	}
	
	@Override
	public double calcularArea() {
		// Regresamos el valor del area del círculo
		return PI * radio * radio;
	}
	
	/*
	 * TODO: Declarar un método en la clase Figura llamado
	 * calcularPerimetro() con tipo de retorno double. En dicha
	 * clase, el método debe regresar el valor 0.0.
	 * -----------------------------------------------
	 * Sobreescribir el método calcularPerimetro() de la clase Figura
	 * en la clase Circulo e implementar el código necesario
	 * para realizar el cálculo correspondiente con la fórmula
	 * perímetro = PI * diámetro | diámetro = 2 * radio
	 * -----------------------------------------------
	 * Invocar desde un syso en la clase Main el método
	 * calcularPerimetro() en ambos objetos c1 y c2.
	 */
	
	@Override
	public double calcularPerimetro() {
		// Regresamos el valor del perímetro del círculo
		return PI * 2 * radio;
	}
	
	@Override
	public String toString() {
		// Regresamos la representación en cadena del objeto
		return "Circulo \n" +
				" > radio: " + radio + "\n" +
				" > color: " + color + "\n" +
				" > area: " + calcularArea() + "\n" +
				" > perimetro: " + calcularPerimetro();
	}
	
}













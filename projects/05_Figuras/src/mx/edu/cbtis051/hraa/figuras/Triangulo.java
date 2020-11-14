package mx.edu.cbtis051.hraa.figuras;

public class Triangulo extends Figura {
	
	// Variables de clase
	private double base;
	private double altura;
	private double lado1;
	private double lado2;

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLado1() {
		return lado1;
	}

	public void setLado1(double lado1) {
		this.lado1 = lado1;
	}

	public double getLado2() {
		return lado2;
	}

	public void setLado2(double lado2) {
		this.lado2 = lado2;
	}

	@Override
	public double calcularArea() {
		// Regresamos el valor del área
		return base * altura / 2;
	}

	@Override
	public double calcularPerimetro() {
		// Regresamos el valor del perímetro
		return base + lado1 + lado2;
	}
	
	/*
	 * TODO: 
	 * 1 - Implementar en la clase Triangulo:
	 *   > Constructor con parámetros que reciba un valor
	 *     para la base y otro para la altura. Dicho 
	 *     constructor debe tomar los valores recibidos
	 *     como parámetro y asignarlos a las variables
	 *     de clase correspondientes.
	 *   > Implementar una sobreescritura del método toString
	 *     que regrese una cadena con los valores del triángulo:
	 *     base, altura, lado1, lado2, area y perimetro.
	 * 2 - En la clase Main:
	 *     > Crear un objeto de la clase Triangulo llamado t1
	 *       utilizando el constructor con parámetros 
	 *       enviando un valor para la base y otro
	 *       para la altura.
	 *     > Asignar un valor al lado1 y lado2 utilizando
	 *       el método setLado1 y setLado2 según corresponda.
	 *     > Invocar desde un syso el método toString con el objeto
	 *       recién creado (t1).
	 */

}











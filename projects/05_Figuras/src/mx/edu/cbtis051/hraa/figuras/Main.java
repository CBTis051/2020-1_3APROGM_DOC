package mx.edu.cbtis051.hraa.figuras;

public class Main {

	public static void main(String[] args) {
		// Pruebas de la clase Circulo
		Circulo c1 = new Circulo();
		c1.setRadio(51.51);
		c1.setColor("Azul");
		System.out.println("Circulo c1.radio: " + c1.getRadio());
		System.out.println("Circulo c1.color: " + c1.getColor());
		
		Circulo c2 = new Circulo(51.051, "Rojo");
		System.out.println("Circulo c2.radio: " + c2.getRadio());
		System.out.println("Circulo c2.color: " + c2.getColor());

		System.out.println("Circulo c1.area: " + c1.calcularArea());
		System.out.println("Circulo c2.area: " + c2.calcularArea());
		
		System.out.println("Circulo c1.perimetro: " + c1.calcularPerimetro());
		System.out.println("Circulo c2.perimetro: " + c2.calcularPerimetro());
		
		System.out.println(c1.toString());
		
		// Cuadrado
		Cuadrado cuad1 = new Cuadrado(2020, "Verde");
		
		System.out.println(cuad1.toString());
		
		// Polimorfismo
		System.out.println(c1.toString());
		c1.setColor("Verde");
		System.out.println(c1.toString());
		c1.setColor(c2);
		c1.setRadio(c2);
		System.out.println(c1.toString());
		
	}

}

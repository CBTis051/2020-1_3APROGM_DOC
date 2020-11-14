package mx.edu.cbtis051.hraa.figuras;

public abstract class Figura {
	
	public abstract double calcularArea();
	
	public abstract double calcularPerimetro();
	
}

/*
 * TODO: Crear una clase llamada Cuadrado considerando:
 * 1 - debe ser una subclase de Figura
 * 2 - agregar una variable de clase llamada lado
 *     de tipo double.
 * 3 - agregar una variable de clase llamada color
 *     de tipo String.
 * 4 - implementar un constructor predeterminado (sin parámetros)
 *     que inicialice los valores de las variables de clase 
 *     lado y color con 0.0 y null, según sea el caso.
 * 5 - implementar un constructor con parámetros que reciba un 
 *     valor para el lado y otro para el color, el constructor
 *     debe asignar los valores recibidos como parámetro a las 
 *     variables de clase correspondientes.
 * 6 - agregar los métodos getter y setter a la clase correspondientes
 *     a las variables lado y color.
 * 7 - sobreescribir (Override) los métodos:
 *       a) calcularArea() --> usar fórmula area = lado * lado
 *       b) calcularPerimetro() --> usar fórmula perimetro = 4 * lado
 *       c) toString() --> regresar una cadena que contenga
 *                         lado, color, area y perímetro
 * 8 - en la clase Main, crear un objeto de la clase Cuadrado de nombre
 *     cuad1 utilizando el constructor con parámetros asignando los 
 *     valores que sean de su preferencia.
 * 9 - invocar desde un syso en la clase Main el método toString() del
 *     objeto recién creado (cuad1).
 */









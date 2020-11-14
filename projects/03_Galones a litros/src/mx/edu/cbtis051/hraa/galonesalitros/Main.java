package mx.edu.cbtis051.hraa.galonesalitros;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/*
		 * Solicitar al usuario una cantidad de galones
		 * Realizar el cálculo utilizando como referencia
		 * 1 galón = 3.785 litros
		 * Mostrar el resultado con el siguiente formato:
		 * X galones --> Y litros
		 */
		
		// Declaración de variables
		Scanner sc = new Scanner(System.in);
		int galones = 0;
		double litros = 0;
		
		// Solicitar cantidad de galones
		System.out.print("Cantidad de galones: ");
		galones = sc.nextInt();
		
		// Calculamos cantidad de litros
		litros = galones * 3.785;
		
		// Mostramos resultado
		System.out.println(galones + " galones --> " + litros + " litros");
		
		// Cerramos scanner
		sc.close();

	}

}

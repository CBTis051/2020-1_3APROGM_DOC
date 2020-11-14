package mx.edu.cbtis051.studytool;

import java.util.Scanner;

public class StudyPage {

	public static void main(String[] args) {
		
		/*
		 * Solicitar al usuario un término de estudio
		 * y su correspondiente definición.
		 * Posteriormente desplegar la información 
		 * introducida por el usuario en pantalla.
		 */
		Scanner sc = new Scanner(System.in);
		
		String term;
		System.out.print("Introduzca un término de estudio: ");
		term = sc.nextLine();
		
		String termdef;
		System.out.println("Introduzca una definición: ");
		termdef = sc.nextLine();
		
		System.out.println(term + ": " + termdef);
		
		sc.close();
		
	}

}

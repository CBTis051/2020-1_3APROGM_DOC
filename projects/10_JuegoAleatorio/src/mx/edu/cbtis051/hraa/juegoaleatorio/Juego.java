package mx.edu.cbtis051.hraa.juegoaleatorio;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Juego {

	// Atributos
	private int numero;
	private String jugador;
	private int puntaje = 100;
	
	/**
	 * Inicializamos el juego
	 * @param jugador
	 */
	public Juego(String jugador) {
		this.jugador = jugador;
		
		// Se genera número aleatorio entre 0 y 9
		this.numero = (int)(Math.random() * 10);
		
		// TRAMPAAAA
		System.out.println(this.numero);
	}
	
	/**
	 * Lógica del juego
	 * @param padre
	 */
	public void jugar(JFrame padre) {
		
		int numeroUsuario = 0;
		
		// Se inicia el ciclo con los intentos
		for (int i = 0; i < 3; i++) {
			
			// Se solicita el número al usuario
			try {
				
				numeroUsuario = Integer.parseInt(
						JOptionPane.showInputDialog(
								padre,
								"Introduce un número",
								"Intento " + (i + 1),
								JOptionPane.QUESTION_MESSAGE
							)
					);
				
			} catch (NumberFormatException e) {
				// Manejamos el error de conversión
				JOptionPane.showMessageDialog(
						padre,
						"El texto introducido no es un número válido.",
						"Error",
						JOptionPane.WARNING_MESSAGE
					);
				
				// Se disminuye el puntaje
				puntaje = puntaje - 30;
				
				// Se valida si se terminaron los intentos
				if (i == 2) {
					// Se acabaron los intentos por lo que se finaliza el juego
					finalizar(padre);
					// Salimos del método jugar
					return;
				} else {
					// Vamos al siguiente turno o intento con la siguiente iteración del for
					continue;
				}
			}
			
			// Se valida si el usuario adivinó el número
			if (numeroUsuario == this.numero) {
				// El usuario adivinó el número
				JOptionPane.showMessageDialog(
						padre,
						"Felicidades " + this.jugador + " adivinaste el número aleatorio.\n" +
						"Obtuviste " + this.puntaje + " puntos.",
						"Juego",
						JOptionPane.INFORMATION_MESSAGE
					);
				
				// Finalizamos el juego saliendo del método jugar
				return;
			} else {
				// El usuario no adivinó el número aleatorio
				if (i == 2) {
					// Se acabaron los intentos
					finalizar(padre);
				} else {
					// No se han acabado los intentos
					JOptionPane.showMessageDialog(
							padre,
							"Lo siento.\n" +
							numeroUsuario + " no es el número aleatorio.\n" +
							"Te quedan " + (2 - i) + " intentos.",
							"Juego",
							JOptionPane.INFORMATION_MESSAGE
						);
				}
				
				// Se disminuye el puntaje
				puntaje = puntaje - 30;
			}
			
		}
		
	}
	
	/**
	 * Se muestra al usuario el número aleatorio que no pudo adivinar
	 * @param padre
	 */
	public void finalizar(JFrame padre) {
		JOptionPane.showMessageDialog(
				padre,
				"Lo siento.\n" +
				"El número aleatorio era " + this.numero,
				"Juego",
				JOptionPane.INFORMATION_MESSAGE
			);
	}
}



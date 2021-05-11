package mx.edu.cbtis051.hraa.css;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Juego {
	
	// Atributos
	int numero;
	private String jugador;
	private int puntaje = 100;
	
	/**
	 * Inicializa el juego
	 * @param jugador
	 */
	public Juego(String jugador) {
		this.jugador = jugador;
		// Se genera número aleatorio entre 0 y 9
		this.numero = (int)(Math.random() * 10);
	}
	
	public void jugar(JFrame parent) {
		
		int numeroUsuario = 0;
		
		// Se inicia el ciclo con los intentos
		for (int i = 0; i < 3; i++) {
			
			// Se solicita el número al usuario
			try {
				
				numeroUsuario = Integer.parseInt(
						JOptionPane.showInputDialog(
							parent, 
							"Introduce un número", 
							"Intento "+(i+1), 
							JOptionPane.QUESTION_MESSAGE
						)
					);
				
			} catch (NumberFormatException e) {
				
				// Se maneja el error
				JOptionPane.showMessageDialog(
						parent,
						"El texto introducido no es un número válido.",
						"Error",
						JOptionPane.WARNING_MESSAGE
					);
				
				// Se disminuye el puntaje
				puntaje = puntaje - 30;
				
				// Se valida si se terminaron los intentos
				if (i == 2) {
					// Se acabaron los intentos por lo que se finaliza el juego
					finalizar(parent);
					// Salimos del método jugar
					return;
				} else {
					// Vamos al siguiente turno con la siguiente iteración del for
					continue;
				}
			}
			
			// Se valida el número
			if (numeroUsuario == numero) {
				
				JOptionPane.showMessageDialog(
						parent,
						"Felicidades " + jugador + " adivinaste el número aleatorio.\n" +
						"Obtuviste " + puntaje + " puntos.",
						"Juego",
						JOptionPane.INFORMATION_MESSAGE
					);
				
				// Finalizamos el juego saliendo del método jugar
				return;
				
			} else {
				if (i == 2) {
					// Se acabaron los intentos.
					finalizar(parent);
				} else {
					JOptionPane.showMessageDialog(
							parent, 
							"Lo siento.\n" +
									numeroUsuario + " no es el número aleatorio.\n" + 
									"Te quedan " + (2-i) + " intentos.",
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
	 */
	public void finalizar(JFrame parent) {
		JOptionPane.showMessageDialog(
				parent, 
				"Lo siento.\n" +
						"El número aleatorio era " + numero,
						"Juego",
						JOptionPane.INFORMATION_MESSAGE
			);
	}

}

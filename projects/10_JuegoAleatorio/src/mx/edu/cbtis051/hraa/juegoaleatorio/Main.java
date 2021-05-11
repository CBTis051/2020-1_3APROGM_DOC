package mx.edu.cbtis051.hraa.juegoaleatorio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("JUEGO DE NÚMEROS ALEATORIOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 438, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>\n<strong>INSTRUCCIONES:</strong>\n<ul>\n\t<li>El objetivo del juego es  adivinar un número aleatorio entre 0 y 9.</li>\n\t<li>Sólo se permiten números enteros.</li>\n\t<li>Se tienen 3 intentos para adivinar el número.</li>\n</ul>\n</html>");
		lblNewLabel_1.setBounds(6, 46, 438, 137);
		contentPane.add(lblNewLabel_1);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Se solicita el nombre del jugador para iniciar el juego
				String jugador = JOptionPane.showInputDialog(
						Main.this,
						"Introduce tu nombre",
						"Juego",
						JOptionPane.QUESTION_MESSAGE
					);
				
				// Se crea el objeto del juego
				Juego juego = new Juego(jugador);
				
				// Se inicia el juego
				juego.jugar(Main.this);
				
			}
		});
		btnJugar.setBounds(165, 195, 117, 49);
		contentPane.add(btnJugar);
		setLocationRelativeTo(null); // Centrar en la pantalla
	}

}

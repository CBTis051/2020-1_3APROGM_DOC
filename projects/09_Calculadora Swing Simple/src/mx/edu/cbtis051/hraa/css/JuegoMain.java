package mx.edu.cbtis051.hraa.css;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JuegoMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JuegoMain frame = new JuegoMain();
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
	public JuegoMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("JUEGO DE NÚMEROS ALEATORIOS");
		lblNewLabel.setFont(new Font("Ubuntu Mono", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 438, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblInstrucciones = new JLabel("<html>\nINSTRUCCIONES:\n<ul>\n\t<li>El objetivo del juego es adivinar un número aleatorio entre 0 y 9.</li>\n\t<li>Sólo se permiten números enteros.</li>\n\t<li>Se tienen 3 intentos para adivinar el número.</li>\n</ul>\n</html>");
		lblInstrucciones.setVerticalAlignment(SwingConstants.TOP);
		lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstrucciones.setFont(new Font("Ubuntu Mono", Font.PLAIN, 16));
		lblInstrucciones.setBounds(6, 51, 438, 110);
		contentPane.add(lblInstrucciones);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se solicita el nombre del usuario
				String jugador = JOptionPane.showInputDialog(
							JuegoMain.this, 
							"Introduce tu nombre", 
							"Juego nuevo", 
							JOptionPane.QUESTION_MESSAGE
						);
				
				// Se crea el juego
				Juego juego = new Juego(jugador);
				
				System.out.println(juego.numero);
				
				juego.jugar(JuegoMain.this);
				
			}
		});
		btnJugar.setBounds(153, 173, 117, 41);
		contentPane.add(btnJugar);
	}
}

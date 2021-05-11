package mx.hdsti;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class SplashScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen window = new SplashScreen();
					window.frame.setVisible(true);
					
					// Creamos un timer para abrir el login
					Timer temporizador = new Timer(4000, new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// Abrimos el login y ocultamos el SplashScreen
							Login ventana = new Login();
							ventana.setVisible(true);
							
							window.frame.setVisible(false);
						}
					});
					
					// Iniciamos el Timer
					temporizador.setRepeats(false);
					temporizador.start();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SplashScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 216, 216);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSplash = new JLabel("");
		lblSplash.setHorizontalAlignment(SwingConstants.CENTER);
		lblSplash.setIcon(new ImageIcon(SplashScreen.class.getResource("/img/logo.png")));
		lblSplash.setBounds(6, 6, 204, 204);
		frame.getContentPane().add(lblSplash);
		
		// Quitar marco a la ventana
		frame.setUndecorated(true);
		
		// Centrar en la pantalla
		frame.setLocationRelativeTo(null);
	}
}














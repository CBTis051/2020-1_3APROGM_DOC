package mx.edu.cbtis051.hraa.sistema;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Splash extends JFrame {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
					frame.setVisible(true);
					
					// Creamos un timer para que después de 3 segundos se abra el Main
					Timer temporizador = new Timer(3000, new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// Abrimos el Main y cerramos el Splash
							Main ventana = new Main();
							ventana.setVisible(true);
							
							// Test de productos
							//ventana.testApiProductos();
							
							frame.setVisible(false);
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
	 * Create the frame.
	 */
	public Splash() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 320, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblSplash = new JLabel("");
		lblSplash.setIcon(new ImageIcon(Splash.class.getResource("/images/logo_dgeti.png")));
		lblSplash.setBounds(10, 10, 300, 300);
		contentPane.add(lblSplash);
		setLocationRelativeTo(null); // Centrar en la pantalla
	}
}

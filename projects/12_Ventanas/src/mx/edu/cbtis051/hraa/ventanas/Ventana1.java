package mx.edu.cbtis051.hraa.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana1 frame = new Ventana1();
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
	public Ventana1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setUndecorated(true); // Quitar todo el borde y controles
		setLocationRelativeTo(null); // Centrar en la pantalla
		
		JLabel lblNewLabel = new JLabel("VENTANA 1");
		lblNewLabel.setFont(new Font("Exo", Font.PLAIN, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(30, 30, 316, 71);
		contentPane.add(lblNewLabel);
		
		JButton btn1 = new JButton("1");
		btn1.setEnabled(false);
		btn1.setFont(new Font("Exo", Font.PLAIN, 40));
		btn1.setBounds(30, 123, 70, 70);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abrimos la ventana 2
				Ventana2 frame = new Ventana2();
				frame.setVisible(true);
				Ventana1.this.setVisible(false);
			}
		});
		btn2.setFont(new Font("Exo", Font.PLAIN, 40));
		btn2.setBounds(112, 123, 70, 70);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abrimos la ventana 3
				Ventana3 frame = new Ventana3();
				frame.setVisible(true);
				Ventana1.this.setVisible(false);
			}
		});
		btn3.setFont(new Font("Exo", Font.PLAIN, 40));
		btn3.setBounds(194, 123, 70, 70);
		contentPane.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abrimos la ventana 4
				Ventana4 frame = new Ventana4();
				frame.setVisible(true);
				Ventana1.this.setVisible(false);
			}
		});
		btn4.setFont(new Font("Exo", Font.PLAIN, 40));
		btn4.setBounds(276, 123, 70, 70);
		contentPane.add(btn4);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Salimos completamente de la aplicación
				System.exit(0);
			}
		});
		btnX.setFont(new Font("Exo", Font.PLAIN, 20));
		btnX.setBounds(338, 6, 35, 35);
		contentPane.add(btnX);
	}
}







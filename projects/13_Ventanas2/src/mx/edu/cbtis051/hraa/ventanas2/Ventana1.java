package mx.edu.cbtis051.hraa.ventanas2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5928655629636338409L;
	private JPanel contentPane;
	private JTextArea txtData;
	private String data;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Ventana 1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Modak", Font.PLAIN, 45));
		lblNewLabel.setBounds(6, 6, 438, 68);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Objetivo: enviar información de una ventana a otra.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 86, 438, 16);
		contentPane.add(lblNewLabel_1);
		
		txtData = new JTextArea();
		txtData.setBounds(6, 114, 438, 105);
		contentPane.add(txtData);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Abrimos la ventana 2
				Ventana2 ventana = new Ventana2();
				
				// Mandamos la información a la ventana 2
				ventana.setData(txtData.getText());
				
				ventana.setVisible(true);
				
				// Oculto la ventana actual
				Ventana1.this.setVisible(false);
				
			}
		});
		btnEnviar.setBounds(327, 231, 117, 35);
		contentPane.add(btnEnviar);
		setLocationRelativeTo(null); // Centrar en la pantalla
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
		
		// Mostramos la data en el TextArea
		txtData.setText(this.data);
	}
}












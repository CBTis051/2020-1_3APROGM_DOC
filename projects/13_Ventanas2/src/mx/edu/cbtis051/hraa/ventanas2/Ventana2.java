package mx.edu.cbtis051.hraa.ventanas2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1604190278453735416L;
	private JPanel contentPane;
	private JTextPane tpData;
	private String data;

	/**
	 * Create the frame.
	 */
	public Ventana2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Ventana 2");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Modak", Font.PLAIN, 45));
		lblNewLabel.setBounds(6, 6, 438, 68);
		contentPane.add(lblNewLabel);
		
		tpData = new JTextPane();
		tpData.setBounds(6, 87, 438, 128);
		contentPane.add(tpData);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Abrimos la ventana 1
				Ventana1 ventana = new Ventana1();
				
				// Mandamos la información a la ventana 1
				ventana.setData(tpData.getText());
				
				ventana.setVisible(true);
				
				// Ocultamos la ventana actual
				Ventana2.this.setVisible(false);
			}
		});
		btnRegresar.setBounds(321, 227, 123, 39);
		contentPane.add(btnRegresar);
		setLocationRelativeTo(null); // Centrar en la pantalla
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
		
		// Mostramos la data en el TextPane
		tpData.setText(this.data);
	}
}











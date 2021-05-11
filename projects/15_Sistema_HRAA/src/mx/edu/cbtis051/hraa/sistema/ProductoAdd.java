package mx.edu.cbtis051.hraa.sistema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mx.edu.cbtis051.hraa.sistema.api.Api;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductoAdd extends JFrame {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTextField txtImagen;

	/**
	 * Create the frame.
	 */
	public ProductoAdd() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Centrar en la pantalla
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("AGREGAR PRODUCTO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dax Pro", Font.PLAIN, 20));
		lblNewLabel.setBounds(6, 6, 438, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(6, 45, 100, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descripción");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(6, 84, 100, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Modelo");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(6, 123, 100, 27);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Marca");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(6, 162, 100, 27);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Imagen");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(6, 201, 100, 27);
		contentPane.add(lblNewLabel_1_4);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtNombre.setBounds(118, 46, 326, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(118, 85, 326, 26);
		contentPane.add(txtDescripcion);
		
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtModelo.setColumns(10);
		txtModelo.setBounds(118, 124, 326, 26);
		contentPane.add(txtModelo);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtMarca.setColumns(10);
		txtMarca.setBounds(118, 163, 326, 26);
		contentPane.add(txtMarca);
		
		txtImagen = new JTextField();
		txtImagen.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtImagen.setColumns(10);
		txtImagen.setBounds(118, 202, 326, 26);
		contentPane.add(txtImagen);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Se recopila la información para crear el producto
				long id = Api.addProducto(
						txtNombre.getText(),
						txtDescripcion.getText(),
						txtModelo.getText(),
						txtMarca.getText(),
						txtImagen.getText()
						);
				
				// Se valida si se agregó el producto correctamente
				if (id > 0) {
					JOptionPane
					.showMessageDialog(ProductoAdd.this, "Producto agregado correctamente con el id: "+id);
					
					// Cerramos la ventana actual y mostramos el catálogo
					ProductoAdd.this.dispose();
					
					ProductoCatalog productoCatalog = ProductoCatalog.getInstance();
					productoCatalog.setVisible(true);
				}
				
			}
		});
		btnGuardar.setBounds(327, 240, 117, 29);
		contentPane.add(btnGuardar);
	}
}

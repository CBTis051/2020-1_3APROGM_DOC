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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		// WindowListener
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				cerrarVentana(true);

			}
		});
		
		JLabel lblNewLabel = new JLabel("AGREGAR PRODUCTO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dax Pro", Font.PLAIN, 20));
		lblNewLabel.setBounds(6, 6, 438, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblNombre.setBounds(6, 45, 98, 27);
		contentPane.add(lblNombre);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblDescripcin.setBounds(6, 84, 98, 27);
		contentPane.add(lblDescripcin);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblModelo.setBounds(6, 123, 98, 27);
		contentPane.add(lblModelo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarca.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblMarca.setBounds(6, 162, 98, 27);
		contentPane.add(lblMarca);
		
		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImagen.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		lblImagen.setBounds(6, 201, 98, 27);
		contentPane.add(lblImagen);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtNombre.setBounds(116, 46, 328, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(116, 85, 328, 26);
		contentPane.add(txtDescripcion);
		
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtModelo.setColumns(10);
		txtModelo.setBounds(116, 124, 328, 26);
		contentPane.add(txtModelo);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtMarca.setColumns(10);
		txtMarca.setBounds(116, 163, 328, 26);
		contentPane.add(txtMarca);
		
		txtImagen = new JTextField();
		txtImagen.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		txtImagen.setColumns(10);
		txtImagen.setBounds(116, 202, 328, 26);
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
				
				if (id > 0) {
					JOptionPane.showMessageDialog(ProductoAdd.this, "Producto agregado con el ID: "+id);
					cerrarVentana(false);
				}
				
			}
		});
		btnGuardar.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		btnGuardar.setBounds(327, 237, 117, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana(true);
			}
		});
		btnCancelar.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		btnCancelar.setBounds(216, 237, 117, 29);
		contentPane.add(btnCancelar);
	}
	
	private void cerrarVentana(boolean ask) {
		
		int cerrar = 0;
		
		// Se valida si se debe preguntar
		if (ask) {			
			// Se pregunta si se desea cerrar la ventana
			cerrar = JOptionPane
					.showConfirmDialog(
							ProductoAdd.this,
							"¿Desea cancelar la creación del producto?",
							"Productos",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
		}
		
		if (!ask || cerrar == JOptionPane.YES_OPTION) {
			
			this.dispose();
			ProductoCatalog catalogo = ProductoCatalog.getInstance();
			catalogo.setVisible(true);
			
		}
		
	}
	
}

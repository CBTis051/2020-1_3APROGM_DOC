package mx.edu.cbtis051.hraa.sistema;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mx.edu.cbtis051.hraa.sistema.api.Api;
import mx.edu.cbtis051.hraa.sistema.models.Producto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductoCatalog extends JFrame {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	// Objeto para el singleton
	private static ProductoCatalog instance;

	/**
	 * Create the frame.
	 */
	private ProductoCatalog() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Centramos en la pantalla
		setLocationRelativeTo(null);
		
		// Se invoca el método mostrarProductos para crear la tabla
		mostrarProductos();
		
		scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Abrimos el formulario para agregar un producto
				ProductoAdd productoAdd = new ProductoAdd();
				productoAdd.setVisible(true);
				
			}
		});
		btnAgregar.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		panel.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Obtenemos la fila de la tabla que está seleccionada
				String registro = "";
				
				// Validamos que se tenga seleccionada una fila
				if (table.getSelectedRow() != -1) {
					
					// Si se tiene un registro seleccionado
					registro = table
							.getValueAt(table.getSelectedRow(), 0).toString();
					long id = Long.parseLong(registro); // Convertir el id de String a long
					
					// Se pregunta si se desea eliminar el producto o no
					int eliminar = JOptionPane
							.showConfirmDialog(
									ProductoCatalog.this,
									"¿Desea eliminar el producto con el id: "+id+"?",
									"Productos",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE
									);
					
					if (eliminar == JOptionPane.YES_OPTION) {
						
						// Se hace la petición para eliminar el producto
						if (Api.deleteProducto(id) == 1) {
							
							// Se eliminó correctamente
							JOptionPane
							.showMessageDialog(ProductoCatalog.this, "Producto eliminado exitósamente.");
							
							// Actualizamos la información de la tabla
							mostrarProductos();
							scrollPane.setViewportView(table);
							
						} else {

							// No se eliminó
							JOptionPane
							.showMessageDialog(ProductoCatalog.this, "No se pudo eliminar el producto.");
						}
						
					}
					
				} else {
					
					// No hay ningún registro seleccionado
					JOptionPane
					.showMessageDialog(ProductoCatalog.this, "Por favor seleccione un producto");
					
				}
				
			}
		});
		btnEliminar.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		panel.add(btnEliminar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Actualizamos la información de la tabla
				mostrarProductos();
				scrollPane.setViewportView(table);
				
			}
		});
		btnActualizar.setFont(new Font("Dax Pro", Font.PLAIN, 15));
		panel.add(btnActualizar);
	}
	
	/**
	 * Muestra el contenido de la tabla
	 */
	private void mostrarProductos() {
		
		// Se crea un modelo para la tabla
		DefaultTableModel modelo = new DefaultTableModel() {
			
			/**
			 * Default serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// Agregar encabezados de columnas
		modelo.setColumnIdentifiers(
				new String[] {
						"ID",
						"NOMBRE",
						"DESCRIPCION",
						"MODELO",
						"MARCA",
						"IMAGEN"
				}
				);
		
		// Se crea la tabla a partir del modelo
		table = new JTable(modelo);
		
		// Se asigna el tipo de fuente de la tabla
		table.setFont(new Font("Dax Pro", Font.PLAIN, 12));
		
		// Se invoca el endpoint para obtener los productos
		Producto[] productos = Api.getProductos();
		
		if (productos != null) {
			// Se recorre el array de productos y se agregan al modelo
			for (Producto producto : productos) {
				// Se agrega el producto al modelo
				modelo.addRow(
						new String[] {
								Long.toString(producto.getId()),
								producto.getNombre(),
								producto.getDescripcion(),
								producto.getModelo(),
								producto.getMarca(),
								producto.getImagen()
						}
						);
			}
		}
		
	}
	
	// Singleton
	public static ProductoCatalog getInstance() {
		
		// Validamos si ya se creó el objeto
		if (instance == null) {
			instance = new ProductoCatalog();
		}
		
		return instance;
		
	}

}

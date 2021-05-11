package mx.edu.cbtis051.hraa.sistema;

import java.awt.BorderLayout;
import java.awt.Font;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductoCatalog extends JFrame {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private static ProductoCatalog instance;

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JButton btnActualizar;

	private JScrollPane scrollPane;
	private JButton btnEliminar;
	private JButton btnAgregar;

	/**
	 * Create the frame.
	 */
	private ProductoCatalog() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// Se invoca el método para mostrar los usuarios
		mostrarProductos();
		
		scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarProductos();
				scrollPane.setViewportView(table);
			}
		});
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Obtenemos el registro seleccionado de la tabla
				String registro = "";
				if (table.getSelectedRow() != -1) {
					
					registro = table.getValueAt(table.getSelectedRow(), 0).toString();
					long id = Long.parseLong(registro);
					
					// Se pregunta si se desea eliminar el producto o no
					int eliminar = JOptionPane
							.showConfirmDialog(
									ProductoCatalog.this,
									"Desea eliminar el producto con el id: "+id+"?",
									"Productos",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
					
					if (eliminar == JOptionPane.YES_OPTION) {
						
						// Se hace la petición
						if (Api.deleteProducto(id)==1) {
							JOptionPane.showMessageDialog(ProductoCatalog.this, "Producto eliminado correctamente");
							
							// Se actualiza la tabla
							mostrarProductos();
							scrollPane.setViewportView(table);
							
						} else {
							JOptionPane.showMessageDialog(ProductoCatalog.this, "No se pudo eliminar el producto seleccionado");
						}
						
					}
					
				} else {
					// Si no hay ningún registro seleccionado
					JOptionPane.showMessageDialog(ProductoCatalog.this, "Por favor seleccione un producto");
				}
				
			}
		});
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Se abre la ventana para agregar un producto
				ProductoAdd productoAdd = new ProductoAdd();
				productoAdd.setVisible(true);
				
				ProductoCatalog.this.setVisible(false);
				
			}
		});
		panel.add(btnAgregar);
		panel.add(btnEliminar);
		panel.add(btnActualizar);
		setLocationRelativeTo(null);
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
			public boolean isCellEditable(int row, int column) { return false; };
		};
		
		// Agregar encabezados de columnas
		modelo.setColumnIdentifiers(
				new String[] {
					"ID",
					"NOMBRE",
					"MODELO",
					"MARCA"
				}
				);
		
		// Se crea la tabla
		table = new JTable(modelo);
		
		// Se asigna el tipo de fuente
		table.setFont(new Font("Exo", Font.PLAIN, 12));
		
		// Se llena la información de la tabla
		Producto[] productos = Api.getProductos();
		
		if (productos != null) {
			for (Producto producto : productos) {
				// Se agrega el producto al modelo
				modelo.addRow(new String[] {
						Long.toString(producto.getId()),
						producto.getNombre(),
						producto.getModelo(),
						producto.getMarca()
				});
			}
		}
		
	}
	
	// Singleton
	public static ProductoCatalog getInstance() {
		
		if (instance == null) {
			instance = new ProductoCatalog();
		}
		
		return instance;
	}

}

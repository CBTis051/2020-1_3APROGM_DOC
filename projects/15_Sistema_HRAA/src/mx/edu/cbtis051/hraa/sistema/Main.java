package mx.edu.cbtis051.hraa.sistema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); // Absolute layout
		setContentPane(contentPane);
		
		JButton btnProductos = new JButton("PRODUCTOS");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Abrimos el catálogo de productos
				ProductoCatalog productoCatalog = ProductoCatalog.getInstance();
				productoCatalog.setVisible(true);
				
			}
		});
		btnProductos.setBounds(154, 103, 150, 50);
		contentPane.add(btnProductos);
		
		// Centrar en la pantalla
		setLocationRelativeTo(null);
	}
	
	public void testApiProductos() {
		
		// Obtenemos el array con todos los productos
//		Producto[] array = Api.getProductos();
//		
//		// Validamos que se haya obtenido la lista de productos
//		if (array != null) {
//			
//			// Iteramos el array e imprimimos cada producto
//			for (Producto producto : array) {
//				// Imprimimos el producto
//				System.out.println(producto.toString());
//			}
//			
//		}
		
		// Obtenemos un solo producto
		//System.out.println(Api.getProducto(101));
		
		// Creamos un producto
//		long id = Api.addProducto(
//				"Xiaomi BlackShark", 
//				"El Black Shark 3 es simétrico tanto vertical como horizontalmente en la parte posterior, por lo que puede sentir el equilibrio entre sus dos manos.", 
//				"Black Shark 3", 
//				"Xiaomi", 
//				"https://i.linio.com/p/c266136e67851639c777dba955bd21ed-product.webp"
//				);
//		
//		System.out.println("Producto creado con el ID: "+id);
		
		// Actualizamos la información de un producto
//		long actualizados = Api.updateProducto(
//				"Xiaomi BlackShark Editado", 
//				"El Black Shark 3 es simétrico tanto vertical como horizontalmente en la parte posterior, por lo que puede sentir el equilibrio entre sus dos manos.", 
//				"Black Shark 3", 
//				"Xiaomi", 
//				"https://i.linio.com/p/c266136e67851639c777dba955bd21ed-product.webp",
//				3100
//				);
//		
//		System.out.println("Producto actualizado exitósamente. --> "+actualizados);
		
		// Eliminamos un producto
//		long eliminados = Api.deleteProducto(53);
//		
//		System.out.println("Producto eliminado exitósamente. --> "+eliminados);
		
	}

}

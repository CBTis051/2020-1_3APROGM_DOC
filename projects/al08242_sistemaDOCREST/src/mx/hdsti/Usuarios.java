package mx.hdsti;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Usuarios extends JInternalFrame {
	
	// Variables
	JTable table;
	Auxiliar aux;
	Connection conexion;
	ResultSet rs;

	/**
	 * Create the frame.
	 */
	public Usuarios() {
		
		aux = new Auxiliar();
		
		setTitle("CAT\u00C1LOGO DE USUARIOS");
		setClosable(true);
		setBounds(100, 100, 500, 350);
		getContentPane().setLayout(null);
		
		// Invocamos el método para mostrar los usuarios
		mostrarUsuarios();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 402, 298);
		scrollPane.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		getContentPane().add(scrollPane);
		
		JButton btnActualizar = new JButton("");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarUsuarios();
				scrollPane.setViewportView(table);
			}
		});
		btnActualizar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/i_actualizar.png")));
		btnActualizar.setBounds(422, 11, 50, 50);
		getContentPane().add(btnActualizar);
		
		JButton btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Usuarios_agregar ventana = new Usuarios_agregar();
				
				Principal.desktopPane.add(ventana);
				
				ventana.setLocation(
						(Principal.desktopPane.getWidth()-ventana.getWidth())/2,
						(Principal.desktopPane.getHeight()-ventana.getHeight())/2
					);
				
				ventana.setVisible(true);
				
			}
		});
		btnAgregar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/i_agregar.png")));
		btnAgregar.setBounds(422, 72, 50, 50);
		getContentPane().add(btnAgregar);
		
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Obtenemos el registro seleccionado
				// Variable para saber el registro seleccionado
				String registro = "";
				
				// Verificar si hay un registro seleccionado
				if(table.getSelectedRow() != -1) {
										
					// Obtenemos el id del usuario
					registro = table.getValueAt(table.getSelectedRow(), 0).toString();
					int idusuario = Integer.parseInt(registro);
					
					// Abrimos el frame de edición
					Usuarios_editar ventana = new Usuarios_editar(idusuario);
					
					Principal.desktopPane.add(ventana);
					
					ventana.setLocation(
							(Principal.desktopPane.getWidth()-ventana.getWidth())/2,
							(Principal.desktopPane.getHeight()-ventana.getHeight())/2
						);
					
					ventana.setVisible(true);
					
				}
				else {
					//Si no hay ningun registro seleccionado
					JOptionPane.showMessageDialog(Usuarios.this, "Por favor seleccione un usuario");       
				}
				
			}
		});
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/i_editar.png")));
		btnEditar.setBounds(422, 133, 50, 50);
		getContentPane().add(btnEditar);
		
		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Obtenemos usuario seleccionado y preguntamos si se desea eliminar
				
				// Variable para saber el registro seleccionado
				String registro = "";
				
				// Verificar si hay un registro seleccionado
				if(table.getSelectedRow() != -1) {
										
					// Obtenemos el id del usuario
					registro = table.getValueAt(table.getSelectedRow(), 0).toString();
					int idusuario = Integer.parseInt(registro);
					
					// Verificamos que no sea el usuario activo
					if (idusuario ==  AppData.id) {
						JOptionPane.showMessageDialog(Usuarios.this, "No es posible eliminar su propio usuario", "Usuarios", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Eliminar el usuario seleccionado
					int eliminar = JOptionPane.showConfirmDialog(Usuarios.this, "¿Desea eliminar el usuario con el ID: "+idusuario+"?", "Contactos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (eliminar == JOptionPane.YES_OPTION) {
						
						// Nos conectamos
						conexion = aux.conectar(AppData.bd, AppData.bduser, AppData.bduserpassword);
						aux.eliminarUsuario(idusuario, conexion);
						
						// Mostrar mensaje de éxito y actualizar la tabla
						JOptionPane.showMessageDialog(Usuarios.this, "Usuario eliminado con éxito");
						
						// Cerramos la conexión
						aux.desconectar(conexion);
						
						mostrarUsuarios();
						scrollPane.setViewportView(table);
						
					}
					
				}
				else {
					//Si no hay ningun registro seleccionado
					JOptionPane.showMessageDialog(Usuarios.this, "Por favor seleccione un usuario");       
				}
				
			}
		});
		btnEliminar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/i_eliminar.png")));
		btnEliminar.setBounds(422, 194, 50, 50);
		getContentPane().add(btnEliminar);
		
		

	}
	
	public void mostrarUsuarios() {
		
		//Crear un modelo para la tabla
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		//Agregar encabezados de columnas
		modelo.setColumnIdentifiers( new String[] {
			"ID", 
			"NOMBRE", 
			"USUARIO",
			"ROL"		
		});
		
		table = new JTable(modelo);
		
		table.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		
		//Llenado de la información de la tabla
		try {
			
			//Obtenemos la conexión y los usuarios de la BD
			
			conexion = aux.conectar(AppData.bd, AppData.bduser, AppData.bduserpassword);
			
			rs = aux.getUsuarios(conexion);
			
			//Ciclo para agregar uno a uno los registros
			while(rs.next()) {
				modelo.addRow(new String[] {
					Integer.toString(rs.getInt("idusuario")),
					rs.getString("nombre"),
					rs.getString("usuario"),
					rs.getString("rol")
				});
			}
			
			// Cerramos la conexión
			aux.desconectar(conexion);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

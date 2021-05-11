package mx.hdsti;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Usuarios_editar extends JInternalFrame {
	
	// Variables
	Auxiliar aux;
	private JTextField txtNombre;
	private JTextField txtUsuario;
	Connection conexion;
	ResultSet rs;
	private JComboBox cbRol;
	int id;

	/**
	 * Create the frame.
	 */
	public Usuarios_editar(int id) {
		
		aux = new Auxiliar();
		
		this.id = id;
		
		setTitle("EDITAR USUARIO");
		setClosable(true);
		setBounds(100, 100, 500, 257);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("NOMBRE");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		label.setBounds(10, 18, 118, 20);
		getContentPane().add(label);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBounds(138, 11, 336, 35);
		getContentPane().add(txtNombre);
		
		JLabel label_1 = new JLabel("USUARIO");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		label_1.setBounds(10, 64, 118, 20);
		getContentPane().add(label_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsuario.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(138, 57, 336, 35);
		getContentPane().add(txtUsuario);
		
		JLabel label_4 = new JLabel("ROL");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		label_4.setBounds(10, 108, 118, 20);
		getContentPane().add(label_4);
		
		cbRol = new JComboBox();
		cbRol.setModel(new DefaultComboBoxModel(new String[] {"administrador", "operador"}));
		cbRol.setBounds(138, 103, 336, 35);
		getContentPane().add(cbRol);
		
		JButton btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Validaciones
				if (txtNombre.getText().equals("") || txtUsuario.getText().equals("")) {
					
					JOptionPane.showMessageDialog(Usuarios_editar.this, "Por favor complete todos los campos", "Editar usuario", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				// Editar la información del usuario
				Connection conn = aux.conectar(AppData.bd, AppData.bduser, AppData.bduserpassword);
				
				// Obtener mensaje resultado de la operación de actualización
				String editado = aux.actualizarUsuario(id, txtNombre.getText(), txtUsuario.getText(), cbRol.getSelectedItem().toString(), conn);
				
				// Cerrar conexión
				aux.desconectar(conn);
				
				if(editado.equals("true")) {
					JOptionPane.showMessageDialog(Usuarios_editar.this, "Información actualizada correctamente.\nPor favor actualice el catálogo.", "Editar usuario", JOptionPane.INFORMATION_MESSAGE);
					
					// Cerramos el frame
					Usuarios_editar.this.doDefaultCloseAction();
				} else {
					JOptionPane.showMessageDialog(Usuarios_editar.this, "No se pudo actualizar la información del usuario: \n"+editado, "Editar usuario", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnGuardar.setIcon(new ImageIcon(Usuarios_editar.class.getResource("/img/i_guardar.png")));
		btnGuardar.setBounds(424, 149, 50, 50);
		getContentPane().add(btnGuardar);
		
		JButton btnRegresar = new JButton("");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuarios_editar.this.doDefaultCloseAction();
			}
		});
		btnRegresar.setIcon(new ImageIcon(Usuarios_editar.class.getResource("/img/i_regresar.png")));
		btnRegresar.setBounds(364, 149, 50, 50);
		getContentPane().add(btnRegresar);
		
		mostrarDatos();

	}
	
	public void mostrarDatos() {
		
		// Obtenemos la información del usuario
		conexion = aux.conectar(AppData.bd, AppData.bduser, AppData.bduserpassword);
		
		rs = aux.getUsuario(id, conexion);
		
		try {
			
			if (rs.first()) {
				
				// Mostrar datos
				txtNombre.setText(rs.getString("nombre"));
				txtUsuario.setText(rs.getString("usuario"));
				cbRol.setSelectedItem(rs.getString("rol"));
				
			} else {
				JOptionPane.showMessageDialog(Usuarios_editar.this, "No se pudo cargar la información del usuario", "Usuarios", JOptionPane.ERROR_MESSAGE);
				doDefaultCloseAction();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

package mx.hdsti;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Usuarios_agregar extends JInternalFrame {
	private JTextField txtNombre;
	private JTextField txtUsuario;
	private JPasswordField txtContra;
	private JPasswordField txtContraConfirm;
	Auxiliar aux;

	/**
	 * Create the frame.
	 */
	public Usuarios_agregar() {
		
		aux = new Auxiliar();
		
		setTitle("AGREGAR USUARIO");
		setClosable(true);
		setBounds(100, 100, 500, 350);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblNombre.setBounds(10, 27, 118, 20);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setBounds(138, 20, 336, 35);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblUsuario.setBounds(10, 73, 118, 20);
		getContentPane().add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsuario.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(138, 66, 336, 35);
		getContentPane().add(txtUsuario);
		
		JLabel lblContra = new JLabel("CONTRASEÑA");
		lblContra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContra.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblContra.setBounds(10, 119, 118, 20);
		getContentPane().add(lblContra);
		
		txtContra = new JPasswordField();
		txtContra.setHorizontalAlignment(SwingConstants.LEFT);
		txtContra.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtContra.setColumns(10);
		txtContra.setBounds(138, 112, 336, 35);
		getContentPane().add(txtContra);
		
		JLabel lblConfirmar = new JLabel("CONFIRMAR");
		lblConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmar.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblConfirmar.setBounds(10, 165, 118, 20);
		getContentPane().add(lblConfirmar);
		
		txtContraConfirm = new JPasswordField();
		txtContraConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		txtContraConfirm.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		txtContraConfirm.setColumns(10);
		txtContraConfirm.setBounds(138, 158, 336, 35);
		getContentPane().add(txtContraConfirm);
		
		JLabel lblRol = new JLabel("ROL");
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRol.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		lblRol.setBounds(10, 209, 118, 20);
		getContentPane().add(lblRol);
		
		JComboBox cbRol = new JComboBox();
		cbRol.setModel(new DefaultComboBoxModel(new String[] {"administrador", "operador"}));
		cbRol.setBounds(138, 204, 336, 35);
		getContentPane().add(cbRol);
		
		JButton btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Validaciones
				
				String contra = String.copyValueOf(txtContra.getPassword());
				String contraConfirm = String.copyValueOf(txtContraConfirm.getPassword());
				
				if (txtNombre.getText().equals("") || txtUsuario.getText().equals("") || contra.equals("") || contraConfirm.equals("")) {
					
					JOptionPane.showMessageDialog(Usuarios_agregar.this, "Por favor complete todos los campos", "Agregar usuario", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				if (!contra.equals(contraConfirm)) {
					
					JOptionPane.showMessageDialog(Usuarios_agregar.this, "Las contraseñas no coinciden", "Agregar usuario", JOptionPane.ERROR_MESSAGE);
					
					txtContra.requestFocus();
					txtContra.selectAll();
					
					return;
				}
				
				// Guardar la información
				Connection conn = aux.conectar(AppData.bd, AppData.bduser, AppData.bduserpassword);
				
				// Codificar la contraseña
				contra = aux.toSHA1(contra);
				
				// Obtener mensaje resultado de la operación de agregar
				String agregado = aux.agregarUsuario(txtNombre.getText(), txtUsuario.getText(), contra, cbRol.getSelectedItem().toString(), conn);
				
				if(agregado.equals("true")) {
					JOptionPane.showMessageDialog(Usuarios_agregar.this, "Usuario agregado correctamente.\nPor favor actualice el catálogo.", "Agregar usuario", JOptionPane.INFORMATION_MESSAGE);
					
					// Cerramos el frame
					Usuarios_agregar.this.doDefaultCloseAction();
				} else {
					JOptionPane.showMessageDialog(Usuarios_agregar.this, "No se pudo agregar el usuario: \n"+agregado, "Agregar usuario", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnGuardar.setIcon(new ImageIcon(Usuarios_agregar.class.getResource("/img/i_guardar.png")));
		btnGuardar.setBounds(424, 250, 50, 50);
		getContentPane().add(btnGuardar);
		
		JButton btnRegresar = new JButton("");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuarios_agregar.this.doDefaultCloseAction();
			}
		});
		btnRegresar.setIcon(new ImageIcon(Usuarios_agregar.class.getResource("/img/i_regresar.png")));
		btnRegresar.setBounds(364, 250, 50, 50);
		getContentPane().add(btnRegresar);

	}
}

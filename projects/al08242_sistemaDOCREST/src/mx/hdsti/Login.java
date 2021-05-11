package mx.hdsti;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent.KeyBinding;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.RenderingHints.Key;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContra;
	
	private Auxiliar aux;
	private JButton btnEntrar;

	/**
	 * Create the frame.
	 */
	public Login() {
		
		aux = new Auxiliar();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Centrar en la pantalla
		setLocationRelativeTo(null);
		// Quitar marco a la ventana
		setUndecorated(true);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/img/logo.png")));
		lblLogo.setBounds(6, 6, 238, 238);
		contentPane.add(lblLogo);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Ubuntu Mono", Font.PLAIN, 15));
		lblUsuario.setBounds(16, 256, 61, 16);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("Ubuntu Mono", Font.PLAIN, 15));
		txtUsuario.setBounds(6, 275, 238, 32);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		lblContrasea.setFont(new Font("Ubuntu Mono", Font.PLAIN, 15));
		lblContrasea.setBounds(16, 319, 121, 16);
		contentPane.add(lblContrasea);
		
		txtContra = new JPasswordField();
		txtContra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Validar el Enter
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnEntrar.doClick();
				}
			}
		});
		txtContra.setHorizontalAlignment(SwingConstants.CENTER);
		txtContra.setFont(new Font("Ubuntu Mono", Font.PLAIN, 15));
		txtContra.setColumns(10);
		txtContra.setBounds(6, 336, 238, 32);
		contentPane.add(txtContra);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cerrar aplicación
				System.exit(0);
			}
		});
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/img/i_cancel.png")));
		btnCancelar.setBounds(143, 380, 50, 50);
		contentPane.add(btnCancelar);
		
		btnEntrar = new JButton("");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtenemos las credenciales del usuario
				String usuario = txtUsuario.getText();
				String contra = 
						String.copyValueOf(txtContra.getPassword());
				
				// Validamos que no estén vacíos
				if(usuario.equals("")||contra.equals("")) {
					JOptionPane.showMessageDialog(
							Login.this, 
							"Introduzca usuario y contraseña por favor.", 
							"Login", 
							JOptionPane.ERROR_MESSAGE);
					// Salimos del método
					return;
				}
				
				// Codificamos la contraseña
				contra = aux.toSHA1(contra);
				
				// Intentamos el login
				aux.loginREST(usuario, contra);
				
//				// Iniciamos la conexión
//				Connection conn = 
//						aux.conectar(AppData.bd, AppData.bduser, AppData.bduserpassword);
//				
//				// Intentamos el login
//				if (aux.login(usuario, contra, conn)) {
//					
//					// Abrimos ventana principal
//					Principal ventana = new Principal();
//					ventana.setVisible(true);
//					
//					Login.this.setVisible(false);
//					
//					// Cerramos la conexión
//					aux.desconectar(conn);
//					
//				} else {
//					// Error al hacer login
//					JOptionPane.showMessageDialog(
//							Login.this, 
//							"Usuario y/o contraseña incorrectos.", 
//							"Login", 
//							JOptionPane.ERROR_MESSAGE);
//				}
				
			}
		});
		btnEntrar.setIcon(new ImageIcon(Login.class.getResource("/img/i_login.png")));
		btnEntrar.setBounds(194, 380, 50, 50);
		contentPane.add(btnEntrar);
	}
}









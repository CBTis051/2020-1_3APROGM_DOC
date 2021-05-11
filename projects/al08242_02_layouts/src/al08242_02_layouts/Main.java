package al08242_02_layouts;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContra;
	private JLabel lblUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("INICIAR SESIÓN EN EL SISTEMA");
		lblTitulo.setFont(new Font("Exo 2", Font.PLAIN, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(6, 6, 438, 38);
		contentPane.add(lblTitulo);
		
		lblUsuario = new JLabel("USUARIO");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Exo 2", Font.PLAIN, 14));
		lblUsuario.setBounds(6, 52, 104, 38);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 56, 275, 33);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContra = new JLabel("CONTRASEÑA");
		lblContra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContra.setFont(new Font("Exo 2", Font.PLAIN, 14));
		lblContra.setBounds(6, 102, 104, 38);
		contentPane.add(lblContra);
		
		txtContra = new JPasswordField();
		txtContra.setColumns(10);
		txtContra.setBounds(122, 106, 275, 33);
		contentPane.add(txtContra);
	}
}

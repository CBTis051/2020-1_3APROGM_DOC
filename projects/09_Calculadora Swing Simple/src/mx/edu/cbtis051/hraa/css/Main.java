package mx.edu.cbtis051.hraa.css;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumero1;
	private JTextField txtNumero2;
	private JTextField txtResultado;
	// Objeto de la clase Calculadora
	private Calculadora calc;

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
		
		// Inicializamos el objeto Calculadora
		calc = new Calculadora();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 214, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNumero1 = new JTextField();
		txtNumero1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("txtNumero1 focusLost");
				// Intentamos obtener un número double del JTextField
				try {
					
					// Obtenemos el texto de txtNumero1 y lo convertimos a double
					double n1 = Double.parseDouble(txtNumero1.getText());
					
					// Asignamos el valor del n1 al objeto calc
					calc.setNumero1(n1);
					
					System.out.println("Número 1 = " + calc.getNumero1());
					
				} catch (NumberFormatException e2) {
					// Manejamos el error
					System.out.println("El texto introducido no es un número válido.");
					JOptionPane.showMessageDialog(
							Main.this, 
							"El texto introducido no es un número válido.",
							"Error",
							JOptionPane.WARNING_MESSAGE
					);
					txtNumero1.requestFocus();
					txtNumero1.selectAll();
				}
			}
		});
		txtNumero1.setText("0");
		txtNumero1.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumero1.setFont(new Font("Verdana", Font.BOLD, 15));
		txtNumero1.setBounds(6, 34, 202, 50);
		contentPane.add(txtNumero1);
		txtNumero1.setColumns(10);
		
		txtNumero2 = new JTextField();
		txtNumero2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("txtNumero2 focusLost");
				// Intentamos obtener un número double del JTextField
				try {
					
					// Obtenemos el texto de txtNumero2 y lo convertimos a double
					double n2 = Double.parseDouble(txtNumero2.getText());
					
					// Asignamos el valor del n2 al objeto calc
					calc.setNumero2(n2);
					
					System.out.println("Número 2 = " + calc.getNumero2());
					
				} catch (NumberFormatException e2) {
					// Manejamos el error
					System.out.println("El texto introducido no es un número válido.");
					JOptionPane.showMessageDialog(
							Main.this, 
							"El texto introducido no es un número válido.",
							"Error",
							JOptionPane.WARNING_MESSAGE
					);
					txtNumero2.requestFocus();
					txtNumero2.selectAll();
				}
			}
		});
		txtNumero2.setText("0");
		txtNumero2.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumero2.setFont(new Font("Verdana", Font.BOLD, 15));
		txtNumero2.setColumns(10);
		txtNumero2.setBounds(6, 92, 202, 50);
		contentPane.add(txtNumero2);
		
		JButton btnSuma = new JButton("+");
		btnSuma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Obtenemos el resultado del objeto calculadora y lo mostramos
				 * en el txtResultado
				 */
				
				// Obtenemos el resultado de la suma y lo convertimos a String
				String resultado = Double.toString(calc.sumar());
				
				// Asignamos el texto con el resultado al txtResultado
				txtResultado.setText(resultado);
			}
		});
		btnSuma.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		btnSuma.setBounds(6, 154, 50, 50);
		contentPane.add(btnSuma);
		
		JButton btnResta = new JButton("-");
		btnResta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Obtenemos el resultado del objeto calculadora y lo mostramos
				 * en el txtResultado
				 */
				
				// Obtenemos el resultado de la resta y lo convertimos a String
				String resultado = Double.toString(calc.restar());
				
				// Asignamos el texto con el resultado al txtResultado
				txtResultado.setText(resultado);
			}
		});
		btnResta.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		btnResta.setBounds(56, 154, 50, 50);
		contentPane.add(btnResta);
		
		JButton btnProducto = new JButton("*");
		btnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Obtenemos el resultado del objeto calculadora y lo mostramos
				 * en el txtResultado
				 */
				
				// Obtenemos el resultado del producto y lo convertimos a String
				String resultado = Double.toString(calc.multiplicar());
				
				// Asignamos el texto con el resultado al txtResultado
				txtResultado.setText(resultado);
			}
		});
		btnProducto.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		btnProducto.setBounds(107, 154, 50, 50);
		contentPane.add(btnProducto);
		
		JButton btnCociente = new JButton("/");
		btnCociente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Obtenemos el resultado del objeto calculadora y lo mostramos
				 * en el txtResultado
				 */
				
				// Validamos que no se intente realizar una división por 0
				if (calc.getNumero2() == 0) {
					
					// Le indicamos al usuario que no puede realizar una división por 0
					JOptionPane.showMessageDialog(
							Main.this,
							"No es posible realizar una división entre 0.",
							"Error",
							JOptionPane.ERROR_MESSAGE
					);
					
					// Seleccionamos el número a corregir
					txtNumero2.requestFocus();
					txtNumero2.selectAll();
					
				} else {
					
					// Obtenemos el resultado del cociente y lo convertimos a String
					String resultado = Double.toString(calc.dividir());
				
					// Asignamos el texto con el resultado al txtResultado
					txtResultado.setText(resultado);
					
				}
				
				
			}
		});
		btnCociente.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		btnCociente.setBounds(158, 154, 50, 50);
		contentPane.add(btnCociente);
		
		txtResultado = new JTextField();
		txtResultado.setText("0");
		txtResultado.setHorizontalAlignment(SwingConstants.CENTER);
		txtResultado.setFont(new Font("Verdana", Font.BOLD, 15));
		txtResultado.setColumns(10);
		txtResultado.setBounds(6, 216, 202, 50);
		contentPane.add(txtResultado);
		
		JLabel lblNewLabel = new JLabel("CALCULADORA SIMPLE");
		lblNewLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 202, 16);
		contentPane.add(lblNewLabel);
	}
}

/**
 * TODO:
 * En la clase Calculadora:
 *  1 - Crear los dos métodos faltantes, el de multiplicar() y el de dividir().
 * En la clase Main:
 *  1 - Agregar el evento click (actionPerformed) al botón btnProducto y al botón
 *  btnCociente e implementar la correspondiente funcionalidad.
 */









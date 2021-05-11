package mx.edu.cbtis051.hraa.formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Alta extends JFrame {

	private JPanel contentPane;
	private JTextField txtPaterno;
	private JTextField txtMaterno;
	private JTextField txtNombre;
	private JTextField txtCalle;
	private JTextField txtColonia;
	private JComboBox<String> cbEstadoCivil;
	private JTextArea txtComentarios;
	private JTextField txtCP;
	private JRadioButton rbMasculino;
	private JRadioButton rbFemenino;
	private final ButtonGroup rbgSexo = new ButtonGroup();
	private JCheckBox chbCelular;
	private JCheckBox chbTablet;
	private JCheckBox chbComputadora;
	private DatePicker dpFechaNac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alta frame = new Alta();
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
	public Alta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null); // Centrar en la pantalla
		
		JLabel lblNewLabel = new JLabel("FORMULARIO DE ALTA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 16, 499, 16);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 34, 499, 12);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido paterno");
		lblNewLabel_1.setBounds(6, 44, 146, 16);
		contentPane.add(lblNewLabel_1);
		
		txtPaterno = new JTextField();
		txtPaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Verificamos si se presionó Enter para pasar el focus al siguiente control
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Pasamos el focus al siguiente control
					txtMaterno.requestFocus();
					txtMaterno.selectAll();
				}
			}
		});
		txtPaterno.setBounds(6, 66, 130, 26);
		contentPane.add(txtPaterno);
		txtPaterno.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido materno");
		lblNewLabel_1_1.setBounds(164, 44, 146, 16);
		contentPane.add(lblNewLabel_1_1);
		
		txtMaterno = new JTextField();
		txtMaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Verificamos si se presionó Enter para pasar el focus al siguiente control
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Pasamos el focus al siguiente control
					txtNombre.requestFocus();
					txtNombre.selectAll();
				}
			}
		});
		txtMaterno.setColumns(10);
		txtMaterno.setBounds(164, 66, 130, 26);
		contentPane.add(txtMaterno);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setBounds(322, 44, 146, 16);
		contentPane.add(lblNewLabel_1_2);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Verificamos si se presionó Enter para pasar el focus al siguiente control
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Pasamos el focus al siguiente control
					txtCalle.requestFocus();
					txtCalle.selectAll();
				}
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(322, 66, 130, 26);
		contentPane.add(txtNombre);
		
		JLabel lblNewLabel_1_3 = new JLabel("Calle y número");
		lblNewLabel_1_3.setBounds(6, 104, 146, 16);
		contentPane.add(lblNewLabel_1_3);
		
		txtCalle = new JTextField();
		txtCalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Verificamos si se presionó Enter para pasar el focus al siguiente control
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Pasamos el focus al siguiente control
					txtColonia.requestFocus();
					txtColonia.selectAll();
				}
			}
		});
		txtCalle.setColumns(10);
		txtCalle.setBounds(6, 126, 130, 26);
		contentPane.add(txtCalle);
		
		JLabel lblNewLabel_1_4 = new JLabel("Colonia");
		lblNewLabel_1_4.setBounds(164, 104, 146, 16);
		contentPane.add(lblNewLabel_1_4);
		
		txtColonia = new JTextField();
		txtColonia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Verificamos si se presionó Enter para pasar el focus al siguiente control
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Pasamos el focus al siguiente control
					txtCP.requestFocus();
					txtCP.selectAll();
				}
			}
		});
		txtColonia.setColumns(10);
		txtColonia.setBounds(164, 126, 130, 26);
		contentPane.add(txtColonia);
		
		JLabel lblNewLabel_2 = new JLabel("Sexo");
		lblNewLabel_2.setBounds(6, 164, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		rbMasculino = new JRadioButton("Masculino");
		rbMasculino.setSelected(true);
		rbMasculino.setActionCommand("masculino");
		rbgSexo.add(rbMasculino);
		rbMasculino.setBounds(6, 180, 141, 23);
		contentPane.add(rbMasculino);
		
		rbFemenino = new JRadioButton("Femenino");
		rbFemenino.setActionCommand("femenino");
		rbgSexo.add(rbFemenino);
		rbFemenino.setBounds(6, 204, 141, 23);
		contentPane.add(rbFemenino);
		
		JLabel lblNewLabel_3 = new JLabel("Dispositivos");
		lblNewLabel_3.setBounds(164, 164, 146, 16);
		contentPane.add(lblNewLabel_3);
		
		chbCelular = new JCheckBox("Celular");
		chbCelular.setBounds(164, 180, 128, 23);
		contentPane.add(chbCelular);
		
		chbTablet = new JCheckBox("Tablet");
		chbTablet.setBounds(164, 204, 128, 23);
		contentPane.add(chbTablet);
		
		chbComputadora = new JCheckBox("Computadora");
		chbComputadora.setBounds(164, 226, 128, 23);
		contentPane.add(chbComputadora);
		
		JLabel lblNewLabel_4 = new JLabel("Estado civil");
		lblNewLabel_4.setBounds(322, 164, 128, 16);
		contentPane.add(lblNewLabel_4);
		
		cbEstadoCivil = new JComboBox();
		cbEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Soltero", "Casado", "Unión libre", "Divorciado", "Viudo"}));
		cbEstadoCivil.setBounds(322, 180, 130, 27);
		contentPane.add(cbEstadoCivil);
		
		JLabel lblNewLabel_5 = new JLabel("Comentarios");
		lblNewLabel_5.setBounds(6, 247, 146, 16);
		contentPane.add(lblNewLabel_5);
		
		txtComentarios = new JTextArea();
		txtComentarios.setBounds(6, 275, 499, 78);
		contentPane.add(txtComentarios);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Procesamiento de la información del formulario
				System.out.println("DATOS DEL FORMULARIO");
				
				// Campos de texto
				System.out.println("Paterno: " + txtPaterno.getText());
				System.out.println("Materno: " + txtMaterno.getText());
				System.out.println("Nombre: " + txtNombre.getText());
				System.out.println("Calle: " + txtCalle.getText());
				System.out.println("Colonia: " + txtColonia.getText());
				System.out.println("C.P.: " + txtCP.getText());
				
				// Radio button Sexo
				System.out.println("Sexo: " + rbgSexo.getSelection().getActionCommand());
				
				// Check box Dispositivos
				if (chbCelular.isSelected()) {
					System.out.println("Celular: SI");
				}
				if (chbTablet.isSelected()) {
					System.out.println("Tablet: SI");
				}
				if (chbComputadora.isSelected()) {
					System.out.println("Computadora: SI ("+chbComputadora.getText()+")");
				}
				
				// Combo Box Estado civil
				System.out.println("Estado civil: " + cbEstadoCivil.getSelectedItem().toString());
				
				// DatePicker Fecha nacimiento
				System.out.println("Fecha nacimiento: " + dpFechaNac.getText());
				
				// TextArea comentarios
				System.out.println("Comentarios: " + txtComentarios.getText());
				
			}
		});
		btnGuardar.setBounds(388, 359, 117, 29);
		contentPane.add(btnGuardar);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("CP");
		lblNewLabel_1_2_1.setBounds(322, 104, 146, 16);
		contentPane.add(lblNewLabel_1_2_1);
		
		txtCP = new JTextField();
		txtCP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					rbMasculino.requestFocus();
				}
			}
		});
		txtCP.setColumns(10);
		txtCP.setBounds(322, 126, 130, 26);
		contentPane.add(txtCP);
		
		JLabel lblNewLabel_4_1 = new JLabel("Fecha nacimiento");
		lblNewLabel_4_1.setBounds(322, 208, 128, 16);
		contentPane.add(lblNewLabel_4_1);
		
		DatePickerSettings dps = new DatePickerSettings();
		dps.setFormatForDatesBeforeCommonEra("yyyy-MM-dd");
		dps.setFormatForDatesCommonEra("yyyy-MM-dd");
		
		dpFechaNac = new DatePicker(dps);
		dpFechaNac.setBounds(322, 225, 182, 26);
		dpFechaNac.setDateToToday();
		contentPane.add(dpFechaNac);
	}
}

package mx.hdsti;

/**
 * AppData.java
 * @author Haya Ramirez Angel Alejandro
 * 20181128
 * Clase para la información global del Sistema.
 */

public class AppData {
	
	// Constantes de la BD
	public static final String bd = "sistemabd";
	public static final String bduser = "sistemausr";
	public static final String bduserpassword = "sistema.051A";           
	
	// Para saber si el usuario ha iniciado sesión
	public static boolean isLoggedIn = false;
	
	// Información del usuario
	public static int id;
	public static String nombre, usuario, rol;
	
}










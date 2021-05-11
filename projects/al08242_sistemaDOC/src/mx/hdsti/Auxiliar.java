package mx.hdsti;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Auxiliar.java
 * @author Haya Ramírez Angel Alejandro
 * Clase para operaciones sobre la BD.
 */

public class Auxiliar {
	
	// Método para conectar con el servidor
	public Connection conectar(String bd, String usr, String pswd) {
		try {
			
			// Cargamos el driver y creamos la conexión
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = 
					DriverManager.getConnection(
						//"jdbc:mysql://51.51.51.21/"+bd+"?useSSL=false", usr, pswd
						"jdbc:mysql://192.168.0.17/"+bd+"?useSSL=false", usr, pswd
					);
			
			return conn;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean desconectar(Connection conexion) {
		
		try {
			// Cerramos la conexión
			conexion.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Método para iniciar sesión en el sistema
	public boolean login(String usuario, String contra, Connection conexion) {
		
		// Cadena de consulta
		String query = 
				"SELECT * FROM usuarios WHERE usuario = ? AND password = ? ";
		
		try {
			
			// Creamos un comando preparado
			PreparedStatement ps = 
					conexion.prepareStatement(query);
			
			// Agregamos los datos a la consulta
			ps.setString(1, usuario);
			ps.setString(2, contra);
			
			// Ejecutamos la consulta
			ResultSet registros = ps.executeQuery();
			
			// Verificamos si se encontró el usuario
			if (registros.first()) {
				// Si se pudo iniciar sesión
				
				// Asignamos información del usuario
				AppData.id = registros.getInt("idusuario");
				AppData.nombre = registros.getString("nombre");
				AppData.usuario = registros.getString("usuario");
				AppData.rol = registros.getString("rol");
				
				AppData.isLoggedIn = true;
				
				return true;
			} else {
				// No se pudo iniciar sesión
				return false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ResultSet getUsuarios(Connection conexion) {
		
		//Creamos la cadena para la consulta SQL
		String query = "SELECT * FROM usuarios ORDER BY nombre ASC";

		try {
			
			//Creamos los objetos para realizar la consulta a la BD
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			//Regresamos el ResultSet con los registros de la BD
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ResultSet getUsuario(int id, Connection conexion) {
		
		// Cadena de consulta
		String query = 
				"SELECT * FROM usuarios WHERE idusuario = ?";
		
		try {
			
			// Creamos un comando preparado
			PreparedStatement ps = 
					conexion.prepareStatement(query);
			
			// Agregamos los datos a la consulta
			ps.setInt(1, id);
			
			// Ejecutamos la consulta
			ResultSet registros = ps.executeQuery();
			
			return registros;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String agregarUsuario(String nombre, String usuario, String contra, String rol, Connection conexion) {
		
		//Cadena para el insert
		String query = "INSERT INTO usuarios (nombre, usuario, password, rol) values (?,?,?,?)";
		
		try {
			
			//Creamos un PreparedStatement para insertar el registro
			PreparedStatement ps = conexion.prepareStatement(query);
			
			//Agregar los parámetros
			ps.setString(1, nombre);
			ps.setString(2, usuario);
			ps.setString(3, contra);
			ps.setString(4, rol);
			
			//Ejecutar el comando SQL
			ps.executeUpdate();
			
			//Regresamos true si se insertó el usuario correctamente
			return "true";
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
		
	}
	
	public String actualizarUsuario(int id, String nombre, String usuario, String rol, Connection conexion) {
		
		//Creamos la cadena para actualizar
		String query = "UPDATE usuarios SET nombre = ?, usuario = ?, rol = ? WHERE idusuario = ?";
		
		try {
			
			//Creamos objetos para actualización
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, nombre);
			ps.setString(2, usuario);
			ps.setString(3, rol);
			ps.setInt(4, id);
			
			//Ejecutamos la actualización
			ps.executeUpdate();
			
			//Regresamos resultado positivo
			return "true";
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	public boolean cambiarContra(int id, String contra, Connection conexion) {
		
		// Convertimos la contraseña a SHA-1
		contra = toSHA1(contra);
		
		//Creamos la cadena para actualizar
		String query = "UPDATE usuarios SET password = ? WHERE idusuario = ?";
		
		try {
			
			//Creamos objetos para actualización
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, contra);
			ps.setInt(2, id);
			
			//Ejecutamos la actualización
			ps.executeUpdate(query);
			
			//Regresamos resultado positivo
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean eliminarUsuario(int id, Connection conexion) {
		
		//Creamos la cadena para la eliminación del usuario
		String query = "DELETE FROM usuarios WHERE idusuario = ?;";    
		
		try {
			
			//Creamos los objetos para la ejecución
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setInt(1, id);
			
			//Ejecutamos el comando para eliminar
			ps.executeUpdate();
			
			//Regresamos resultado exitoso
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	// Convertir String a SHA1
	public String toSHA1(String contra) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] bytes = md.digest(contra.getBytes());
			
			// Convertir a Hexadecimal
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<bytes.length; i++) {
				sb.append(
					Integer.toString(
						(bytes[i] & 0xff) + 0x100, 16
					).substring(1)
				);
			}
			
			// Regresamos la cadena encriptada
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}










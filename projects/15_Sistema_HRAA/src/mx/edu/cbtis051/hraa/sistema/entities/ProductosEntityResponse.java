package mx.edu.cbtis051.hraa.sistema.entities;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

import mx.edu.cbtis051.hraa.sistema.models.Producto;
import mx.edu.cbtis051.hraa.sistema.utils.Helper;

public class ProductosEntityResponse {

	// Atributos
	private String mensaje;
	private String fecha;
	private String autor;
	private Producto[] data;
	private long dataLong;
	private JSONArray errors;
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}
	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	/**
	 * @return the data
	 */
	public Producto[] getData() {
		return data;
	}
	/**
	 * Convert JSONArray to Producto array
	 * @param data
	 */
	public void setData(JSONArray data) {
		
		// Validamos que data no sea null
		if (data != null) {
			
			// Creamos un array del tamaño de los elementos del JSONArray
			Producto[] array = new Producto[data.length()];
			
			JSONObject jsonObject = null;
			Producto producto = null;
			
			// Iteramos el JSONArray para crear un array de Producto
			for (int i = 0; i < data.length(); i++) {
				
				try {
					
					jsonObject = data.getJSONObject(i);
					
					producto = new Producto();
					producto.setId(Helper.getLongValueFromJSONObject(jsonObject, "id"));
					producto.setNombre(Helper.getStringValueFromJSONObject(jsonObject, "nombre"));
					producto.setDescripcion(Helper.getStringValueFromJSONObject(jsonObject, "descripcion"));
					producto.setModelo(Helper.getStringValueFromJSONObject(jsonObject, "modelo"));
					producto.setMarca(Helper.getStringValueFromJSONObject(jsonObject, "marca"));
					producto.setImagen(Helper.getStringValueFromJSONObject(jsonObject, "imagen"));
					
				} catch (JSONException e) {
					e.printStackTrace();
					jsonObject = null;
					producto = null;
				}
				
				// Agregamos el producto extraido al array
				array[i] = producto;
				
			}
			
			// Asignamos el valor del atributo
			this.data = array;
			
		}
		
	}
	/**
	 * @return the dataLong
	 */
	public long getDataLong() {
		return dataLong;
	}
	/**
	 * @param dataLong the dataLong to set
	 */
	public void setDataLong(long dataLong) {
		this.dataLong = dataLong;
	}
	/**
	 * @return the errors
	 */
	public JSONArray getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(JSONArray errors) {
		this.errors = errors;
	}
	
}

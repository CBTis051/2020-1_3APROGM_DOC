package mx.edu.cbtis051.hraa.sistema.models;

public class Producto {

	// Atributos
	private long id;
	private String nombre;
	private String descripcion;
	private String modelo;
	private String marca;
	private String imagen;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	@Override
	public String toString() {
		
		StringBuilder producto = new StringBuilder();
		producto.append("Producto: \n");
		producto.append("> id: " + getId() + "\n");
		producto.append("> nombre: " + getNombre() + "\n");
		producto.append("> descripcion: " + getDescripcion() + "\n");
		producto.append("> modelo: " + getModelo() + "\n");
		producto.append("> marca: " + getMarca() + "\n");
		producto.append("> imagen: " + getImagen() + "\n");
		
		return producto.toString();
		
	}
	
}

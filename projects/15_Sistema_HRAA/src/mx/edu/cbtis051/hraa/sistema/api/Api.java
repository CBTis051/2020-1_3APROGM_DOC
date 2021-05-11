package mx.edu.cbtis051.hraa.sistema.api;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.wink.client.ClientConfig;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.ClientRuntimeException;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

import mx.edu.cbtis051.hraa.sistema.entities.ProductosEntityResponse;
import mx.edu.cbtis051.hraa.sistema.models.Producto;
import mx.edu.cbtis051.hraa.sistema.providers.ProductosEntityProvider;

public class Api {
	
	// Endpoint de la API REST
	private static final String BASE_URL = "http://test.cbtis051.edu.mx:8080/";
	private static final String PRODUCTOS = "productos";
	
	/**
	 * Get Endpoint Uri for a given resource
	 * @param resource
	 * @return endpoint uri
	 */
	private static String getEndpointUri(String resource) {
		return BASE_URL + resource;
	}
	
	/**
	 * Get resource to make a request
	 * @param provider
	 * @param uri
	 * @return resource
	 */
	private static Resource getResource(Class<?> provider, String uri) {
		
		// Creamos la aplicación de cliente
		Application clientApp = new Application() {
			@Override
			public Set<Class<?>> getClasses() {
				Set<Class<?>> classes = new HashSet<>();
				
				classes.add(provider); // Le indicamos a la aplicación que soporte el provider
			
				return classes;
			}
		};
		
		// Configuración de la aplicación cliente
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.connectTimeout(3000);
		clientConfig.readTimeout(30000);
		clientConfig.applications(clientApp);
		
		// Cliente REST
		RestClient restClient = new RestClient(clientConfig);
		
		return restClient.resource(uri);
		
	}
	
	/**
	 * Get array of products
	 * @return array of Product
	 */
	public static Producto[] getProductos() {
		
		try {
			Resource resource = getResource(
					ProductosEntityProvider.class, 
					getEndpointUri(PRODUCTOS));
			
			// Se hace el request
			ClientResponse response =
					resource.accept(MediaType.APPLICATION_JSON)
					.get();
			
			// Se extrae el contenido de la respuesta (el entity)
			ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
			
			// Se valida es estatus de la respuesta
			if (response.getStatusType() == Response.Status.OK) {
				
				// Extraemos la propiedad data con el array de productos
				return entityResponse.getData();
				
			} else {
				System.out.println(
						"Response from "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
				System.out.println(entityResponse.getMensaje());
			}
			
		} catch (ClientRuntimeException e) {
			// Se indica que hubo un error porque se agotó el tiempo de espera para la conexión
			System.out.println("getProductos() > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: tiempo de espera para la conexión agotado.");
		} catch (Exception e) {
			// Se indica que hubo un error
			System.out.println("getProductos() > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Get single product from given id
	 * @param id
	 * @return
	 */
	public static Producto getProducto(long id) {
		
		try {
			Resource resource = getResource(
					ProductosEntityProvider.class, 
					getEndpointUri(PRODUCTOS + "/" + id));
			
			// Se hace el request
			ClientResponse response =
					resource.accept(MediaType.APPLICATION_JSON)
					.get();
			
			// Se extrae el contenido de la respuesta (el entity)
			ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
			
			// Se valida es estatus de la respuesta
			if (response.getStatusType() == Response.Status.OK) {
				
				// Extraemos la propiedad data con el array de productos
				return entityResponse.getData()[0];
				
			} else {
				System.out.println(
						"Response from "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
				System.out.println(entityResponse.getMensaje());
				JOptionPane
					.showMessageDialog(null, entityResponse.getMensaje());
			}
			
		} catch (ClientRuntimeException e) {
			// Se indica que hubo un error porque se agotó el tiempo de espera para la conexión
			System.out.println("getProducto(id) > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: tiempo de espera para la conexión agotado.");
		} catch (Exception e) {
			// Se indica que hubo un error
			System.out.println("getProducto(id) > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Add product with given data
	 * @param nombre
	 * @param descripcion
	 * @param modelo
	 * @param marca
	 * @param imagen
	 * @return the id of the created product
	 */
	public static long addProducto(
			String nombre,
			String descripcion,
			String modelo,
			String marca,
			String imagen
			) {
		
		try {
			Resource resource = getResource(
					ProductosEntityProvider.class, 
					getEndpointUri(PRODUCTOS));
			
			// Se construye el objeto JSON que se enviará con el request POST
			JSONObject requestBody = new JSONObject();
			try {
				requestBody.put("nombre", nombre);
				requestBody.put("descripcion", descripcion);
				requestBody.put("modelo", modelo);
				requestBody.put("marca", marca);
				requestBody.put("imagen", imagen);
			} catch (JSONException e) {
				System.out.println("addProducto() > " + e.getMessage());
				return -1; 
			}
			
			// Se hace el request
			ClientResponse response =
					resource
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.post(requestBody.toString());
			
			// Se extrae el contenido de la respuesta (el entity)
			ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
			
			// Se valida es estatus de la respuesta
			if (response.getStatusType() == Response.Status.CREATED) {
				
				// Extraemos la propiedad data con el id del producto creado
				return entityResponse.getDataLong();
				
			} else {
				System.out.println(
						"Response from "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
				System.out.println(entityResponse.getErrors().toString());
				JOptionPane
					.showMessageDialog(null, entityResponse.getErrors().toString());
			}
			
		} catch (ClientRuntimeException e) {
			// Se indica que hubo un error porque se agotó el tiempo de espera para la conexión
			System.out.println("addProducto() > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: tiempo de espera para la conexión agotado.");
			return -1;
		} catch (Exception e) {
			// Se indica que hubo un error
			System.out.println("addProducto() > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Update product info of the given id
	 * @param nombre
	 * @param descripcion
	 * @param modelo
	 * @param marca
	 * @param imagen
	 * @param id
	 * @return Number of updated rows
	 */
	public static long updateProducto(
			String nombre,
			String descripcion,
			String modelo,
			String marca,
			String imagen,
			long id
			) {
		try {
			Resource resource = getResource(
					ProductosEntityProvider.class, 
					getEndpointUri(PRODUCTOS + "/" + id));
			
			// Se construye el objeto JSON que se enviará con el request POST
			JSONObject requestBody = new JSONObject();
			try {
				requestBody.put("nombre", nombre);
				requestBody.put("descripcion", descripcion);
				requestBody.put("modelo", modelo);
				requestBody.put("marca", marca);
				requestBody.put("imagen", imagen);
			} catch (JSONException e) {
				System.out.println("updateProducto() > " + e.getMessage());
				return -1; 
			}
			
			// Se hace el request
			ClientResponse response =
					resource
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.put(requestBody.toString());
			
			// Se extrae el contenido de la respuesta (el entity)
			ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
			
			// Se valida es estatus de la respuesta
			if (response.getStatusType() == Response.Status.OK) {
				
				// Extraemos la propiedad data con la cantidad de productos actualizados
				return entityResponse.getDataLong();
				
			} else if (response.getStatusType() == Response.Status.NOT_FOUND) {
				System.out.println(
						"Response from "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
				System.out.println(entityResponse.getMensaje());
				JOptionPane
					.showMessageDialog(null, entityResponse.getMensaje());
			} else {
				System.out.println(
						"Response from "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
				System.out.println(entityResponse.getErrors().toString());
				JOptionPane
					.showMessageDialog(null, entityResponse.getErrors().toString());
			}
			
		} catch (ClientRuntimeException e) {
			// Se indica que hubo un error porque se agotó el tiempo de espera para la conexión
			System.out.println("updateProducto() > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: tiempo de espera para la conexión agotado.");
			return -1;
		} catch (Exception e) {
			// Se indica que hubo un error
			System.out.println("updateProducto() > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Delete a product with given id
	 * @param id
	 * @return Number of deleted rows
	 */
	public static long deleteProducto(long id) {
		try {
			Resource resource = getResource(
					ProductosEntityProvider.class, 
					getEndpointUri(PRODUCTOS + "/" + id));
			
			// Se hace el request
			ClientResponse response =
					resource
					.accept(MediaType.APPLICATION_JSON)
					.delete();
			
			// Se extrae el contenido de la respuesta (el entity)
			ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
			
			// Se valida es estatus de la respuesta
			if (response.getStatusType() == Response.Status.OK) {
				
				// Extraemos la propiedad data con la cantidad de productos eliminados
				return entityResponse.getDataLong();
				
			} else if (response.getStatusType() == Response.Status.NOT_FOUND) {
				System.out.println(
						"Response from "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
				System.out.println(entityResponse.getMensaje());
				JOptionPane
					.showMessageDialog(null, entityResponse.getMensaje());
			} else {
				System.out.println(
						"Response from "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
				System.out.println(entityResponse.getErrors().toString());
				JOptionPane
					.showMessageDialog(null, entityResponse.getErrors().toString());
			}
			
		} catch (ClientRuntimeException e) {
			// Se indica que hubo un error porque se agotó el tiempo de espera para la conexión
			System.out.println("deleteProducto() > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: tiempo de espera para la conexión agotado.");
			return -1;
		} catch (Exception e) {
			// Se indica que hubo un error
			System.out.println("deleteProducto() > " + e.getMessage());
			JOptionPane
				.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
			return -1;
		}
		
		return 0;
	}

}
















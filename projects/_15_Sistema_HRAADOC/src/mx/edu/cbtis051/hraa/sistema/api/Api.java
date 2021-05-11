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
	
	// Endpoints
	private static final String BASE_URL = "http://test.cbtis051.edu.mx:8080/";
	private static final String PRODUCTOS = "productos";
	
	/**
	 * Get Endpoint Uri for a given resource 
	 * @param resource
	 * @return
	 */
	private static String getEndpointUri(String resource) {
		return BASE_URL + resource;
	}
	
	/**
	 * Get resource to make a request
	 * @param provider
	 * @param uri
	 * @return
	 */
	private static Resource getResource(Class<?> provider, String uri) {
		
		Application clientApp = new Application() {
			
			@Override
			public Set<Class<?>> getClasses() {
				Set<Class<?>> classes = new HashSet<>();
								
				classes.add(provider);
				
				return classes;
			}
			
		};
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.connectTimeout(30000);
		clientConfig.readTimeout(30000);
		clientConfig.applications(clientApp);
		
		RestClient restClient = new RestClient(clientConfig);			
		
		return restClient.resource(uri);
		
	}
	
	/**
	 * Get array of products
	 * @return Producto[] array of Product
	 */
	public static Producto[] getProductos() {
		
		Resource resource = getResource(
				ProductosEntityProvider.class, 
				getEndpointUri(PRODUCTOS));
		
		// Se hace la petición																	
		ClientResponse response = 
				resource.accept(MediaType.APPLICATION_JSON)
				.get();
		
		ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
		
		// Se valida el estatus de la respuesta
		if (response.getStatusCode() == 200) {
			return entityResponse.getData();
		} else {
			System.out.println("Respuesta de "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
			System.out.println(entityResponse.getMensaje());
		}
		
		return null;
		
	}
	
	/**
	 * Get single product from given id
	 * @param id
	 * @return Producto
	 */
	public static Producto getProducto(long id) {
		
		Resource resource = getResource(
				ProductosEntityProvider.class, 
				getEndpointUri(PRODUCTOS+"/"+id));
		
		// Se hace la petición																	
		ClientResponse response = 
				resource.accept(MediaType.APPLICATION_JSON)
				.get();
		
		ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
		
		// Se valida el estatus de la respuesta
		if (response.getStatusCode() == 200) {
			return entityResponse.getData()[0];
		} else {
			System.out.println("Respuesta de "+getEndpointUri(PRODUCTOS)+" ("+response.getStatusCode()+"):");
			System.out.println(entityResponse.getMensaje());
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
	 * @return affected rows count
	 */
	public static long updateProducto(
			String nombre,
			String descripcion,
			String modelo,
			String marca,
			String imagen,
			long id
			) {
		
		Resource resource = getResource(
				ProductosEntityProvider.class, 
				getEndpointUri(PRODUCTOS+"/"+id));
		
		// Se construye el objeto JSON que se enviará con el request
		JSONObject requestBody = new JSONObject();
		try {
			requestBody.put("nombre", nombre);
			requestBody.put("descripcion", descripcion);
			requestBody.put("modelo", modelo);
			requestBody.put("marca", marca);
			requestBody.put("imagen", imagen);
		} catch (JSONException e) {
			e.printStackTrace();
			return -1;
		}
		
		// Se hace la petición																	
		ClientResponse response = 
				resource
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.put(requestBody.toString());
		
		ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
		
		// Se valida el estatus de la respuesta
		if (response.getStatusType() == Response.Status.OK) {
			return entityResponse.getDataLong();
		} else if (response.getStatusType() == Response.Status.NOT_FOUND) {
			System.out.println("Respuesta de "+getEndpointUri(PRODUCTOS+"/"+id)+" ("+response.getStatusCode()+"):");
			System.out.println(entityResponse.getMensaje());
		} else {
			System.out.println("Respuesta de "+getEndpointUri(PRODUCTOS+"/"+id)+" ("+response.getStatusCode()+"):");
			System.out.println(entityResponse.getErrors().toString());
		}
		
		return 0;
	}
	
	public static long deleteProducto(long id) {
		Resource resource = getResource(
				ProductosEntityProvider.class, 
				getEndpointUri(PRODUCTOS+"/"+id));
		
		// Se hace la petición																	
		ClientResponse response = 
				resource.accept(MediaType.APPLICATION_JSON)
				.delete();
		
		ProductosEntityResponse entityResponse = response.getEntity(ProductosEntityResponse.class);
		
		// Se valida el estatus de la respuesta
		if (response.getStatusType() == Response.Status.OK) {
			return entityResponse.getDataLong();
		} else if (response.getStatusType() == Response.Status.NOT_FOUND) {
			System.out.println("Respuesta de "+getEndpointUri(PRODUCTOS+"/"+id)+" ("+response.getStatusCode()+"):");
			System.out.println(entityResponse.getMensaje());
		} else {
			System.out.println("Respuesta de "+getEndpointUri(PRODUCTOS+"/"+id)+" ("+response.getStatusCode()+"):");
			System.out.println(entityResponse.getErrors().toString());
		}
		
		return 0;
	}
	
}

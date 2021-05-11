package mx.edu.cbtis051.hraa.sistema.providers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.apache.wink.common.utils.ProviderUtils;
import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

import mx.edu.cbtis051.hraa.sistema.entities.ProductosEntityResponse;
import mx.edu.cbtis051.hraa.sistema.utils.Helper;

@Provider
public class ProductosEntityProvider implements MessageBodyReader<ProductosEntityResponse> {

	@Override
	public boolean isReadable(
			Class<?> type, Type genericType, 
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public ProductosEntityResponse readFrom(
			Class<ProductosEntityResponse> type, Type genericType, 
			Annotation[] annotations, MediaType mediaType, 
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		ProductosEntityResponse response = null;
		
		byte[] bytes = ProviderUtils.readFromStreamAsBytes(entityStream);
		String bytesString = new String(bytes, ProviderUtils.getCharset(mediaType));
		
		try {
			// Se obtiene la respuesta del servicio
			JSONObject jsonResponse = new JSONObject(bytesString);
			
			response = new ProductosEntityResponse();
			
			// Se valida si se recibieron errores
			if (jsonResponse.containsKey("errors")) {
				
				// Si hubo errores se obtienen
				response.setErrors(Helper.getJSONArrayFromJSONObject(jsonResponse, "errors"));
				
			} else {
				// Si no hubo errores
				response.setMensaje(Helper.getStringValueFromJSONObject(jsonResponse, "mensaje"));
				response.setFecha(Helper.getStringValueFromJSONObject(jsonResponse, "fecha"));
				response.setAutor(Helper.getStringValueFromJSONObject(jsonResponse, "autor"));
				
				// Procesamos el elemento data
				JSONArray dataJSON = Helper.getJSONArrayFromJSONObject(jsonResponse, "data");
				
				if (dataJSON != null) {
					response.setData(dataJSON);
				} else {
					response.setDataLong(Helper.getLongValueFromJSONObject(jsonResponse, "data"));
				}
			}
			
			
		} catch (JSONException e) {
			// Si no se pudo obtener la información de la respuesta debido a un error JSON
			e.printStackTrace();
			response = null;
		}
		
		return response;
	}

}

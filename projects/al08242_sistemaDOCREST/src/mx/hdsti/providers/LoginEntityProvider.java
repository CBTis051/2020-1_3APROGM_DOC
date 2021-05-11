package mx.hdsti.providers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.apache.wink.common.utils.ProviderUtils;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

import mx.hdsti.entities.LoginEntityResponse;

@Provider
public class LoginEntityProvider implements MessageBodyReader<LoginEntityResponse>{

	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public LoginEntityResponse readFrom(Class<LoginEntityResponse> arg0, Type arg1, Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream arg5) throws IOException {
		
		LoginEntityResponse response = null;
		 
        byte[] bytes = ProviderUtils.readFromStreamAsBytes(arg5);
        String bytesString = new String(bytes, ProviderUtils.getCharset(arg3));
 
        try {
 
            // Se obtiene la respuesta del servicio
            JSONObject jsonResponse =  new JSONObject(bytesString);
 
            String ok = getStringValueFromJSONObject(jsonResponse, "ok");
 
            if (ok != null && ok.equals("true")) {
 
            	// Si la respuesta fue true, se obtiene el token
            	String token = jsonResponse.getString("token");
            	
                response = new LoginEntityResponse();
                response.setOk(ok);
                response.setToken(token);
 
            } else if (ok != null && ok.equals("false")) {
            	
            	// Si la respuesta fue false, se obtiene el error
            	JSONObject err = getJSONObjectFromJSONObject(jsonResponse, "err");
            	
                response = new LoginEntityResponse();
                response.setOk(ok);
                response.setResultMessage(err.getString("message"));
 
            }
 
        } catch (JSONException e) {
            e.printStackTrace();
            response = null;
        }
 
        return response;
	}
	
	protected String getStringValueFromJSONObject(JSONObject jsonObject, String elementName) {
		 
        String value = null;
 
        try {
 
            if (jsonObject != null && elementName != null
                    && jsonObject.has(elementName)) {
                value = jsonObject.getString(elementName);
            }
 
        } catch (JSONException e) {
            e.printStackTrace();
            value = null;
        }
 
        return value;
 
    }
	
	protected JSONObject getJSONObjectFromJSONObject(JSONObject jsonObject, String elementName) {
		 
        JSONObject value = null;
 
        try {
 
            if (jsonObject != null && elementName != null
                    && jsonObject.has(elementName)) {
                value = jsonObject.getJSONObject(elementName);
            }
 
        } catch (JSONException e) {
            e.printStackTrace();
            value = null;
        }
 
        return value;
 
    }
	
}
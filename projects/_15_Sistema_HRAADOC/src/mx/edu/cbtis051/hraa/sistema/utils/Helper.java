package mx.edu.cbtis051.hraa.sistema.utils;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

public class Helper {
	
	/**
	 * Get a String value from a given JSONObject
	 * @param jsonObject
	 * @param elementName
	 * @return
	 */
	public static String getStringValueFromJSONObject(JSONObject jsonObject, String elementName) {
		 
        String value = null;
 
        try {
 
            if (jsonObject != null && 
            		elementName != null && 
            		jsonObject.has(elementName)) {
            	
                value = !jsonObject.isNull(elementName) ?
                		jsonObject.getString(elementName):
                			null;
            }
 
        } catch (JSONException e) {
            e.printStackTrace();
            value = null;
        }
 
        return value;
 
    }

	/**
	 * Get a long value from a given JSONObject
	 * @param jsonObject
	 * @param elementName
	 * @return
	 */
	public static long getLongValueFromJSONObject(JSONObject jsonObject, String elementName) {
		 
        long value = 0;
 
        try {
 
            if (jsonObject != null && 
            		elementName != null && 
            		jsonObject.has(elementName)) {
            	
                value = jsonObject.getLong(elementName);
            }
 
        } catch (JSONException e) {
            e.printStackTrace();
            value = 0;
        }
 
        return value;
 
    }
	
	/**
	 * Get a JSONArray from a given JSONObject
	 * @param jsonObject
	 * @param elementName
	 * @return
	 */
	public static JSONArray getJSONArrayFromJSONObject(JSONObject jsonObject, String elementName) {
		
		JSONArray value = null;
		
		try {
			if (jsonObject != null &&
					elementName != null &&
					jsonObject.has(elementName)) {
				value = jsonObject.optJSONArray(elementName);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			value = null;
		}
		
		return value;
		
	}
	
}

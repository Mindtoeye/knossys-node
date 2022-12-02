package com.knossys.rnd.net;

import java.io.StringReader;
import java.util.Map.Entry;

import javax.json.JsonValue;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

/**
 * @author vvelsen
 */
public class JSONTools {
	
	/**
	 * 
	 */
	public static JsonObject parseJSON(String aMessage) {
		JsonReader jsonReader = Json.createReader(new StringReader(aMessage));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		return (jsonObject);
	}

	/**
	 * 
	 */
	public static JsonArray parseJSONArray(String aMessage) {
		JsonReader jsonReader = Json.createReader(new StringReader(aMessage));
		JsonArray jArray = jsonReader.readArray();
		jsonReader.close();
		return (jArray);
	}
	
	/**
	 * @param existingObject
	 * @return
	 */
	public static JsonObjectBuilder jsonObjectToBuilder(JsonObject existingObject) {
    JsonObjectBuilder job = Json.createObjectBuilder();

		for (Entry<String, JsonValue> entry : existingObject.entrySet()) {
        job.add(entry.getKey(), entry.getValue());
    }

    return job;
  }	
}

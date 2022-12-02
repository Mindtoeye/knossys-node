package com.knossys.rnd.net;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.apache.log4j.Logger;

/** 
 * @author vvelsen
 */
public class KMessage extends JSONWrapper  {
  
  private static Logger M_log = Logger.getLogger(KMessage.class);
  
  public String id=StringTools.generateStringUUID ();
  
  /**
   * @param anObject
   */
  public void fromJSON(JsonObject refObject) {
    M_log.info("fromJSON ()");
   
    id=this.getString(refObject,"id");      
  }    
  
  /**
   * @return
   */
  public JsonObject getJSONObject () {
    JsonObjectBuilder builder=Json.createObjectBuilder();
    
    builder.add("id", ""+id);
    
    return (builder.build());
  }
  
  /**
   * @return
   */
  public JsonObject getJSONObjectSummary () {   
    return (getJSONObject());
  }  
  
  /**
   * @return
   */
  public String getJSONString () {
    JsonObject built=getJSONObject();
    return (built.toString());
  }     
}

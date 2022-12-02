package com.knossys.rnd.net;

import java.util.Date;
import java.util.logging.Logger;

import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * @author vvelsen
 */
public class JSONWrapper {
	
	private static Logger M_log = Logger.getLogger(JSONWrapper.class.getName());
	
	private JsonObject referenceObject=null;
	private String referenceString="";
	
	/**
	 * @return
	 */
	public JsonObject getReferenceObject() {
		return referenceObject;
	}

	/**
	 * @param referenceObject
	 */
	public void setReferenceObject(JsonObject referenceObject) {
		this.referenceObject = referenceObject;
	}	
	
	/**
	 * @return
	 */
	public String getReferenceString() {
		return referenceString;
	}

	/**
	 * @param referenceString
	 */
	public void setReferenceString(String referenceString) {
		this.referenceString = referenceString;
	}	

	/**
	 * @param aStockObject
	 * @return
	 */
	public JsonArray getArray(JsonObject aStockObject, String aName) {
		// M_log.info("getArray ("+aName+"): " + JSONTools.displayType(aStockObject));

		if (aStockObject==null) {
			return (null);
		}
		
		if (aStockObject.containsKey(aName)==false) {
			return (null);
		}
		
		if (aStockObject.isNull(aName)==true) {
			return (null);
		}		
		
		if (aStockObject.get(aName) == null) {
			M_log.info("Error: name " + aName + " does not exist ("+aName+"): " + aStockObject.toString());
			return (null);
		}

		JsonArray result=null;
		
		try {
		  result = aStockObject.getJsonArray(aName);
		} catch (Exception e){
			//M_log.info("Something still went wrong retrieving "+aName+": " + e.getMessage());
			return (null);
		}

		if (result != null) {
			return result;
		}

		return null;
	}	
	
	/**
	 * @param aStockObject
	 * @return
	 */
	public String getString(JsonObject aStockObject, String aName) {
		// M_log.info("getString ("+aName+"): " + JSONTools.displayType(aStockObject));

		if (aStockObject==null) {
			return ("");
		}
		
		if (aStockObject.containsKey(aName)==false) {
			return ("");
		}
		
		if (aStockObject.isNull(aName)==true) {
			return ("");
		}		
		
		if (aStockObject.get(aName) == null) {
			M_log.info("Error: name " + aName + " does not exist ("+aName+"): " + aStockObject.toString());
			return ("");
		}

		String result="";
		
		try {
		  result = aStockObject.getString(aName);
		} catch (Exception e){
			//M_log.info("Something still went wrong retrieving "+aName+": " + e.getMessage());
			return ("");
		}

		if (result != null) {
			return result;
		}

		return "";
	}

	/**
	 * @param aStockObject
	 * @return
	 */
	public Boolean getBoolean (JsonObject aStockObject, String aName) {
		// M_log.info("getBoolean ("+aName+"): " + JSONTools.displayType(aStockObject));

		if (aStockObject==null) {
			return (false);
		}
		
		if (aStockObject.containsKey(aName)==false) {
			return (false);
		}
		
		if (aStockObject.isNull(aName)==true) {
			return (false);
		}		
		
		if (aStockObject.get(aName) == null) {
			M_log.info("Error: name " + aName + " does not exist: " + aStockObject.toString());
			return (false);
		}

		Boolean result = aStockObject.getBoolean(aName);

		return result;
	}	
	
	/**
	 * @param aStockObject
	 * @return
	 */
	public Integer getInteger(JsonObject aStockObject, String aName) {
		// M_log.info("getInteger ("+aName+"): " + JSONTools.displayType(aStockObject));

		if (aStockObject==null) {
			return (0);
		}
		
		if (aStockObject.containsKey(aName)==false) {
			return (0);
		}
		
		if (aStockObject.isNull(aName)==true) {
			return (0);
		}
		
		if (aStockObject.get(aName) == null) {
			M_log.info("Error: name " + aName + " does not exist: " + aStockObject.toString());
			return (0);
		}

		try {
			if (aStockObject.getJsonNumber(aName) == null) {
				return (0);
			}
		} catch (Exception e) {
			M_log.info("Error extracting number from JSON object ("+aName+"): " + aStockObject.toString());
			return (0);
		}

		Integer testValue = aStockObject.getInt(aName);

		return testValue;
	}	
	
	/**
	 * @param aStockObject
	 * @return
	 */
	public Long getLong(JsonObject aStockObject, String aName) {
		// M_log.info("getLong ("+aName+"): " + JSONTools.displayType(aStockObject));

		if (aStockObject==null) {
			return (0L);
		}
		
		if (aStockObject.containsKey(aName)==false) {
			return (0L);
		}
		
		if (aStockObject.isNull(aName)==true) {
			return (0L);
		}
		
		if (aStockObject.get(aName) == null) {
			M_log.info("Error: name " + aName + " does not exist: " + aStockObject.toString());
			return (0L);
		}

		try {
			if (aStockObject.getJsonNumber(aName) == null) {
				return (0L);
			}
		} catch (Exception e) {
			M_log.info("Error extracting number from JSON object ("+aName+"): " + aStockObject.toString());
			return (0L);
		}

		Long testValue = aStockObject.getJsonNumber(aName).longValue();

		return testValue;
	}

	/**
	 * @param aStockObject
	 * @return
	 */
	public Double getDouble(JsonObject aStockObject, String aName) {
		// M_log.info("getDouble ("+aName+"): " + JSONTools.displayType(aStockObject));

		if (aStockObject==null) {
			return (0D);
		}
		
		if (aStockObject.containsKey(aName)==false) {
			return (0D);
		}
		
		if (aStockObject.isNull(aName)==true) {
			return (0D);
		}
		
		if (aStockObject.get(aName) == null) {
			M_log.info("Error: name " + aName + " does not exist: " + aStockObject.toString());
			return (0D);
		}

		try {
			if (aStockObject.getJsonNumber(aName) == null) {
				return (0D);
			}
		} catch (Exception e) {
			M_log.info("Error extracting number from JSON object ("+aName+"): " + aStockObject.toString());
			return (0D);
		}

		Double testValue = aStockObject.getJsonNumber(aName).doubleValue();

		return testValue;
	}

	/**
	 * @param aStockObject
	 * @return
	 */
	public Date getDate(JsonObject aStockObject, String aName) {
		// M_log.info("getDate ("+aName+"): " + JSONTools.displayType(aStockObject));
		
		if (aStockObject==null) {
			return (new Date());
		}
		
		if (aStockObject.containsKey(aName)==false) {
			return (new Date ());
		}
		
		if (aStockObject.isNull(aName)==true) {
			return (new Date());
		}

		if (aStockObject.get(aName) == null) {
			M_log.info("Error: name " + aName + " does not exist: " + aStockObject.toString());
			return (new Date());
		}

		return (new Date());
	}
	
  /**
   * @param aStockObject
   * @return
   */
  public JsonObject getJsonObject(JsonObject aStockObject, String aName) {
    // M_log.info("getJsonObject ("+aName+"): " + JSONTools.displayType(aStockObject));
    
    if (aStockObject==null) {
      return (null);
    }
    
    if (aStockObject.containsKey(aName)==false) {
      return (null);
    }
    
    if (aStockObject.isNull(aName)==true) {
      return (null);
    }
    
    JsonObject result=(JsonObject) aStockObject.get(aName);

    return (result);
  }	
}

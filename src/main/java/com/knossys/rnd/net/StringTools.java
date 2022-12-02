package com.knossys.rnd.net;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 */
public class StringTools {

	public static MessageDigest digest=null;
	
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	
	/**
	 * 
	 */
	public StringTools () {
		try {
			StringTools.digest=MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 * @param s
	 * @return
	 */
  public static String removeLastChar(String s) {
    return (s == null || s.length() == 0)
      ? null 
      : (s.substring(0, s.length() - 1));
  }	
	
  /**
   * 
   */ 	
	public static boolean isEmptyString(String string) {
    return string == null || string.isEmpty();
  }
	
  /**
   * 
   */ 	
	public static boolean isBlankString(String string) {
    return string == null || string.trim().isEmpty();
  }	
	
  /**
   * 
   */ 
  public static String string2StringParameter (String aParameter, String aValue) {    
    return (aParameter + "=" + aValue);
  }	
	
  /**
   * 
   */	
	public static String boolean2StringParameter (String aParameter, Boolean aValue) {
	  if (aValue==true) {
	    return (aParameter + "=true");
	  }
	  
	  return (aParameter + "=false");
	}
	
	/**
	 * 
	 */
	public static Boolean string2Boolean (String aString) {
		
		if (aString.isEmpty()==true) {
			return (false);
		}
		
		if ((aString.equalsIgnoreCase("true")==true) || (aString.equalsIgnoreCase("yes")==true) || (aString.equalsIgnoreCase("ok")==true)) {
			return (true);
		}
		
		return (false);
	}
	
	/**
	 * 
	 * @return
	 */
	public static String generateStringUUID() {
		UUID id = UUID.randomUUID();

		return (id.toString());
	}

	/**
	 * 
	 * @param aString
	 * @return
	 */
	public static String trimDoubleQuotes(String aString) {
		return (aString.replaceAll("^\"|\"$", ""));
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String quote(String string) {
		if (string == null || string.length() == 0) {
			return "\"\"";
		}

		char c = 0;
		int i;
		int len = string.length();
		StringBuilder sb = new StringBuilder(len + 4);
		String t;

		sb.append('"');
		for (i = 0; i < len; i += 1) {
			c = string.charAt(i);
			switch (c) {
			case '\\':
			case '"':
				sb.append('\\');
				sb.append(c);
				break;
			case '/':
				// if (b == '<') {
				sb.append('\\');
				// }
				sb.append(c);
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\r':
				sb.append("\\r");
				break;
			default:
				if (c < ' ') {
					t = "000" + Integer.toHexString(c);
					sb.append("\\u" + t.substring(t.length() - 4));
				} else {
					sb.append(c);
				}
			}
		}
		sb.append('"');
		return sb.toString();
	}

	/**
	 * 
	 * @param text
	 * @param length
	 * @return
	 */
	public static String leftpad(String text, int length) {
		return String.format("%" + length + "." + length + "s", text);
	}

	/**
	 * 
	 */
	public static String rightpad(String text, int length) {
		return String.format("%-" + length + "." + length + "s", text);
	}
	
  /**
   * 
   * @param aList
   * @return
   */
  public static String hashtableKeysToString(Hashtable<String,String> aList, String aSeparator) {
    StringBuffer formatter = new StringBuffer();

    Set<String> keys = aList.keySet();
    int index=0;
    for(String key: keys){
      if (index > 0) {
        formatter.append(aSeparator + " ");
      }

      formatter.append(key);
      
      index++;
    }

    return (formatter.toString());
  }	
  
  /**
   * 
   * @param aList
   * @return
   */
  public static String hashmapKeysToString(HashMap<String,String> aList, String aSeparator) {
    StringBuffer formatter = new StringBuffer();

    Set<String> keys = aList.keySet();
    int index=0;
    for(String key: keys){
      if (index > 0) {
        formatter.append(aSeparator + " ");
      }

      formatter.append(key);
      
      index++;
    }

    return (formatter.toString());
  }   

	/**
	 * 
	 * @param aList
	 * @return
	 */
	public static String arraylistToString(ArrayList<String> aList, String aSeparator) {
		StringBuffer formatter = new StringBuffer();

		for (int i = 0; i < aList.size(); i++) {
			if (i > 0) {
				formatter.append(aSeparator + " ");
			}

			formatter.append(aList.get(i));
		}

		return (formatter.toString());
	}
	
	/**
	 * 
	 * @param aList
	 * @return
	 */
	public static String arraylistToStringQuoted(ArrayList<String> aList, String aSeparator) {
		StringBuffer formatter = new StringBuffer();

		for (int i = 0; i < aList.size(); i++) {
			if (i > 0) {
				formatter.append(aSeparator);
			}

			formatter.append("'"+aList.get(i)+"'");
		}

		return (formatter.toString());
	}	
	
	/**
	 * @param aString
	 * @return
	 */
	public static String stringEncode (String aString) {
		byte[] bytes;
		try {
			bytes = aString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return (null);
		}
		String encoded = Base64.getEncoder().encodeToString(bytes);
		return (encoded);
	}

	 /**
   * @param aString
   * @return
   */
  public static String bytesEncode (byte[] bytes) {
    String encoded = Base64.getEncoder().encodeToString(bytes);
    return (encoded);
  }
	
	/**
	 * @param aString
	 * @return
	 */
	public static String stringDecode (String aString) {
		byte[] decoded = Base64.getDecoder().decode(aString);
		return (new String (decoded));
	}	
	
	/**
	 * 
	 */
	public static String generateString() {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		
		return (generatedString);
	}
	
	/**
	 * 
	 */
	public static String generateString(int aLength) {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = aLength;
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		
		return (generatedString);
	}	
	
	/**
	 * @param commentstr
	 * @return
	 */
  public static String removeUrl(String commentstr) {
    String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
    Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(commentstr);
    int i = 0;
    while (m.find()) {
      commentstr = commentstr.replaceAll(m.group(i),"").trim();
      i++;
    }
    return commentstr;
  }
  
  /**
   * @param string
   * @return
   */
	public static URL convertToURLEscapingIllegalCharacters(String string){
    try {
        String decodedURL = URLDecoder.decode(string, "UTF-8");
        URL url = new URL(decodedURL);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()); 
        return uri.toURL(); 
    } catch (Exception ex) {
        ex.printStackTrace();
        return null;
    }
}  
  
  /**
   * @param url
   * @return
   * @throws UnsupportedEncodingException
   */
  public static Map<String, List<String>> splitQuery(URL url) throws UnsupportedEncodingException {
    final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
    final String[] pairs = url.getQuery().split("&");
    for (String pair : pairs) {
      final int idx = pair.indexOf("=");
      final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
      if (!query_pairs.containsKey(key)) {
        query_pairs.put(key, new LinkedList<String>());
      }
      final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
      query_pairs.get(key).add(value);
    }
    return query_pairs;
  }  
  
  /**
   * @param input
   * @return
   */
  public static String applySha256(String input){		
		try {	        
			//Applies sha256 to our input, 
			byte[] hash = StringTools.digest.digest(input.getBytes("UTF-8"));	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
  }
  
  /**
   * 
   * @param in
   * @return
   */
  public static String inputstreamToString (InputStream in) {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = in;
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
	    //
			return null;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					//
					return null;
				}
			}
		}

		body = stringBuilder.toString();
		
		return body;
  }
  
  /**
   * @param aName
   * @return
   */
  public static String makeFilenameDBSafe (String aName) {
  	if (aName==null) {
  	  return "";
  	}
  	return (aName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_"));
  }
  
	/**
	 * @param bytes
	 * @return
	 */
	public String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	/**
	 * https://stackoverflow.com/questions/7124735/hmac-sha256-algorithm-for-signature-calculation
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String encode(String key, String data) throws Exception {
	  Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	  SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
	  sha256_HMAC.init(secret_key);
	  
	  //sha256_HMAC.doFinal(data.getBytes("UTF-8"));

	  //bytesToHex(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
	  
	  return (Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
	}
	
	/**
	 * 
	 * @return
	 */
	public String getServerTime() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return dateFormat.format(calendar.getTime());
  }
	
	/**
	 * @param aString
	 * @return
	 */
	public String removeNewlines (String aString) {
		return (aString.replace("\n", " ").replace("\r", " "));
	}
	
	/**
	 * @param aString
	 * @return
	 */
	public static String makeCSVSafe (String aString) {
		return (aString.replace(",", "_"));
	}	
	
	/**
	 * @param anEmail
	 * @return
	 */
	public static String getNameFromEmail (String anEmail) {
		String [] splitter = anEmail.split("@");
		if (splitter.length>0) {
			return (splitter [0]);
		}
		
		return (anEmail);
	}
}

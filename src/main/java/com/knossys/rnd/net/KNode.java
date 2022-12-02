package com.knossys.rnd.net;

import org.apache.log4j.Logger;

/**
 * 
 */
public class KNode {

  private static Logger M_log = Logger.getLogger(KNode.class);
    
  /**
   *  
   */
  private void usage () {
    M_log.info("usage()");
    
  }
  
  /**
   *  
   */
  public Boolean init (String[] args) {
    M_log.info("init()");
        
    if (args.length < 1) {
      usage();
      return (false);
    }
        
    return (true);
  }
  
  /**
   * 
   */
  public void run () {
    
  }
  
  /** 
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) {
    KNode connector=new KNode();
    if (connector.init(args)==true) {
      connector.run();
    }
  }
}

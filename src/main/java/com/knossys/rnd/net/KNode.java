package com.knossys.rnd.net;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import javax.json.JsonObject;


/**
 * https://www.rabbitmq.com/tutorials/tutorial-one-java.html
 */
public class KNode implements DeliverCallback {

  private static Logger M_log = Logger.getLogger(KNode.class);
  private final static String QUEUE_NAME = "knossys";
  private JSONTools jTools=new JSONTools ();
    
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
  @Override
  public void handle(String consumerTag, Delivery delivery) throws IOException {
    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
    System.out.println(" [x] Received '" + message + "'");   
    
    JsonObject incomingObject=JSONTools.parseJSON(message);
    KMessage incomingMessage=new KMessage ();
    incomingMessage.fromJSON(incomingObject);
  }  
  
  /**
   * @throws TimeoutException 
   * @throws IOException 
   * 
   */
  public Boolean boot () {
    M_log.info("boot()");
    
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("krabbitmq");
    
    Channel channel = null;
    
    try {
      Connection connection = factory.newConnection();
      channel = connection.createChannel();
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      String message = new KMessage ().getJSONString();
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
      System.out.println(" [x] Sent '" + message + "'");
    } catch (Exception e) {
      M_log.info("Error: " + e.getMessage());
      return (false);
    }
    
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    
    try {
      channel.basicConsume(QUEUE_NAME, true, this, consumerTag -> { });
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return (false);
    }    
    
    return (true);
  }
  
  /** 
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) {
    KNode connector=new KNode();
    if (connector.init(args)==true) {
      while (connector.boot ()==false) {
        M_log.info("Unable to connect, waiting 4 seconds ...");
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          return;
        }
      }
    }
  }
}

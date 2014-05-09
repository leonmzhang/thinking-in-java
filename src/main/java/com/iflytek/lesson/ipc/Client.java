package com.iflytek.lesson.ipc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import javax.net.SocketFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

public class Client {
  private static final Log LOG = LogFactory.getLog(Client.class);
  
  private Socket socket = null;
  
  // protected final SocketFactory socketFactory;
  
  public Client() throws IOException {
    socket = SocketChannel.open().socket();
    socket.connect(new InetSocketAddress("192.168.20.104", 10000));
    socket.getChannel().write(ByteBuffer.allocate(1024));
    socket.getChannel().close();
  }
  
  public class Connection extends Thread {
    
  }
  
  public void call() {
    
  }
  
  public static void main(String[] args) {
    PropertyConfigurator.configure("conf/log4j.properties");
    
    LOG.info("invoke a call.");
    Client client;
    try {
      client = new Client();
      client.call();
    } catch (IOException e) {
      LOG.fatal("", e);
    }
    
  }
}

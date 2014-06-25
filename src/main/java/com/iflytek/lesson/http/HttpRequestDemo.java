package com.iflytek.lesson.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class HttpRequestDemo {
  
  public static final String HOST = "openapi.openspeech.cn";
  public static final int port = 80;
  
  public static final String TEST_HOST = "60.166.12.151";
  public static final int TEST_PORT = 9110;
  public static final String URL = "/webapi/index.htm";
  
  public static void iatDemoMethod() {
    PostMethod post = new PostMethod(
        "/webapi/index.htm?svc=iat&token=bbdaf8e705b6467947c4d939b2db7d6a&auf=audio/L16;rate=8000&aue=amr&ent=sms8k");
    //"/index.htm?svc=iat&token=bbdaf8e705b6467947c4d939b2db7d6a&auf=audio/L16;rate=8000&aue=amr&ent=sms8k");
    
    File armFile = new File("demo.amr");
    int fileLength = (int) armFile.length();
    byte[] value = new byte[fileLength];
    
    FileInputStream fis = null;
    
    try {
      fis = new FileInputStream(new File("demo.amr"));
      int len = fis.read(value);
      fis.close();
      
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    HttpClient client = new HttpClient();
    client.getHostConfiguration().setHost(TEST_HOST, TEST_PORT);
    //client.getHostConfiguration().setHost(HOST, port);
    
    BASE64Encoder encoder = new BASE64Encoder();
    String amrBase64Str = encoder.encode(value);
    
    // Header header = new Header();
    // header.setName("Content-Type");
    // header.setValue("binary");
    // header.setName("Content-Length");
    // header.setValue(Integer.toString(amrBase64Str.length()));
    post.setRequestContentLength(amrBase64Str.length());
    post.setRequestHeader("Content-Type", "binary");
//    post.setRequestHeader("Content-Length",
//        Integer.toString(amrBase64Str.length()));
    // post.setRequestHeader(header);
    //post.setRequestBody(amrBase64Str);
    NameValuePair nvp = new NameValuePair();
    //nvp.setName("body");
    nvp.setValue(amrBase64Str);
    post.setRequestBody(new NameValuePair[]{nvp});
    
    //System.out.println(post.getReq));
    System.out.println(post.getRequestHeaders()[0]);
    //System.out.println(post.getRequestHeaders()[1]);
    
    try {
      client.executeMethod(post);
      System.out.println(post.getStatusLine().toString());
      System.out.println(post.getResponseBodyAsString());
      String bodyStr = post.getResponseBodyAsString();
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] responseValue = decoder.decodeBuffer(bodyStr);
      System.out.print(new String(responseValue));
    } catch (HttpException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    iatDemoMethod();
    System.exit(0);
    
    String xPar = "appid=123456&imei=xxxxx&imsi=xxxx&mac=xxxx&";
    BASE64Encoder encoder = new BASE64Encoder();
    String xParBase64 = encoder.encode(xPar.getBytes());
    System.out.println(xParBase64);
    
    HttpClient client = new HttpClient();
    client.getHostConfiguration().setHost("openapi.openspeech.cn", 80);
    GetMethod get = new GetMethod("/index.htm?svc=token");
    Header header = new Header();
    header.setName("X-Par");
    header.setValue(xParBase64);
    get.setRequestHeader(header);
    System.out.println(get.getRequestHeaders()[0].toString());
    
    try {
      client.executeMethod(get);
      System.out.println(get.getStatusLine().toString());
      System.out.println(get.getResponseBodyAsString());
      String bodyStr = get.getResponseBodyAsString();
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] value = decoder.decodeBuffer(bodyStr);
      System.out.print(new String(value));
    } catch (HttpException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

package com.iflytek.cpdb.lesson01;

public class Test2 {
  
  
  public static void main(String[] args) {
    Test1 test1= new Test1();
    Test1.Inner inner = test1.new Inner();
    inner.inner();
  }
}

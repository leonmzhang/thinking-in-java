package mytest;

class StaticClass {
	static class Test{
		int i = 0;
		
		public Test(int i) {
			this.i = i;
		}
	}
	
	public void test() {
		for (int i = 0; i < 10; i++) {
			@SuppressWarnings("unused")
			Test t = new Test(i);
		}
	}
	
	public static void main(String[] args) {
		//byte b = 0xff;
//		System.out.println(Byte.MAX_VALUE);
//		System.out.println(Byte.MIN_VALUE);
//		System.out.println((int)Character.MAX_VALUE);
//		System.out.println((int)Character.MIN_VALUE);
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Long.MAX_VALUE);
//		System.out.println(Long.MIN_VALUE);
//		System.out.println(Float.MAX_VALUE);
//		System.out.println(Float.MIN_VALUE);
		float f = 20014999;  
		double d = f;  
		double d2 = 20014999;  
		System.out.println("f=" + f);  
		System.out.println("d=" + d);  
		System.out.println("d2=" + d2);
		int i = Float.floatToIntBits(f);
		System.out.println(Integer.toBinaryString(i));
		f = 7E10f;
	}
}
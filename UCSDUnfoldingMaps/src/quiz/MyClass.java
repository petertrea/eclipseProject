package quiz;
// this file for course learning only
// Another test on Monday
public class MyClass {
	private int a;
	public double b;
	
	public MyClass(int first, double second) {
		this.a = first;
		this.b = second;
	}
	public static void incrementBoth(MyClass c1) {
		c1.a = c1.a +1;
		c1.b = c1.b +1.0;
	}
	public static void incrementA(int first) {
		first = first +1;
	}
	public static void incrementB(double second) {
		second = second +1.0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClass c1 = new MyClass(10,20.5);
		MyClass c2 = new MyClass(10,31.5);
		incrementA(c2.a);
		incrementB(c2.b);
		System.out.println(c2.a + ", " +c2.b);
		//c2 = c1;
		//c1.a = 2;
		//System.out.println(c2.a);

	}

}
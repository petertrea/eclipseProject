package Practise;

public class Person {
	private String name;
	
	public String toString() {
		return this.getName();
	}
	
	public String getName() {
		return name;
	}
	
	public Person(String n) {
		super();
		this.name = n;
//		System.out.print("#1 ");
	}
/*	public void setName(String n) {
		this.name = n;
	}
*/	
}

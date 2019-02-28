package Practise;

public class Student extends Person {
	private int studentID;
	
	public int getSID() {
		return studentID;
	}	

	public String toString() {
		return this.getSID() + ": " + super.toString();
	}
	
	public Student(String n, int i) {
		super(n);		
		this.studentID = i;		
	}
	
	
/*	public Student() {
		super();
		this.setName("Student");
	}*/
	
	
	/*	
	public Student() {
		this("Student");
		System.out.print("#2 ");		
	}
	
	public Student(String n) {
		super(n);
//		System.out.print("#3 ");		
	}
	*/
	

}

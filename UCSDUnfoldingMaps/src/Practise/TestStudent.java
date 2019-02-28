package Practise;

public class TestStudent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Student s = new Student();
//		Person p  = new Person("Tim");
//		System.out.println(p);
//		System.out.println(p.toString());
//		Student s = new Student("Cara",1234);
//		Person s = new Student("Cara",1234);
//		System.out.println( s );
		Person p[] = new Person[3];
		p[0] = new Person( "Tim" );
		p[1] = new Student( "Cara", 1234 );
		p[2] = new Student( "Mia", 1256);
		for(int i = 0; i < p.length; i++){
			System.out.println( p[i] );
		}

	}

}

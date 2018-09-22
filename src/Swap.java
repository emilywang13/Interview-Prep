
public class Swap {
	
		Object o;
		Swap(Object o) { this.o = o; }
		
		public static void swap(Swap a, Swap b) {
			Object temp = a.o;
			a.o = b.o;
			b.o = temp;
		}

	public static void main(String[] args) {
		Swap first = new Swap(1);
		Swap second = new Swap(2);
		System.out.println("First was " + first.o);
		System.out.println("Second was " + second.o);

		first.swap(first,  second);
		System.out.println("First is now " + first.o);
		System.out.println("Second is now " + second.o);

		
	}



}

import java.util.Comparator;
import java.util.PriorityQueue;

public class MyPriorityQueue {
	
	
	public static void main(String[] args) {
		//implement comparator to order strings in increasing order
		Comparator<String> com = new Comparator<String>() {
			@Override
			public int compare(String first, String second) {
				if (first.length() < second.length()) 
					return -1;
				else if (second.length() < first.length())
					return 1;
				else
					return 0;
			}
		};
		
		PriorityQueue<String> p = new PriorityQueue<String>(com);
		p.add("testing");
		p.add("print");
		p.add("dlksfljksdjflk");
		//prints head, or shortest string
		System.out.println(p.poll());
	}

}

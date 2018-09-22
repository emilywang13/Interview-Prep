
public class Practice {
	
	int[] numberSwap(int[] input, int a, int b) {
		int begin = input[a]; //create a pointer to the first number to be swapped
		int end = input[b];
		int next = input[a+1];
		int cur = a; //current position in array to be swapped
		while (next != end) {
			input[cur] = next;
			input[cur + 1] = input[a];
		}
		
		
		return input;
	}
	
	public static void removeChars(String remove) {
		boolean[] flags = new boolean[128];
		for (char c: remove.toCharArray()) {
			System.out.println("(int)c is " + (int)c);
			flags[c] = true;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		removeChars("abce");
	}

}

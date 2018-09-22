import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Sort {
	
	//10.1 Given 2 sorted arrays, merge B into A
	int[] sortedMerge(int[] A, int[] B) {
		//keep track of number of elements in A
		int AIndex = 3;
		int BIndex = B.length-1;
		int mergeIndex = AIndex + BIndex + 1;
		while (BIndex >= 0) {
			if (AIndex > 0 && A[AIndex] > B[BIndex]) {
				A[mergeIndex] = A[AIndex];
				AIndex--;
			}
			else {
				A[mergeIndex] = B[BIndex];
				BIndex--;
			}
			mergeIndex--;
		}
		return A;
	}
	
	//10.2 Sort an array of strings so all anagrams are next to each other
	String[] groupAnagrams(String[] input) {
		String[] result = input;
		//create a HashMap to hold strings with key = alphabetized string, values = all anagrams of key
		HashMap<String, ArrayList<String>> h = new HashMap<String, ArrayList<String>>();
		for (String s: input) {
			//if value at key is empty, initialize new ArrayList<String>
			if (h.get(anagram(s)) == null) {
				h.put(anagram(s), new ArrayList<String>());
			}
			//add value to key
			h.get(anagram(s)).add(s);
		}
		Collection<ArrayList<String>> allStrings = h.values();
		int i = 0;
		for (ArrayList<String> a: allStrings) {
			for (String s: a) {
				result[i] = s;
				i++;		
			}
		}
		return result;
	}
	
	//returns the string with characters sorted alphabetically
	String anagram(String input) {
		if (input.length() == 0 || input.length() == 1) 
			return input;
		char[] alpha = input.toCharArray();
		//iterate through String input, moving min elements to front
		for (int i = 0; i < input.length()-1; i++) {
			//let minimum start at current element. after each iteration, new minimum will move one over to the right since front of array is already sorted
			char min = input.charAt(i);
			for (int j = i+1; j < input.length(); j++) {
				//if we found a character smaller than current min
				if (input.charAt(j) < min) {
					//update current min
					min = input.charAt(j);
					//swap min character with current char, moving min char to front of alpha
					char temp = input.charAt(i);
					alpha[i] = min;
					alpha[j] = temp;
				}
			}
			
		}
		return String.valueOf(alpha);
	}
	
	public static void main(String[] args) {
		String[] test = {"list", "cat", "slit", "dog", "ball", "god", "a", "b", "a", ""};
		System.out.println("Before grouping anagrams, array is ");
		for (String input: test) {
			System.out.print(input + " ");
		}
		
		Sort s = new Sort();
		String[] result = s.groupAnagrams(test);
		System.out.println("\nAfter grouping anagrams, array is ");
		for (String input: result) {
			System.out.print(input + " ");
		}
	}
}

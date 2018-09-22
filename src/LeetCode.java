import java.util.HashMap;
class LeetCode {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> h = new HashMap<Character, Integer>();
        for (char c: s.toCharArray()) {
            if (!h.containsKey(c))
                h.put(c, 0);
        }
        for (char c: s.toCharArray()) {
            h.put(c, h.get(c) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (h.get(s.charAt(i)) == 1)
                return i;
        }
        return -1; //there are no non-repeating characters
    }
    public static void main(String[] args) {
    	LeetCode s = new LeetCode();
    	System.out.println(s.firstUniqChar("aa"));
    }
}
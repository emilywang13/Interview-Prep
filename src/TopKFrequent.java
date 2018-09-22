import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TopKFrequent {
	    public List<Integer> topKFrequent(int[] nums, int k) {
	        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
	        for (int i : nums) {
	            if (!h.containsKey(i)) h.put(i, 0);
	            else {
	                h.put(i, h.get(i) + 1);
	            }
	        }
	        	        
	        List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(h.entrySet());
	        Collections.sort(list, new Comparator<Map.Entry<Integer,Integer>>() {
	        	public int compare(Entry<Integer,Integer> x, Entry<Integer, Integer> y) {
	        		return y.getValue() - x.getValue();
	        	}
	        }); 
	   
	      
	        List<Integer> kvalues = new ArrayList<Integer>();
	        for (int i = 0; i < k; i++)
	            kvalues.add(list.get(i).getKey());
	        return kvalues;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,1,1,2,2,3,1};
		TopKFrequent x = new TopKFrequent();
		List<Integer> result = x.topKFrequent(nums, 2);
		for (int i : result) {
			System.out.println(i);
		}
	}

}

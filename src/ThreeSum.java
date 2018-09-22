import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/*
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 */
class ThreeSum {
	/*iterate through array, i is pointer to first element, j and k are pointers to second and last.
	 * if j+k is too large, decrement k to find another element. otherwise, increment k.
	 */
    public List<List<Integer>> threeSum(int[] nums) {
    	Arrays.sort(nums);
        List<List<Integer>> triplets = new ArrayList<List<Integer>>();
        if (nums.length <= 2) return triplets; //if there are <= 2 elements, return empty triplets list

        
        for (int i = 0; i < nums.length - 2; i++) {
        	//skip over duplicates, except for first element in each chunk of repeated elements
            if (i==0 || nums[i] > nums[i-1]) {
        	int start = i+1;
        	int end = nums.length-1;
        	while (start < end) {
        		if (nums[i] + nums[start] + nums[end] == 0) {
        			List<Integer> trip = new ArrayList<Integer>();
                    trip.add(nums[i]);
                    trip.add(nums[start]);
                    trip.add(nums[end]);
                    triplets.add(trip);
                    int prevStart = start;
                    //increment start until there is a new value, repeat process to find more triplets
                    while (nums[start] == nums[prevStart] && start < end) {
                    	start++;
                    }
        		}
        		//if sum is too small, increment start
        		else if (nums[i] + nums[start] + nums[end] < 0) {
        			int prevStart = start;
        			//increment start, skipping duplicates
        			 while (nums[start] == nums[prevStart] && start < end) {
                     	start++;
                     }
        		}
        		//sum is too large, decrement end
        		else {
        			int prevEnd = end;
        			//increment end, skipping duplicates
        			while (nums[end] == nums[prevEnd] && start < end) {
                    	end--;
                    }
        		}
        	}
            }
        }
        	
        for (List<Integer> t : triplets) {
        	for (Iterator it = t.iterator(); it.hasNext();) {
        		System.out.print(it.next() + " ");
        	}
        	System.out.println();
        }
        return triplets;
    }
    public static void main(String[] args) {
    	ThreeSum s = new ThreeSum();
    	int[] input = {-2,0,1,1,2};
    	/*
    	 * i starts at -2, start is 0, end is 2. that's the first triplet.
    	 * next, start is 1. 1+2+(-2) is too big, end--. found second triplet.
    	 * start increments until find something different that prevStart(1), start > end. stop.
    	 * i then points to 0, start is 1, end is 2. 
    	 * i then points to the second 1
    	 */
    	s.threeSum(input);
    	
    	
    }
}
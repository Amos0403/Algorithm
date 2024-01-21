
/**
 * CISC 380
 * Algorithms Assignment 3 EXTRA CREDIT
 * 
 * 
 * Implements an iterative dynamic programming solution to find the subarray of maximum sum, of a given array of numbers.
 * 
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 * 
 */

public class MaxSum{

    /**
     * Returns the sum of the subarray with the maximum sum from the given array of integers.
     * 
     * 
     * @param a an array of integers
     * @return the sum of the subarray with the maximum sum.
     */
    public static int maxSumSubarray(int[] a){
		
        //YOUR CODE HERE
        int n = a.length;
        int[] D = new int[n];  // Initialize the table
        D[0] = a[0];  // Base case

        for (int i = 1; i < n; i++) {
            D[i] = Math.max(a[i], a[i] + D[i-1]);  // Fill the table
        }

        // Find and return the maximum sum
        int maxSum = D[0];
        for (int i = 1; i < n; i++) {
            if (D[i] > maxSum) {
                maxSum = D[i];
            }
        }

        return maxSum;

    }//maxSubArray

}//class
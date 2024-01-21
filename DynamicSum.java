import java.util.Arrays;

/**
 * CISC 380
 * Algorithms Assignment 4
 * 
 * Implements dynamic programming solutions to see if a subset adds up to a value.
 * 
 * @author Amos Le
 * Due Date: 11/12/2023
 */



public class DynamicSum{

    public DynamicSum(){
        //YOUR CODE HERE
    }//constructor

    /**
     *Checks to see if a subset of arr adds up to exactly t with an iterative solution.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return True, if a subset adds up to t, false otherwise.
     * 
     */

     // This iterates over all elements in the array and for each element, it checks all sums up to t. Therefore, the time complexity is O(n*t), where n is the number of elements in the array and t is the target sum.
    public boolean isSum(int[] arr, int t){
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][t+1];

        // Base case initialization
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Dynamic Programming for subset sum
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= t; j++) {
                if (arr[i-1] <= j)
                    dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
                else
                    dp[i][j] = dp[i-1][j];
            }
        }

        return dp[n][t];
    }//isSum

    /**
     *Checks to see if a subset of arr adds up to exactly t with a memoizied solution.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return    True, if a subset adds up to t, false otherwise.
     * 
     */

     //The time complexity is O(n*t)
    public boolean isSumMem(int[] arr, int t ){
        int n = arr.length;
        Boolean[][] dp = new Boolean[n+1][t+1];

    // Initialize base cases
    for (int i = 0; i <= n; i++)
        dp[i][0] = true;
    for (int i = 1; i <= t; i++)
        dp[0][i] = false;

    // Fill up the dp table
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= t; j++) {
            if (arr[i-1] <= j)
                dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
            else
                dp[i][j] = dp[i-1][j];
        }
    }

    return dp[n][t];
    }//isSumMem

    /**
     * Recovers the subset of arr that adds up to t, if it exists.
	 *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return a subset of arr that adds up to t, null otherwise.
     * 
     */

    //It iterates through the array to find the subset, which takes O(n) time. Therefore, the total time complexity is O(nt + n) = O(nt).
    public int[] getSubset(int[] arr, int t){
        if (!isSumMem(arr, t)) {
            return null;
        }

        int n = arr.length;
        int[] subset = new int[n];
        int count = 0;

        for (int i = n; i > 0 && t > 0; i--) {
            if (!isSumMem(arr, t, i - 1)) {
                subset[count] = arr[i - 1];
                t -= arr[i - 1];
                count++;
            }
        }

        return Arrays.copyOf(subset, count);
    }//getSubset

}//class
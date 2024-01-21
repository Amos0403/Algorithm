/**
 * CISC 380
 * Algorithms Assignment 4 EXTRA CREDIT
 * 
 * Implements a dynamic programming solution to find the length of the longest palindromic subsequence.
 * 
 * @author Amos Le
 * Due Date: 11/12/2023
 */


public class PalindromicSequence{

    /**
     * Implements a dynamic programming solution to find the length of the longest Palindromic subsequence of the given string.
     * 
     * 
     * @param x the string that may contain a palindromic subsequence
     * @return the length of the longest palindromic subsequence, or 0 if there is none.
     */
    public static int getLengthLongestPalindrome(String x){
        int n = x.length();
        int[][] dp = new int[n][n];

    // All characters in the string are palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
    }

        for (int cl = 2; cl <= n; cl++) {
            for (int i = 0; i < n - cl + 1; i++) {
                int j = i + cl - 1;
                if (x.charAt(i) == x.charAt(j)) {
                    // If there are only 2 characters and both are same
                    if (cl == 2) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][n - 1];

    }//longestPalindrome

    /**
     * Implements a dynamic programming solution to return the longest palindromic subsequence of the given string 
     * @param x the string that may contain a palindromic subsequence
     * @return the string of the longest palindrome, or null if there is none
     */
    public static String getLongestPalindrome(String x) {
        int n = x.length();
        int[][] dp = new int[n][n];
        String[][] solution = new String[n][n];

        // All characters in the string are palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            solution[i][i] = String.valueOf(x.charAt(i));
        }

        for (int cl = 2; cl <= n; cl++) {
            for (int i = 0; i < n - cl + 1; i++) {
                int j = i + cl - 1;
                if (x.charAt(i) == x.charAt(j)) {
                    // If there are only 2 characters and both are same
                    if (cl == 2) {
                        dp[i][j] = 2;
                        solution[i][j] = x.substring(i, j + 1);
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                        solution[i][j] = x.charAt(i) + solution[i + 1][j - 1] + x.charAt(j);
                    }
                } else {
                    if (dp[i][j - 1] > dp[i + 1][j]) {
                        dp[i][j] = dp[i][j - 1];
                        solution[i][j] = solution[i][j - 1];
                    } else {
                        dp[i][j] = dp[i + 1][j];
                        solution[i][j] = solution[i + 1][j];
                    }
                }
            }
        }

        return solution[0][n - 1];
    }



}//class
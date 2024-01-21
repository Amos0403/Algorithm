/**
 * CISC 380
 * Algorithms Assignment 3
 *
 * Implements a dynamic programming solution to the Interleaving Strings problem.
 * 
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 */

public class Interleaver{

    private Boolean[][] memo;
    public Interleaver(){
        //YOUR CODE HERE
        
        
       
    }//constructor

    /**
     * Finds if the two strings x and y are an interleaving of string z
     * 
     * The string Z is an interleaving of X and Y if it can be obtained
     * by interleaving the characters in X and Y in a way that
     * maintains the left-to-right order of the c in X and Y:
     * 
     * @param x the first string that composes an interleaving
     * @param y the second string that composes an interleaving
     * @param z the string to check for an interleaving
     * @return True, if the string z is an interleaving of x and y. False otherwise.
     * 
     */
    public Boolean isInterleaved(String x, String y, String z){

        //YOUR CODE HERE  
        if (z.length() != x.length() + y.length()) {
            return false;
        }
        memo = new Boolean[x.length() + 1][y.length() + 1];
        return isInterleaved(x, y, z, x.length(), y.length());
    }//isInterleaved

    private Boolean isInterleaved(String x, String y, String z, int i, int j) {
        if (i == 0 && j == 0) {
            return true;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > 0 && z.charAt(i + j - 1) == x.charAt(i - 1) && 
            (j == 0 || z.charAt(i + j - 1) != y.charAt(j - 1))) {
            memo[i][j] = isInterleaved(x, y, z, i - 1, j);
        } else if (j > 0 && z.charAt(i + j - 1) == y.charAt(j - 1) && 
                   (i == 0 || z.charAt(i + j - 1) != x.charAt(i - 1))) {
            memo[i][j] = isInterleaved(x, y, z, i, j - 1);
        } else if (i > 0 && j > 0 && z.charAt(i + j - 1) == x.charAt(i - 1) && 
                   z.charAt(i + j - 1) == y.charAt(j - 1)) {
            memo[i][j] = isInterleaved(x, y, z, i - 1, j) || isInterleaved(x, y, z, i, j - 1);
        } else {
            memo[i][j] = false;
        }
        return memo[i][j];
    }

    /**
     * Returns a string representation of the solution of the interleaved string problem.
     * 
     * The return value is a string whose length is equal to z. 
     * All characters in z are replaced by character "x" if they come from the string "x",
     * and all characters in z are replaced by the character "y" if they come from the string y.
     * 
     * For example, on an input of x = "ab", y = "cd", and z = "abcd", then the output shall be the string "xxyy".
     * 
     * @param x the first string that composes an interleaving
     * @param y the second string that composes an interleaving
     * @param z the string to check for an interleaving
     * @return A string representation of the solution, is there is no solution, return "NA"
     */
    public String getSolution(String x, String y, String z){

        //YOUR CODE HERE
        if (!isInterleaved(x, y, z)) {
            return "NA";
        }
        solution = new String[x.length() + 1][y.length() + 1];
        return getSolution(x, y, z, x.length(), y.length());
        return null;

    }
    private String getSolution(String x, String y, String z, int i, int j) {
        if (i == 0 && j == 0) {
            return "";
        }
        if (solution[i][j] != null) {
            return solution[i][j];
        }
        if (i > 0 && z.charAt(i + j - 1) == x.charAt(i - 1)) {
            solution[i][j] = "x" + getSolution(x, y, z, i - 1, j);
        } else if (j > 0 && z.charAt(i + j - 1) == y.charAt(j - 1)) {
            solution[i][j] = "y" + getSolution(x, y, z, i, j - 1);
        } else if (i > 0 && j > 0 && z.charAt(i + j - 1) == x.charAt(i - 1) &&
                   z.charAt(i + j - 1) == y.charAt(j - 1)) {
            solution[i][j] = "x" + getSolution(x, y, z, i - 1, j);
            if (solution[i][j].compareTo("y" + getSolution(x, y, z, i , j-1)) >0){
                solution[i][j] = "y" + getSolution(x,y,z,i,j-1);
            }
        }
        return solution[i][j];
    }

}//class
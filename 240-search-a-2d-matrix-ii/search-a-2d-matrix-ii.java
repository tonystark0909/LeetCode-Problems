class Solution {
  
    /**
     * Searches for a target value in a 2D matrix.
     * The matrix has the following properties:
     * - Integers in each row are sorted in ascending from left to right.
     * - The first integer of each row is greater than the last integer of the previous row.
     *
     * @param matrix 2D matrix of integers
     * @param target The integer value to search for
     * @return boolean indicating whether the target is found
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // Get the number of rows and columns in the matrix
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
      
        // Start from the bottom-left corner of the matrix
        int row = rowCount - 1;
        int col = 0;
      
        // Perform a staircase search
        while (row >= 0 && col < colCount) {
            if (matrix[row][col] == target) {
                // Target is found at the current position
                return true;
            }
            if (matrix[row][col] > target) {
                // Target is less than the current element, move up
                row--;
            } else {
                // Target is greater than the current element, move right
                col++;
            }
        }
      
        // Target was not found in the matrix
        return false;
    }
}
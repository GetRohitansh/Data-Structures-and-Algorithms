import java.util.Arrays;

public class TwoDArrays {


    /* 
    Topics Covered: 
     * Spiral Matrix
     * Sum of Diagonals of matrix
     * Search in row and col wise sorted array (Staircase Search)
    */
    public static void main(String[] args) {
        int matrix[][] = new int[][]{
            {10,20,30,40},
            {15,25,35,45},
            {27,29,37,48},
            {32,33,39,50}
        };
        System.out.println("Spiral Matrix: ");
        spiralMatrix(matrix);
        System.out.println("\nDiagonal Sum of matrix(n,n): " + diagonalSum(matrix));
        System.out.println("Search in row and col wise sorted array: "+Arrays.toString(staircaseSearch(matrix, 32)));
    }
    public static void spiralMatrix(int matrix[][]){
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length-1;
        int endCol = matrix[0].length-1;

        while(startRow<=endRow && startCol<=endCol){
            //top
            for(int j=startCol; j<=endCol; j++){
                System.out.print(matrix[startRow][j] +" ");
            }
            //right
            for(int i=startRow+1; i<=endRow; i++){
                System.out.print(matrix[i][endCol]+" ");
            }
            //bottom
            for(int j=endCol-1; j>=startCol; j--){
                if(endRow == startRow) break;
                System.out.print(matrix[endRow][j] +" ");
            }
            //left
            for(int i=endRow-1; i>=startRow+1; i--){
                if(endCol == startCol) break;
                System.out.print(matrix[i][startCol] +" ");
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
    }

    public static int diagonalSum(int matrix[][]){
        int sum = 0;
        for(int i=0; i<matrix.length; i++){
            sum += matrix[i][i] + matrix[i][matrix.length-1-i];
        }
        return (matrix.length%2==0) ? sum : (sum-matrix[matrix.length/2][matrix.length/2]);
    }

    //Search for key in row wise and column wise sorted matrix
    public static int[] staircaseSearch(int matrix[][], int target){
        int row = 0;
        int col = matrix[0].length-1;
        
        while(row<matrix.length && col>=0){
            if(target==matrix[row][col]){
                return new int[]{row,col};
            }else if(target<matrix[row][col]){
                col--; // Move right
            }else{
                row++; // Move down
            }
        }
        return new int[]{-1,-1};
    }
}

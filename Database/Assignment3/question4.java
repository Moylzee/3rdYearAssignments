/**
 * Question 4 Part A
 * @author Brian Moyles - 21333461
 */
public class question4 {

    public static int[][] matrix;
    
    public static void main(String[] args) {
        matrix = new int[4][4];
        int numVertices = 4;
        int k = 2;
        int numDegree = 0;

        // Create the Adjacency Matrix 
        matrix[0][0] = 0;
        matrix[0][1] = 3;
        matrix[0][2] = 5;
        matrix[0][3] = 1;     
        matrix[1][0] = 3;
        matrix[1][1] = 0;
        matrix[1][2] = 6;
        matrix[1][3] = 2;
        matrix[2][0] = 5;
        matrix[2][1] = 6;
        matrix[2][2] = 0;
        matrix[2][3] = 4;      
        matrix[3][0] = 1;
        matrix[3][1] = 2;
        matrix[3][2] = 4;
        matrix[3][3] = 0;

        // Display the Matrix  
        System.out.println("\nMatrix");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    
        // This can be used to calculate the degree of each node for a given timestamp 
        System.out.println("\nDegrees of each node, excluding values <= K");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("\ni = " +(i+1));
            numDegree = 0; // Reset degree for each node
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] > k) {
                    System.out.print(matrix[i][j] + " ");
                    numDegree++;
                }
            }
            System.out.println("\nDegree of Node " + (i+1) + ": " + numDegree);
        }
    }
}
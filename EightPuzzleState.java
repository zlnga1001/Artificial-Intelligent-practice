import search.*;
import java.util.*;


//State and can record the state of the problem. It is important that this class implements an equals() method that can be used to compare the current state to another state.
public class EightPuzzleState extends State {

    private final int[][] puzzle; //puzzle matrix
    private final int zeroRow, zeroCol; //position of the empty tile

    public EightPuzzleState(int[][] puzzle) {
        this.puzzle = new int[3][3];
        int row = -1, col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.puzzle[i][j] = puzzle[i][j];
                if (puzzle[i][j] == 0) {
                    row = i;
                    col = j;
                }
            }
        }
        this.zeroRow = row;
        this.zeroCol = col;
    }

    //Implement equals() to compare two states
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; //same obj reference
        if (!(obj instanceof EightPuzzleState))
            return false; //check type if compatible

        EightPuzzleState other = (EightPuzzleState) obj;

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] != other.puzzle[i][j]) {
                    return false; // If any element differs, states are not equal
                }
            }
        }
        return true; //all element are same
    }
    
        // HashCode is required for hash-based data structures (like HashSet)
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(puzzle);
    }
    
    // ToString() method for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : puzzle) {
            for (int num : row) {
                sb.append(num == 0 ? "_" : num).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Method to move a tile and return a new state
    public EightPuzzleState move(int newRow, int newCol) {
        int[][] newPuzzle = deepCopy(puzzle);
        // Swap the empty tile with the target position
        newPuzzle[zeroRow][zeroCol] = newPuzzle[newRow][newCol];
        newPuzzle[newRow][newCol] = 0;
        return new EightPuzzleState(newPuzzle);
    }

    // Helper method to deep copy 2D array
    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    // Getter for puzzle
    public int[][] getPuzzle() {
        return puzzle;
    }
}
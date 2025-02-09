import search.*;

import java.util.ArrayList;
import java.util.List;

public class EightPuzzleProblem extends SearchProblem {
    private final EightPuzzleState initialState;
    private static final int[][] GOAL_STATE = {
        {1, 2, 3},
        {8, 0, 4},
        {7, 6, 5}
    };

    public EightPuzzleProblem(int[][] startState) {
        this.initialState = new EightPuzzleState(startState);
    }

    @Override
    public State getInitialState() {
        return initialState;
    }

    @Override
    public boolean goalTest(State state) {
        if (state instanceof EightPuzzleState) {
            return ((EightPuzzleState) state).equals(new EightPuzzleState(GOAL_STATE));
        }
        return false;
    }

    @Override
    public List<Successor> getSuccessors(State state) {
        List<Successor> successors = new ArrayList<>();
        if (!(state instanceof EightPuzzleState)) return successors;

        EightPuzzleState currentState = (EightPuzzleState) state;
        int[][] puzzle = currentState.getPuzzle();
        int row = -1, col = -1;

        // Find the empty tile position
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] == 0) {
                    row = i;
                    col = j;
                }
            }
        }

        // Define possible moves
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
        String[] actions = {"UP", "DOWN", "LEFT", "RIGHT"};

        for (int i = 0; i < 4; i++) {
            int newRow = row + moves[i][0];
            int newCol = col + moves[i][1];

            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                EightPuzzleState newState = currentState.move(newRow, newCol);
                successors.add(new Successor(newState, new SimpleAction(actions[i]), 1));
            }
        }
        return successors;
    }

    @Override
    public int getHeuristicValue(State state) {
        if (!(state instanceof EightPuzzleState)) return Integer.MAX_VALUE;

        EightPuzzleState currentState = (EightPuzzleState) state;
        int[][] puzzle = currentState.getPuzzle();
        int heuristic = 0;

        // Manhattan Distance heuristic
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = puzzle[i][j];
                if (value != 0) {
                    int goalRow = (value - 1) / 3;
                    int goalCol = (value - 1) % 3;
                    heuristic += Math.abs(goalRow - i) + Math.abs(goalCol - j);
                }
            }
        }
        return heuristic;
    }
}

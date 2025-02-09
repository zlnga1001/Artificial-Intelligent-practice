import search.*;
import java.util.*;

public class TowersProblem extends SearchProblem {
    private final int numDisks;
    private final TowersState initialState;
    
    public TowersProblem(int numDisks) {
        this.numDisks = numDisks;
        
        // Initialize the starting state with all disks on the leftmost rod
        List<Stack<Integer>> initialRods = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            initialRods.add(new Stack<>());
        }
        
        // Add disks in decreasing size (largest disk has size numDisks)
        for (int i = numDisks; i > 0; i--) {
            initialRods.get(0).push(i);
        }
        
        this.initialState = new TowersState(initialRods);
    }
    
    @Override
    public State getInitialState() {
        return initialState;
    }
    
    @Override
    public boolean goalTest(State state) {
        if (!(state instanceof TowersState)) return false;
        TowersState towersState = (TowersState) state;
        List<Stack<Integer>> rods = towersState.getRods();
        
        // Check if rightmost rod has all disks in correct order
        if (rods.get(2).size() != numDisks) return false;
        
        // Verify the order of disks
        int prevSize = Integer.MAX_VALUE;
        for (int disk : rods.get(2)) {
            if (disk > prevSize) return false;
            prevSize = disk;
        }
        
        return true;
    }
    
    @Override
    public List<Successor> getSuccessors(State state) {
        List<Successor> successors = new ArrayList<>();
        if (!(state instanceof TowersState)) return successors;
        
        TowersState currentState = (TowersState) state;
        
        // Try all possible moves between rods
        for (int fromRod = 0; fromRod < 3; fromRod++) {
            for (int toRod = 0; toRod < 3; toRod++) {
                if (fromRod != toRod && currentState.isValidMove(fromRod, toRod)) {
                    // Create new state with the move applied
                    TowersState newState = currentState.copy();
                    List<Stack<Integer>> newRods = newState.getRods();
                    int disk = newRods.get(fromRod).pop();
                    newRods.get(toRod).push(disk);
                    
                    // Create action string (e.g., "Move disk from rod 0 to rod 2")
                    SimpleAction action = new SimpleAction("Move disk from rod " + fromRod + " to rod " + toRod);
                    
                    // Add successor with cost 1 for each move
                    successors.add(new Successor(newState, action, 1));
                }
            }
        }
        
        return successors;
    }
    
    @Override
    public int getHeuristicValue(State state) {
        if (!(state instanceof TowersState)) return Integer.MAX_VALUE;
        TowersState towersState = (TowersState) state;
        List<Stack<Integer>> rods = towersState.getRods();
        
        // Count disks not on the goal rod
        // This is admissible because each disk not on goal rod needs at least one move
        return rods.get(0).size() + rods.get(1).size();
    }
}

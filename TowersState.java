import search.*;
import java.util.*;

public class TowersState extends State {
    private final List<Stack<Integer>> rods;  // Three rods with disks
    
    public TowersState(List<Stack<Integer>> rods) {
        this.rods = new ArrayList<>();
        for (Stack<Integer> rod : rods) {
            Stack<Integer> newRod = new Stack<>();
            newRod.addAll(rod);
            this.rods.add(newRod);
        }
    }
    
    public List<Stack<Integer>> getRods() {
        return rods;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TowersState)) return false;
        TowersState other = (TowersState) obj;
        return rods.equals(other.rods);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(rods);
    }
    
    @Override
    public String toString() {
        return "Rods: " + rods.toString();
    }
    
    // Deep copy of the current state
    public TowersState copy() {
        List<Stack<Integer>> newRods = new ArrayList<>();
        for (Stack<Integer> rod : rods) {
            Stack<Integer> newRod = new Stack<>();
            newRod.addAll(rod);
            newRods.add(newRod);
        }
        return new TowersState(newRods);
    }
    
    // Check if a move is valid according to puzzle rules
    public boolean isValidMove(int fromRod, int toRod) {
        if (fromRod < 0 || fromRod > 2 || toRod < 0 || toRod > 2) return false;
        if (rods.get(fromRod).isEmpty()) return false;
        if (rods.get(toRod).isEmpty()) return true;
        return rods.get(fromRod).peek() < rods.get(toRod).peek();
    }
}

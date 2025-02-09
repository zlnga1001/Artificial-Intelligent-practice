import search.*;

public class EvalTowers {
    private static void runSearches(int numDisks) {
        System.out.println("\nTesting Towers of Hanoi with " + numDisks + " disks:");
        TowersProblem problem = new TowersProblem(numDisks);
        
        // Create search methods with the problem
        SearchMethod[] methods = {
            new UniformCost(problem),
            new Greedy(problem),
            new AStar(problem)
        };
        
        // Run each search method
        for (SearchMethod method : methods) {
            System.out.println("\nSearch using " + method.getClass().getSimpleName() + ":");
            method.search(); 
        }
    }
    
    public static void main(String[] args) {
        // Test with 3 disks
        runSearches(3);
        
        // Test with 4 disks
        runSearches(4);
    }
}

import search.*;

public class EvalEightPuzzle {
    public static void main(String[] args) {
        int[][] initial1 = {
            {7, 1, 4},
            {6, 3, 2},
            {8, 5, 0}
        };

        int[][] initial2 = {
            {4, 8, 2},
            {6, 3, 5},
            {1, 7, 0}
        };

        int[][] initial3 = {
            {7, 5, 3},
            {6, 4, 0},
            {8, 1, 2}
        };

        EightPuzzleProblem problem1 = new EightPuzzleProblem(initial1);
        EightPuzzleProblem problem2 = new EightPuzzleProblem(initial2);
        EightPuzzleProblem problem3 = new EightPuzzleProblem(initial3);

        // Create search methods with their respective problems
        SearchMethod[] methods = {
            new UniformCost(problem1), 
            new Greedy(problem1), 
            new AStar(problem1)
        };

        for (SearchMethod method : methods) {
            System.out.println("\nSearch using " + method.getClass().getSimpleName() + ":");
            
            // Problem 1
            SearchMethod method1;
            if (method instanceof UniformCost) {
                method1 = new UniformCost(problem1);
            } else if (method instanceof Greedy) {
                method1 = new Greedy(problem1);
            } else {
                method1 = new AStar(problem1);
            }
            System.out.println("Problem 1:");
            method1.search();

            // Problem 2
            SearchMethod method2;
            if (method instanceof UniformCost) {
                method2 = new UniformCost(problem2);
            } else if (method instanceof Greedy) {
                method2 = new Greedy(problem2);
            } else {
                method2 = new AStar(problem2);
            }
            System.out.println("\nProblem 2:");
            method2.search();

            // Problem 3
            SearchMethod method3;
            if (method instanceof UniformCost) {
                method3 = new UniformCost(problem3);
            } else if (method instanceof Greedy) {
                method3 = new Greedy(problem3);
            } else {
                method3 = new AStar(problem3);
            }
            System.out.println("\nProblem 3:");
            method3.search();
        }
    }
}

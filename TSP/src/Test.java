/**
 * Created by Andreas on 2014-11-27.
 */
public class Test {

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {

        TSPSolver solver = new SimpleHeuristic();


        OnlyOneNodeTest(solver);
    }


    public static void OnlyOneNodeTest(TSPSolver solver) {
        Graph graph = new Graph();

        Solution solution = solver.solve(graph);
        double distance = graph.getDistance(solution);

        GraphPrinter.printGraph(graph, solution);

        return;
    }

}

/**
 * Created by Andreas on 2014-11-27.
 */
public class Test {

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {

        TSPSolver solver = new SimpleHeuristic();


        assert (OnlyOneNodeTest(solver));
    }


    public static boolean OnlyOneNodeTest(TSPSolver solver) {
        Graph graph = new Graph();

        short[] solution = solver.solve(graph);

        double distance = graph.getDistance(solution);

        return true;
    }

}

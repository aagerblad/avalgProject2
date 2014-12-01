/**
 * Created with IntelliJ IDEA.
 * User: tobbew92
 * Date: 30/11/14
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class TSP {

    private Kattio in;
    private Graph graph;
    private int nodes;
    TSPSolver solver;


    public static void main(String[] args) {
        new TSP();
    }

    public TSP() {

        in = new Kattio(System.in);
        nodes = in.getInt();
        graph = new Graph(nodes);
        readGraph();


        solveTSP(graph);
//
//        printSolution(solution);
//        printDistance(graph, solution);

    }

    public static void solveTSP(Graph graph) {
//        for (int m = 0; m < 20; m++) {
//
//        }
        long startTime = System.currentTimeMillis();
        TSPSolver solver = new Greedy();

        long cTime = System.currentTimeMillis() - startTime;
        Solution solution = solver.solve(graph, cTime, 0);
//        printSolution(solution);
//        printDistance(graph, solution);

        TwoOpt twoOpt = new TwoOpt(solution);
//        cTime = System.currentTimeMillis() - startTime;
        solution = twoOpt.solve(graph, startTime, 15000);
        printSolution(solution);
//        System.err.println("m=" + m);
        printDistance(graph, solution);

    }

    private void readGraph() {
        for (int i = 0; i < nodes; i++) {
            graph.addNode(i, in.getDouble(), in.getDouble());
        }

    }

    private static void printDistance(Graph g, Solution s) {
        System.err.println(g.getDistance(s));
    }

    private static void printSolution(Solution s) {
        for (int i = 0; i < s.path.length; i++) {
            System.out.println(s.path[i]);
        }

    }
}

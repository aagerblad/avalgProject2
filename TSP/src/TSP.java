/**
 * Created with IntelliJ IDEA.
 * User: tobbew92
 * Date: 30/11/14
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class TSP {

    private static long timeout = 5000;
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
        readGraph(nodes, graph, in);

        solveTSP(graph);

    }

    public static void solveTSP(Graph graph) {
        graph.getNeighbors();
        long startTime = System.currentTimeMillis();
        TSPSolver solver = new Greedy();

        long cTime = System.currentTimeMillis() - startTime;
        Solution solution = solver.solve(graph, cTime, 0);
//        printSolution(solution);
//        printDistance(graph, solution);

        TwoOpt twoOpt = new TwoOpt(solution);
//        cTime = System.currentTimeMillis() - startTime;
        long tout = timeout;
        solution = twoOpt.solve(graph, startTime, tout);
        TwoHalfOpt twoHalfOpt = new TwoHalfOpt(solution);
        solution = twoHalfOpt.solve(graph, startTime, tout);
        printSolution(solution);
//        System.err.println("m=" + m);
        printDistance(graph, solution);

    }

    public static void readGraph(int nodes, Graph graph, Kattio in) {
        for (int i = 0; i < nodes; i++) {
            graph.addNode(i, in.getDouble(), in.getDouble());
        }
//        graph.calculateNeihborhood();
    }

    public static void printDistance(Graph g, Solution s) {
        System.err.println(g.getDistance(s));
    }

    public static void printSolution(Solution s) {
        for (int i = 0; i < s.path.length; i++) {
            System.out.println(s.path[i]);
        }

    }

    public final static void clearConsole()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

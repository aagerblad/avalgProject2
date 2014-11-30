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

        solver = new Greedy();

        Solution solution = solver.solve(graph, 10000);
        printSolution(solution);
        printDistance(graph, solution);

    }

    private void readGraph() {
        for (int i = 0; i < nodes; i++) {
            graph.addNode(i, in.getDouble(), in.getDouble());
        }

    }

    private void printDistance(Graph g, Solution s) {
        System.out.println(g.getDistance(s));
    }

    private void printSolution(Solution s) {
        for (int i = 0; i < s.path.length; i++) {
            System.out.println(s.path[i]);
        }

    }
}

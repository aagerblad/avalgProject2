import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Andreas on 2014-11-27.
 */
public class Test {

    private Kattio in;
    Graph graph;
    TSPSolver solver;

    public static void main(String[] args) {
        new Test();
    }

    Test() {
//        graph = getKNodes("TSP\\g1.in");
        graph = get1000Nodes();
        TSP.solveTSP(graph);
    }

//    public static void readGraph(int nodes, Graph graph, Kattio in) {
//        for (int i = 0; i < nodes; i++) {
//            graph.addNode(i, in.getDouble(), in.getDouble());
//        }
//    }

    public Graph get3Nodes() {
        return getKNodes("TSP\\g3.in");
    }

    public Graph get20Nodes() {
        return getKNodes("TSP\\g20.in");
    }

    public Graph get100Nodes() {
        return getKNodes("TSP\\g100.in");
    }

    public Graph get1000Nodes() {
        return getKNodes("TSP\\g1000.in");
    }

    private Graph getKNodes(String s) {
        try {
            in = new Kattio(new FileInputStream(s));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int nodes = in.getInt();
        Graph graph = new Graph(nodes);
        TSP.readGraph(nodes, graph, in);
        return graph;
    }


    public static void OnlyOneNodeTest(TSPSolver solver) {
        //Graph graph = new Graph();

        //Solution solution = solver.solve(graph);
        //double distance = graph.getDistance(solution);

        //GraphPrinter.printGraph(graph, solution);

        return;
    }

}

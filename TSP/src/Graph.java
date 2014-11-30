/**
 * Created by Andreas on 2014-11-27.
 */
public class Graph {

    private Node[] nodes;
    public double[][] distances;
    private int numNodes = 0;


    private class Node {
        public double x;
        public double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public Graph(int n) {
        this.numNodes = n;
        this.distances = new double[n][n];
        this.nodes = new Node[n];
    }

    public void addNode(int i, double x, double y) {
        nodes[i] = new Node(x,y);

        for (int j = 0; j < nodes.length; j++) {
            if (nodes[j] != null) {
                distances[i][j] =  Math.sqrt((nodes[i].x-nodes[j].x) * (nodes[i].x-nodes[j].x) + (nodes[i].y-nodes[j].y) * (nodes[i].y-nodes[j].y));
                distances[j][i] = distances[i][j];
            }

        }
    }

    public int getNodes() {
        return numNodes;
    }

    public double[][] getDistances() {
        return distances;
    }


    public double getDistance(Solution solution) {
        double distance = 0;
        for (int i = 0; i < solution.path.length - 1; i++)
            distance += distances[solution.path[i]][solution.path[i+1]];

        return distance;
    }
}

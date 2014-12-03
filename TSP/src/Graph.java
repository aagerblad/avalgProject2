import java.util.Arrays;

/**
 * Created by Andreas on 2014-11-27.
 */
public class Graph {

    private Node[] nodes;
    public double[][] distances;
    //public short[][] neighborhood;
    public int[][] neighbors;
    private int numNodes = 0;
    public int numNeighbors = 50;




    private class Node {
        public double x;
        public double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    //private class NeighborhoodNode implements Comparable<NeighborhoodNode> {
    //
    //    double distance;
    //    short id;
    //
    //    NeighborhoodNode(double distance, short id) {
    //        this.distance = distance;
    //        this.id = id;
    //    }
    //
    //    @Override
    //    public int compareTo(NeighborhoodNode o) {
    //        if (distance > o.distance)
    //            return 1;
    //        if (distance < o.distance)
    //            return -1;
    //        return 0;
    //    }
    //}

    public Graph(int n) {
        this.numNodes = n;
        this.distances = new double[n][n];
        //this.neighborhood = new short[n][n];
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

    public void getNeighbors() {
            if (numNeighbors >= numNodes)
                numNeighbors = numNodes-1;
            neighbors =  new int[numNodes][numNeighbors];


            double dist;
            for (int i = 0; i < numNodes; i++) {
                Arrays.fill(neighbors[i], -1);
                innerFor:
                for (int n = 0; n < numNodes; n++) {
                    if (i != n) {
                        dist = distances[i][n];

                        if (neighbors[i][numNeighbors -1] != -1 && dist > distances[i][neighbors[i][numNeighbors -1]])
                            continue;


                        int min=0, max, mid;
                        max = numNeighbors;

                        while(max-min <= 4) {
                            mid = (min + max)/2;
                            if (dist > distances[i][neighbors[i][mid]])
                                min = mid +1;
                            else
                                max = mid -1;
                        }

                        for (int j = min; j < max; j++) {

                            if (neighbors[i][j] == -1 || (distances[i][neighbors[i][j]] > dist)) {

                                int k = numNeighbors -1;
                                while (k > j) {
                                    neighbors[i][k] = neighbors[i][k-1];
                                    k--;
                                }
                                neighbors[i][j] = n;
                                continue innerFor;

                            }
                        }
                    }
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
        distance += distances[solution.path[0]][solution.path[solution.path.length-1]];
        return distance;
    }
}

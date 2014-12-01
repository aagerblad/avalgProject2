import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: tobbew92
 * Date: 30/11/14
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class Greedy implements TSPSolver {


    @Override
    public Solution solve(Graph graph, long time, long duration) {
        int n = graph.getNodes();
        Solution solution = new Solution(n);
        boolean[] used = new boolean[n];
        short best;

//        Random random = new Random();
//        solution.path[0] = 0;
//        solution.path[0] = (short) random.nextInt(n);
//        System.err.println("Path start: " + solution.path[0]);
        solution.path[0] = 11;
        used[11] = true;

        for (int i = 1; i < n; i ++) {
            best = -1;
            for (short j = 0; j < n; j ++) {
                if (!used[j] && (best == -1 || graph.distances[solution.path[i-1]][j] < graph.distances[solution.path[i-1]][best])) {

                    best = j;
                }
            }
            solution.path[i] = best;
            used[best] = true;

        }

        return solution;
    }
}

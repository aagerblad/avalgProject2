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

//          return newGreedy(graph, time, duration);
        int n = graph.getNodes();
        Solution solution = new Solution(n);

        if (n == 2) {
            solution.path = new short[] {0, 1};
            return solution;
        }

        if (n == 1) {
            solution.path = new short[] {0};
            return solution;
        }

        boolean[] used = new boolean[n];
        short best;

        short startNode = 0;
        used[startNode] = true;

        short curNode = startNode;

        for (int j = 0; j < n; j++) {
            int i;
            best = curNode;
            for (i = 0; i < n; i++) {
                if (!used[i] &&
                        (graph.distances[curNode][i] < graph.distances[curNode][best] || best == curNode))
                    best = (short)i;
            }
            used[best] = true;
            solution.path[curNode] = best;
            curNode = best;
        }
        solution.path[curNode] = startNode;

//        int startValue = 0;
////        Random random = new Random();
////        solution.path[0] = 0;
////        solution.path[0] = (short) random.nextInt(n);
////        System.err.println("Path start: " + solution.path[0]);
//
//
//
//
//        solution.set((short)startValue, 0); //[0] = (short) startValue;
//        used[startValue] = true;
//
//        for (int i = 1; i < n; i ++) {
//            best = -1;
//            for (short j = 0; j < n; j ++) {
//                if (!used[j] && (best == -1 || graph.distances[solution.path[i-1]][j] < graph.distances[solution.path[i-1]][best])) {
//
//                    best = j;
//                }
//            }
//            solution.set(best, i);
//            //solution.path[i] = best;
//            used[best] = true;
//
//        }

        return solution;
    }
}

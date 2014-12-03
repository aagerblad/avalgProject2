import java.util.*;

/**
 * Created by Andreas on 2014-12-01.
 */
public class TwoOpt implements TSPSolver{

//    private class Change implements Comparable<Change>{
//        int mini;
//        int minj;
//        double change;
//
//        Change (int mini, int minj, double change) {
//            this.mini = mini;
//            this.minj = minj;
//            this.change = change;
//        }
//
//        @Override
//        public int compareTo(Change o) {
//            if (change > o.change)
//                return 1;
//            if (change < o.change)
//                return -1;
//            return 0;
//        }
//    }

    public int m = 0;
    Solution currentSolution;

    public TwoOpt (Solution solution) {
        currentSolution = solution;
    }

    public TwoOpt (Solution solution, int m) {
        currentSolution = solution;
        this.m = m;
    }

    @Override
    public Solution solve(Graph graph, long startTime, long duration) {

        if (System.currentTimeMillis() - startTime > duration)
            return currentSolution;

        // TODO not n squared

        double bestChange;
        int mini = 0;
        int minj = 0;

        int N = currentSolution.path.length;

        int iterator = 0;
        do {
            iterator++;
            bestChange = 0;
            for (int t = 0; t < N; t++) {

                int i = (t + m) % N;

                short nodeI = currentSolution.path[i];
                short nodeInext = currentSolution.path[(i+1) % N];

                for (int u = 0; u < N-2; u++) {
                    if (System.currentTimeMillis() - startTime > duration)
                        return currentSolution;
                    int j = (u + t + 2 + m) % N;
                    short nodeJ = currentSolution.path[j];
                    short nodeJnext = currentSolution.path[(j+1) % N];

                    int curIDistance = graph.distances[nodeI][nodeInext];
                    int curJDistance = graph.distances[nodeJ][nodeJnext];

                    int newIJDistance = graph.distances[nodeI][nodeJ];
                    int newIJNextDistance = graph.distances[nodeInext][nodeJnext];

                    int change = newIJDistance + newIJNextDistance - (curIDistance + curJDistance);


                    if (change < bestChange) {
                        bestChange = change;
                        mini = i;
                        minj = j;
                        currentSolution.twoOptimization((short)mini, (short)minj); //blev ju bättre att bara ha den här

                    }
                }
            }

            //if (bestChange < 0)
            //    currentSolution.twoOptimization(mini, minj);

//            if (iterator > 50)
//                break;

        } while ((bestChange < 0 && System.currentTimeMillis() - startTime < duration));


        return currentSolution;
    }



}
 
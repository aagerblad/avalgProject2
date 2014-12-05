import java.util.*;

/**
 * Created by Andreas on 2014-12-01.
 */
public class TwoOpt implements TSPSolver{

    public int m = 0;
    int numNeighbors = 10;
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

        int N = currentSolution.path.length;
        if (N == 1)
            return currentSolution;

        int iterator = 0;
        do {
            iterator++;
            bestChange = 0;

            short curINode = 0;
            for (int i = 0; i < N; i++) {
                short nodeI = curINode;
                short nodeInext = currentSolution.path[nodeI];

                if (graph.getNodes() > 5) {
                    for (int k = 0; k < numNeighbors; k++) {
                        short nodeJ = graph.neighbors[nodeI][k];
                        short nodeJnext = currentSolution.path[nodeJ];

                        int curIDistance = graph.distances[nodeI][nodeInext];
                        int curJDistance = graph.distances[nodeJ][nodeJnext];

                        int newIJDistance = graph.distances[nodeI][nodeJ];
                        int newIJNextDistance = graph.distances[nodeInext][nodeJnext];

                        int change = newIJDistance + newIJNextDistance - (curIDistance + curJDistance);


                        if (change < 0.0) {
                            bestChange = change;
                            if (System.currentTimeMillis() - startTime > duration)
                                return currentSolution;
                            currentSolution.twoOptimization(nodeI, nodeJ); //blev ju bättre att bara ha den här
                        }

                    }

                } else {
                    short curJNode = currentSolution.path[nodeInext];
                    for (int j = 0; j < N-2; j++) {
                        short nodeJ = curJNode;
                        short nodeJnext = currentSolution.path[nodeJ];

                        int curIDistance = graph.distances[nodeI][nodeInext];
                        int curJDistance = graph.distances[nodeJ][nodeJnext];

                        int newIJDistance = graph.distances[nodeI][nodeJ];
                        int newIJNextDistance = graph.distances[nodeInext][nodeJnext];

                        int change = newIJDistance + newIJNextDistance - (curIDistance + curJDistance);


                        if (change < 0.0) {
                            bestChange = change;
                            if (System.currentTimeMillis() - startTime > duration)
                                return currentSolution;
                            currentSolution.twoOptimization(nodeI, nodeJ); //blev ju bättre att bara ha den här
                        }

                        curJNode = nodeJnext;
                    }

                }
                curINode = nodeInext;
            }



            //if (bestChange < 0)
            //    currentSolution.twoOptimization(mini, minj);

//            if (iterator > 50)
//                break;

        } while ((bestChange < 0 && System.currentTimeMillis() - startTime < duration));
//        } while ((bestChange < 0));


        return currentSolution;
    }



}
 
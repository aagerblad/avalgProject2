/**
 * Created with IntelliJ IDEA.
 * User: tobbew92
 * Date: 02/12/14
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class TwoHalfOpt implements TSPSolver{


    Solution currentSolution;

    public TwoHalfOpt (Solution solution) {
        currentSolution = solution;
    }


    @Override
    public Solution solve(Graph graph, long startTime, long duration) {
        Solution newSolution = currentSolution;

        boolean improvement = false;
        int length, opt;
        int N = newSolution.path.length;

        for (short t1 = 0; t1 < N; t1++)
        {
            if (System.currentTimeMillis() - startTime > duration)
                return currentSolution;
            short t2 = (short) ((t1+1) % N);
            short t3 = (short) ((t2+1) % N);
            short[] neighbours = graph.neighbors[newSolution.positions[t2]];
            for (short n = 0; n < graph.numNeighbors - 1; n++)
            {
                if (System.currentTimeMillis() - startTime > duration)
                    return currentSolution;
                short e1 = newSolution.positions[neighbours[n]];
                if (e1 == t1 || e1 == t2 || e1 == t3)
                    continue;

                short e2 = (short) ((e1+1) % N);

                if (e2 == t1 || e2 == t2 || e2 == t3)
                    continue;

                length = graph.distances[newSolution.path[t1]][newSolution.path[t2]] + graph.distances[newSolution.path[t2]][newSolution.path[t3]] + graph.distances[newSolution.path[e1]][newSolution.path[e2]];
                opt = graph.distances[newSolution.path[e2]][newSolution.path[t2]] + graph.distances[newSolution.path[t2]][newSolution.path[e1]] + graph.distances[newSolution.path[t1]][newSolution.path[t3]];


                if (opt < length) {
                    newSolution.moveNode(t2, e2);
                    improvement = true;
                    break;
                }
            }
        }
        if (improvement)
            return newSolution;
        else
            return currentSolution;
    }
}

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
    public Solution solve(Graph graph, long time, long duration) {

        return currentSolution;
    }
}

/**
 * Created by Andreas on 2014-11-27.
 */
public class Solution {

    public short[] path;

    public Solution(int nodes) {
        path = new short[nodes];
    }

    public void twoOptimization(int mini, int minj) {
        short[] newPath = path.clone();
        if (minj < mini) {
            for (int i = 0; i < path.length; i++) {
                newPath[i] = path[(i+mini)%path.length];
            }
        }

        for (int i = 0; i < minj-mini; i++) {
            newPath[mini+i+1] = path[minj-i];
        }

        path = newPath.clone();

    }
}

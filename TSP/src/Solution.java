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
        for (int i = 0; i < minj-mini; i++) {
            newPath[mini+i+1] = path[minj-i];
        }

        path = newPath.clone();

    }
}

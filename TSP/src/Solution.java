/**
 * Created by Andreas on 2014-11-27.
 */
public class Solution {

    public short[] path;
    public int[] positions;

    public Solution(int nodes) {
        path = new short[nodes];
        positions = new int[nodes];
    }

    public void set(short a, int index) {
        path[index] = a;
        positions[a] = index;
    }


    public void twoOptimization(int mini, int minj) {
        short[] newPath = path.clone();
        int[] newPos = positions.clone();
        if (minj < mini) {
            for (int i = 0; i < path.length; i++) {
                newPath[i] = path[(i+mini)%path.length];
                newPos[path[(i+mini)%path.length]] = i;
            }
        }

        for (int i = 0; i < minj-mini; i++) {
            newPath[mini+i+1] = path[minj-i];
            newPos[path[minj-i]] = mini+i+1;
        }

        path = newPath.clone();
        positions = newPos.clone();

    }
}

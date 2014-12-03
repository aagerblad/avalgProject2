/**
 * Created by Andreas on 2014-11-27.
 */
public class Solution {

    public short[] path;
    public short[] positions;

    public Solution(int nodes) {
        path = new short[nodes];
        positions = new short[nodes];
    }

    public void set(short a, int index) {
        path[index] = a;
        positions[a] = (short) index;
    }

    public void moveNode(short current, short target)
    {
        short node = path[current];

        if (current == target)
            return;
        else if (current > target) {
            for (short i = current; i > target; i--) {
                positions[path[i]] = positions[path[i-1]];
                path[i] = path[i - 1];

            }
            positions[node] = target;
            path[target] = node;
        } else {
            for (short i = current; i < target; i++) {
                positions[path[i]] = positions[i+1];
                path[i] = path[i + 1];

            }
            positions[node] = target;
            path[target] = node;
        }
    }


    public void twoOptimization(short mini, short minj) {
        short[] newPath = path.clone();
        short[] newPos = positions.clone();
        if (minj < mini) {
            for (short i = 0; i < path.length; i++) {
                newPath[i] = path[(i+mini)%path.length];
                newPos[path[(i+mini)%path.length]] = i;
            }
        }

        for (short i = 0; i < minj-mini; i++) {
            newPath[mini+i+1] = path[minj-i];
            newPos[path[minj-i]] = (short) (mini+i+1);
        }

        path = newPath.clone();
        positions = newPos.clone();

    }

}

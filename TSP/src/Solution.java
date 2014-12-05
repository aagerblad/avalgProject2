import java.util.Collections;
import java.util.LinkedList;

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

        LinkedList<Short> values = new LinkedList<Short>();
        short node = path[mini]; // 8
        short Jnext = path[minj]; // 3

        while(node != minj) {
            values.add(node);
            node = path[node];
        }

        // 8,5 -> 5,8
//        Collections.reverse(values);

        node = path[path[mini]];
        short newNode;
        while(node != minj) {
            newNode = path[node];
            path[node] = values.pop();
            node = newNode;
        }
        path[node] = values.pop();

        path[path[mini]] = Jnext;

        path[mini] = minj;

        positions = newPos.clone();

    }

}

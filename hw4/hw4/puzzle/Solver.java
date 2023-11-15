package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.Stack;
import java.util.*;

public class Solver {
    private List<WorldState> solution = new ArrayList<>();

    private class searchNode {
        private WorldState worldState;
        private int moves;
        private searchNode prev;

        public searchNode(WorldState worldState, int moves, searchNode prev) {
            this.moves = moves;
            this.prev = prev;
            this.worldState = worldState;
        }
    }

    private class NodeComparator implements Comparator<searchNode> {
        @Override
        public int compare(searchNode n1, searchNode n2) {
            int c1 = n1.moves + n1.worldState.estimatedDistanceToGoal();
            int c2 = n2.moves + n2.worldState.estimatedDistanceToGoal();
            return c1 - c2;
        }
    }
    public Solver(WorldState initial) {
        MinPQ<searchNode> pq = new MinPQ<>(new NodeComparator());
        searchNode curr = new searchNode(initial, 0, null);
        pq.insert(curr);
        while (!pq.isEmpty()) {
            curr = pq.delMin();
            if (curr.worldState.isGoal()) {
                break;
            }
            for (WorldState X : curr.worldState.neighbors()) {
                searchNode newNode = new searchNode(X, curr.moves + 1, curr);
                if (newNode.prev != null && newNode.worldState.equals(newNode.prev)) {
                    continue;
                }
                pq.insert(newNode);
            }
        }
        Stack<WorldState> path = new Stack<>();
        for (searchNode n = curr;  n != null ; n = n.prev) {
            path.push(n.worldState);
        }
        while (!path.isEmpty()) {
            solution.add(path.pop());
        }
    }

    public int moves() {
        return solution.size() - 1;
    }
    public Iterable<WorldState> solution() {
        return solution;
    }
}

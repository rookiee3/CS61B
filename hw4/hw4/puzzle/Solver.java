package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;


public class Solver {
    private List<WorldState> solution = new ArrayList<>();

    private class SearchNode {
        private WorldState worldState;
        private int moves;
        private SearchNode prev;

        public SearchNode(WorldState worldState, int moves, SearchNode prev) {
            this.moves = moves;
            this.prev = prev;
            this.worldState = worldState;
        }
    }

    private class NodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode n1, SearchNode n2) {
            int c1 = n1.moves + n1.worldState.estimatedDistanceToGoal();
            int c2 = n2.moves + n2.worldState.estimatedDistanceToGoal();
            return c1 - c2;
        }
    }
    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new NodeComparator());
        SearchNode curr = new SearchNode(initial, 0, null);
        pq.insert(curr);
        while (!pq.isEmpty()) {
            curr = pq.delMin();
            if (curr.worldState.isGoal()) {
                break;
            }
            for (WorldState X : curr.worldState.neighbors()) {
                SearchNode newNode = new SearchNode(X, curr.moves + 1, curr);
                if (newNode.prev != null && newNode.worldState.equals(newNode.prev)) {
                    continue;
                }
                pq.insert(newNode);
            }
        }
        Stack<WorldState> path = new Stack<>();
        for (SearchNode n = curr;  n != null ; n = n.prev) {
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

package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solver {
    private List<WorldState> solution = new ArrayList<>();
    private Map<WorldState, Integer> edtgCaches = new HashMap<>();

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
        public int compare(SearchNode o1, SearchNode o2) {
            int o1Edtg = getEdtg(o1);
            int o2Edtg = getEdtg(o2);
            int o1Priority = o1.moves + o1Edtg;
            int o2Priority = o2.moves + o2Edtg;
            return o1Priority - o2Priority;
        }

        private int getEdtg(SearchNode sn) {
            if (!edtgCaches.containsKey(sn.worldState)) {
                edtgCaches.put(sn.worldState, sn.worldState.estimatedDistanceToGoal());
            }
            return edtgCaches.get(sn.worldState);
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
        for (SearchNode n = curr;  n != null; n = n.prev) {
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

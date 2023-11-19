import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.Assert.*;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        if (items.isEmpty()) {
            return null;
        }
        Queue<Queue<Item>> res = new Queue<>();
        for (Item item : items) {
            Queue<Item> innerQuene = new Queue<>();
            innerQuene.enqueue(item);
            res.enqueue(innerQuene);
        }
        return res;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> res = new Queue<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            res.enqueue(getMin(q1, q2));
        }
        //q1 not empty
        while (!q1.isEmpty()) {
            res.enqueue(q1.dequeue());
        }
        //q2 not empty
        while (!q2.isEmpty()) {
            res.enqueue(q2.dequeue());
        }
        return res;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.isEmpty()) {
            return null;
        }
        if (items.size() == 1) {
            return items;
        }

        Queue<Queue<Item>> temp = makeSingleItemQueues(items);
        Queue<Item> que = mergeSortedQueues(temp.dequeue(), temp.dequeue());

        while (!temp.isEmpty()) {
            que = mergeSortedQueues(que, temp.dequeue());
        }
        for (int i = 0; i < items.size(); i++) {
            items.dequeue();
            items.enqueue(que.dequeue());
        }
        return items;
    }

    public static void main(String args[]) {
        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        mergeSort(students);
        while (!students.isEmpty()) {
            System.out.println(students.dequeue());
        }
    }

    @Test
    public void testMakeSingleItemQueuesQueue() {
        Queue<Integer> students = new Queue<>();
        students.enqueue(1);
        students.enqueue(2);
        students.enqueue(3);
        Queue<Queue<Integer>> temp = new Queue<>();
        temp = makeSingleItemQueues(students);
        for (int i = 0; i < 3; i++) {
            System.out.println(temp.dequeue().dequeue());
        }
    }

    @Test
    public void testMergeSortedQueues() {
        Queue<Integer> arr1 = new Queue<>();
        arr1.enqueue(1);
        arr1.enqueue(3);
        arr1.enqueue(5);
        Queue<Integer> arr2 = new Queue<>();
        arr2.enqueue(2);
        arr2.enqueue(4);
        arr2.enqueue(6);
        Queue<Integer> temp = mergeSortedQueues(arr1, arr2);
        int i = 1;
        for (Integer pt : temp) {
            assertEquals(pt, (Integer) i++);
        }
    }
}
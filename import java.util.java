import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class AcyclicGraph {

  public static boolean isAcyclic(HashMap<Integer, LinkedList<Integer>> graph) {
    // Calculate in-degree of each node
    HashMap<Integer, Integer> inDegree = new HashMap<>();
    for (int node : graph.keySet()) {
      inDegree.put(node, 0);
    }
    for (int source : graph.keySet()) {
      for (int neighbor : graph.get(source)) {
        inDegree.put(neighbor, inDegree.get(neighbor) + 1);
      }
    }

    // Queue to store nodes with zero in-degree
    Queue<Integer> queue = new LinkedList<>();
    for (int node : inDegree.keySet()) {
      if (inDegree.get(node) == 0) {
        queue.add(node);
      }
    }

    // BFS traversal
    int visitedNodes = 0;
    while (!queue.isEmpty()) {
      int currentNode = queue.poll();
      visitedNodes++;
      for (int neighbor : graph.get(currentNode)) {
        inDegree.put(neighbor, inDegree.get(neighbor) - 1);
        if (inDegree.get(neighbor) == 0) {
          queue.add(neighbor);
        }
      }
    }

    // Check if all nodes were visited
    return visitedNodes == graph.size();
  }

  public static void main(String[] args) {
    // Create a sample graph
    HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
    graph.put(0, new LinkedList<>());
    graph.put(1, new LinkedList<>());
    graph.put(2, new LinkedList<>());
    graph.put(3, new LinkedList<>());
    graph.get(1).add(3);
    graph.get(2).add(4);

    // Check if the graph is acyclic
    boolean isAcyclic = isAcyclic(graph);

    // Print the result
    System.out.println("Is the graph acyclic? " + isAcyclic);
  }
}

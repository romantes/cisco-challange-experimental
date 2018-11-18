package cisco.java.challenge;

import java.util.HashMap;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class GraphHelper {
  public static int[] generatePaths() {
    int [] result = {0, 0, 0, 0, 1, 1, 2, 2, 2};
    return result;
  }



  public static Graph buildReferenceGraph() {

    Graph graph = new Graph();
    graph.setGraphPaths(generatePaths());

    final Graph.Node a = graph.new Node("A");
    final Graph.Node b = graph.new Node("B");
    final Graph.Node c = graph.new Node("C");
    final Graph.Node d = graph.new Node("D");
    final Graph.Node e = graph.new Node("E");
    final Graph.Node f = graph.new Node("F");
    final Graph.Node g = graph.new Node("G");
    final Graph.Node h = graph.new Node("H");
    final Graph.Node i = graph.new Node("I");

    Map<Integer, Graph.Node> nodes = new HashMap<Integer, Graph.Node>(){{
        put(0, a);
        put(1, b);
        put(2, c);
        put(3, d);
        put(4, e);
        put(5, f);
        put(6, g);
        put(7, h);
        put(8, i);
    }};
    graph.setNodes(nodes);
    graph.setEntryIdx(9);
    return graph;
  }

    /*
    build graph as in example:
    A
    |-B
    | |-E
    | |-F
    |
    |
    |-C
    | |-G
    | |-H
    | |-I
    |
    |-D

    */

   static Graph buidActualGraph() {
    Graph graph = new Graph(9);
    Graph.Node A = graph.new Node("A");
    Graph.Node B = graph.new Node("B");
    Graph.Node C = graph.new Node("C");
    Graph.Node D = graph.new Node("D");
    Graph.Node E = graph.new Node("E");
    Graph.Node F = graph.new Node("F");
    Graph.Node G = graph.new Node("G");
    Graph.Node H = graph.new Node("H");
    Graph.Node I = graph.new Node("I");

    graph.setRoot(A);
    graph.add(A, B);
    graph.add(A, C);
    graph.add(A, D);
    graph.add(B, E);
    graph.add(B, F);
    graph.add(C, G);
    graph.add(C, H);
    graph.add(C, I);

    return graph;
  }

  // 2 graphs can be concidered as equals when they
  // contain the same array and hash map
  static boolean isGraphsEquals(Graph g1, Graph g2) {
    boolean arrEquals = false;
    boolean keySetEquals = false;
    boolean entrySetEquals = false;

    int length = g1.getGraphPaths().length - 1;
    
    for (int i = 0; i < length; i++) {
      if (g1.getGraphPaths()[i] == g2.getGraphPaths()[i]) {
        arrEquals = true;
      } else {
        arrEquals = false;
    }

    }

    for (Map.Entry<Integer, Graph.Node> entry : g1.getNodes().entrySet()) {
      int key = entry.getKey();
      if (keySetEquals = g2.getNodes().containsKey(key)) {
        keySetEquals = true;
      } else {
        keySetEquals = false;
      }
    }

    entrySetEquals = g1.getNodes().equals(g2.getNodes());
    
    return arrEquals && keySetEquals && entrySetEquals;
  }
}

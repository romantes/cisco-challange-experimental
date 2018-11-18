package cisco.java.challenge;

import static cisco.java.challenge.GraphHelper.buidActualGraph;
import static cisco.java.challenge.GraphHelper.buildReferenceGraph;
import static cisco.java.challenge.GraphHelper.isGraphsEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphBuldFromScratchTest {

  Graph graph;

  @Before
  public void setUp() {
    graph = new Graph();
  }

  @Test
  public void addNode2level() {
    Graph.Node parent = graph.new Node("rootNode");
    Graph.Node child = graph.new Node("child");
    graph.setRoot(parent);
    graph.add(parent, child);
    int expected = 1;
    int actual = graph.getNodeIdx(child);
    assertEquals(expected, actual);
  }

  @Test
  public void addNode2levelCheckArrayState() {
    Graph.Node parent = graph.new Node("rootNode");
    Graph.Node child = graph.new Node("child");
    graph.setRoot(parent);
    graph.add(parent, child);
    boolean actual = false;
    int[] expectedArray = {0, 0};
    for (int i = 0; i <= 1; i++) {
      if (expectedArray[i] == graph.getGraphPaths()[i]) {
        actual = true;
      } else {
        actual = false;
      }
    }
    assertTrue(actual);
  }

  @Test
  public void compareGraphWithReferenceGraphTest() {
    Graph actual = buidActualGraph();
    Graph expected = buildReferenceGraph();
    assertTrue(isGraphsEquals(actual, expected));
  }

  @Test
  public void getChildrenTest() {
    graph = buidActualGraph();
    GNode[] expected = {graph.new Node("B"),
            graph.new Node("C"),
            graph.new Node("D")
    };
    GNode node = graph.getNodes().get(0);
    GNode[] actual = node.getChildren();
    assertArrayEquals(expected, actual);
  }

  @Test
  public void getChildrenIdxTest() {
    graph = buidActualGraph();

    List<Integer> expected = new ArrayList() {{
      add(1);
      add(2);
      add(3);
    }};

    Graph.Node node = graph.getNodes().get(0);
    List<Integer> actual = node.getChildrenIdxs();
    assertEquals(expected, actual);
  }

  @Test
  public void countChildrenTest() {
    graph = buildReferenceGraph();
    Graph.Node root = graph.getNodes().get(1);
    int expected = 2;
    int actual = root.countChildren();
    assertEquals(expected, actual);
  }

  @Test
  public void pathsTest() {
    graph = buidActualGraph();

    Graph.Node A = graph.new Node("A");
    Graph.Node B = graph.new Node("B");
    Graph.Node C = graph.new Node("C");
    Graph.Node D = graph.new Node("D");
    Graph.Node E = graph.new Node("E");
    Graph.Node F = graph.new Node("F");
    Graph.Node G = graph.new Node("G");
    Graph.Node H = graph.new Node("H");
    Graph.Node I = graph.new Node("I");

    final List<GNode> path1 = new ArrayList<GNode>();
    path1.add(A);
    path1.add(B);
    path1.add(E);

    List<GNode> path2 = new ArrayList<GNode>();
    path2.add(A);
    path2.add(B);
    path2.add(F);

    List<GNode> path3 = new ArrayList<GNode>();
    path3.add(A);
    path3.add(C);
    path3.add(G);

    List<GNode> path4 = new ArrayList<GNode>();
    path4.add(A);
    path4.add(C);
    path4.add(H);

    List<GNode> path5 = new ArrayList<GNode>();
    path5.add(A);
    path5.add(C);
    path5.add(I);

    List<GNode> path6 = new ArrayList<GNode>();
    path6.add(A);
    path6.add(D);


    List<List<GNode>> expected = new ArrayList<List<GNode>>();
    expected.add(path1);
    expected.add(path2);
    expected.add(path3);
    expected.add(path4);
    expected.add(path5);
    expected.add(path6);

    List<List<GNode>> actual = graph.paths(graph.getNodes().get(0));
    assertEquals(expected, actual);
  }

  @Test
  public void findFirstOccurOfParentIdx1() {
    graph = buidActualGraph();
    int expected = 4;
    int actual = graph.findFirstOccurOfParentIdx(0,1);
    assertEquals(expected, actual);
  }

  @Test
  public void findFirstOccurOfParentIdx2() {
    graph = buidActualGraph();
    int expected = 0;
    int actual = graph.findFirstOccurOfParentIdx(0,0);
    assertEquals(expected, actual);
  }

  @Test
  public void raverseTest1() {
    graph = buidActualGraph();
    List<GNode> expected = new ArrayList<>();
    expected.add(graph.new Node("B"));
    expected.add(graph.new Node("E"));
    List<GNode> actual = graph.traverse(0);
    assertEquals(expected,actual);
  }


}
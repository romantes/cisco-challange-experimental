package cisco.java.challenge;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GraphRootTest {
  Graph graph;

  @Before
  public void setUp() {
    graph = new Graph();
  }

  @Test
  public void addRootArrayTest() {
    graph.setRoot(graph.new Node("name0"));
    int expected = 0;
    int actual = graph.getGraphPaths()[0];
    assertEquals(expected, actual);
  }

  @Test
  public void addObjectTest() {
    Graph.Node expected = graph.new Node ("name1");
    graph.setRoot(expected);
    Graph.Node actual = graph.getNodes().get(0);
    assertEquals(expected, actual);
  }

  @Test
  public void capacityEmptyGraphTest() {
    graph.isTresholdReached();
  }

  @Test
  public void entryIdx() {
    int expected = 1;
    graph.setRoot(graph.new Node ("name1"));
    int actual = graph.getEntryIdx();
    assertEquals(expected, actual);
  }

  @Test
  public void getIndexWhenEmptyTest() {
    int expected = -1;
    Graph.Node node = graph.new Node ("name2");
    int actual  = graph.getNodeIdx(node);
    assertEquals(expected, actual);
  }

  @Test
  public void getIndexWhenNodeIsRoot() {
    int expected = 0;
    Graph.Node node = graph.new Node ("name3");
    graph.setRoot(node);
    int actual = graph.getNodeIdx(node);
    assertEquals(expected, actual);
  }

  @Test
  public void entryIdxOnEmpty() {
    int expected = 0;
    int actual = graph.getEntryIdx();
    assertEquals(expected, actual);
  }
}

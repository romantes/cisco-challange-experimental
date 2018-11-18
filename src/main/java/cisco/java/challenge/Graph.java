package cisco.java.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph implements GGraph{
  public static int INITIAL_CAPACITY = 32;
  public static double CAPACITY_TRESHOLD = 0.75;

  private int[] graphPaths;
  private Map<Integer, Node> nodes;

  boolean isEmpty;
  private int entryIdx;

  public class Node implements GNode {
    private String name;

    public Node(String name) {
      this.name = name;
    }
    public String getName() {
      return null;
    }

    public GNode[] getChildren() {
      GNode [] children = new GNode [countChildren()];
      int thisNodeIdx = getNodeIdx(this);
      List<Integer> childrenIdxs = getChildrenIdxs();

      if (childrenIdxs.size() == children.length) {
        for (int i = 0; i < childrenIdxs.size(); i++) {
          int idx = childrenIdxs.get(i);
          GNode node = nodes.get(idx);
          children[i] = node;
        }
      }
      return children;
    }

    List<Integer> getChildrenIdxs() {
      ArrayList<Integer> idxs = new ArrayList<Integer>();
      int thisNodeIdx = getNodeIdx(this);
      for (int i = 0; i < graphPaths.length - 1; i++) {
        if(graphPaths[i] == i) continue;
        if (graphPaths[i] == thisNodeIdx) {
          idxs.add(i);
        }
      }
      return idxs;
    }

    int countChildren() {
      int result = 0;
      int thisNodeIdx = getNodeIdx(this);
      for (int i = 0; i < graphPaths.length -1; i++) {
        if(graphPaths[i] == i) continue;
        if(graphPaths[i] == thisNodeIdx) result++;
      }
      return result;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Node node = (Node) o;

      return name.equals(node.name);
    }

    @Override
    public int hashCode() {
      return name.hashCode();
    }
  }

  public Graph() {
    nodes = new HashMap<Integer,Node>(INITIAL_CAPACITY);
    graphPaths = new int [INITIAL_CAPACITY];
    isEmpty = true;
    entryIdx = 0;
  }

  public Graph(int capacity) {
    nodes = new HashMap<Integer,Node>(capacity);
    graphPaths = new int [capacity];
    isEmpty = true;
    entryIdx = 0;
  }

  public Graph(Node root) {
    this();
    nodes.put(0, root);
    graphPaths [0] = 0;
  }

  public int[] getGraphPaths() {
    return graphPaths;
  }

  public void setGraphPaths(int[] graphPaths) {
    this.graphPaths = graphPaths;
  }

  public Map<Integer, Node> getNodes() {
    return nodes;
  }

  public void setNodes(Map<Integer, Node> nodes) {
    this.nodes = nodes;
  }

  public int getEntryIdx() {
    return entryIdx;
  }

  public void setEntryIdx(int entryIdx) {
    this.entryIdx = entryIdx;
  }

  public boolean isEmpty() {
    return isEmpty;
  }

  public void setEmpty(boolean empty) {
    isEmpty = empty;
  }

  public ArrayList walkGraph(GNode node) {
    return null;
  }

  public List<List<GNode>> paths(GNode node) {
    List<List<GNode>> result = new ArrayList<List<GNode>>();
    int nodeIdx = getNodeIdx((Node) node);
    int occurIdx = findFirstOccurOfParentIdx(0,nodeIdx);

    List<GNode> path = new ArrayList<>();
    path.add(nodes.get(occurIdx));

    return result;
  }

  public void add(Node parent, Node child) {
    // TODO corner cases
    // if(graphPaths.length == 0)
    // null parameter value
    int parentIdx = getNodeIdx(parent);
    graphPaths[entryIdx] = parentIdx;
    nodes.put(entryIdx, child);
    entryIdx++;
  }

  void setRoot(Node node) {
    nodes.put(0, node);
    entryIdx++;
  }

  boolean isTresholdReached() {
    return (double)entryIdx/graphPaths.length >= CAPACITY_TRESHOLD;
  }

  // if there is no element method will return -1
  public int getNodeIdx(Node node) {
    int result = -1;
    for (Map.Entry<Integer, Node> val : nodes.entrySet()) {
      if(val.getValue().equals(node)) result = val.getKey();
    }
    return result;
  }


  int findFirstOccurOfParentIdx(int lastidx ,int parent) {
    for (int i = lastidx; i < graphPaths.length; i++) {
      if(graphPaths[i] == parent) return i;
    }
    return parent;
  }

  List<GNode> traverse(int idx) {
    List<GNode> path = new ArrayList<>();
    int i = idx;
    int parent = idx;
    while (i < graphPaths.length) {
      while (i < graphPaths.length && graphPaths[i] != parent) {
        i++;
      }
      path.add(nodes.get(i));
      parent = i;
    }
    return path;
  }
}

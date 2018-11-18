package cisco.java.challenge;

import java.util.List;

public interface GGraph {
  List walkGraph(GNode node);
  List<List<GNode>> paths(GNode node);
}

package models;

// TODO : static
public class Lookup {
  public final static int LIGRA = 0;
  public final static int MMAP = 1;
  public final static int X_STREAM = 2;
  public final static int GRAPH_CHI = 3;

  public final static int BFS = 0;
  public final static int PAGE_RANK = 1;
  public final static int CONNECTED_COMPONENTS = 2;
  public final static int TRIANGLE_COUNTING = 3;

  public final static int NB_FRAMEWORKS = 4;
  public final static int NB_ALGORITHMS = 4;

  public final static String[][] lookup =
      new String[NB_FRAMEWORKS][NB_ALGORITHMS];
  static {
    lookup[LIGRA][BFS] = "BFS";
    lookup[LIGRA][PAGE_RANK] = "PageRank";
    lookup[LIGRA][CONNECTED_COMPONENTS] = "Components";
    lookup[LIGRA][TRIANGLE_COUNTING] = "Triangle";

    lookup[MMAP][BFS] = "";
    lookup[MMAP][PAGE_RANK] = "PageRank";
    lookup[MMAP][CONNECTED_COMPONENTS] = "ConnectedComponent";
    lookup[MMAP][TRIANGLE_COUNTING] = "PageRank";

    lookup[GRAPH_CHI][BFS] = "";
    lookup[GRAPH_CHI][PAGE_RANK] = "pagerank";
    lookup[GRAPH_CHI][CONNECTED_COMPONENTS] = "connectedcomponents";
    lookup[GRAPH_CHI][TRIANGLE_COUNTING] = "trianglecounting";

    lookup[X_STREAM][BFS] = "bfs";
    lookup[X_STREAM][PAGE_RANK] = "pagerank";
    lookup[X_STREAM][CONNECTED_COMPONENTS] = "cc";
    lookup[X_STREAM][TRIANGLE_COUNTING] = "triangles";
  }
}

package aoc;

import java.util.*;

public class Graph implements Comparable<Graph> {
  int elevation;
  List<Graph> neighbours;
  int distance = Integer.MAX_VALUE;

  public Graph(char elevation) {
    this.elevation = (int) (elevation-'a');
    neighbours = new ArrayList<Graph>();
  }

  public void addNeighbour(Graph neighbour) {
    if (neighbour.elevation - elevation < 2)
      neighbours.add(neighbour);
  }

  public static int dijkstra(Graph start, Graph end) {
    if (start == end) {
      return 0;
    }

    Set<Graph> visited = new HashSet<Graph>();
    PriorityQueue<Graph> nodes = new PriorityQueue<Graph>();
    nodes.add(start);
    start.distance = 0;

    while (!nodes.isEmpty()) {
      Graph current = nodes.poll();
      if (current == end) {
        return end.distance;
      }

      for (Graph neighbour : current.neighbours) {
        if (!visited.contains(neighbour)) {
          nodes.add(neighbour);
          visited.add(neighbour);
          neighbour.distance = Math.min(neighbour.distance,
              current.distance + 1);
        }
      }
    }
    return end.distance;
  }

  public int compareTo(Graph g) {
    return Integer.compare(distance, g.distance);
  }

}

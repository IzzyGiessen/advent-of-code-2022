package aoc;

import java.util.List;
import java.util.ArrayList;

public class Valve implements Comparable<Valve> {
  private int flow;
  private List<String> neighbours;
  public String id;
  public int dist;
  public String parent;

  public Valve(String id, int flow) {
    this.id = id;
    this.flow = flow;
    neighbours = new ArrayList();
    dist = Integer.MAX_VALUE/2;
  }

  public void addNeighbour(String v) {
    neighbours.add(v);
  }

  public List<String> getNeighbours() {
    return neighbours;
  }

  public int getFlow() {
    return flow;
  }

  public void setFlow(int flow) {
    this.flow = flow;
  }

  public static Valve readLine(String line) {
    String[] split = line.split("; ");
    String id = split[0].substring(6, 8);
    int f = Integer.parseInt(split[0].split("=")[1]);
    String[] neighbs = split[1].substring(22).split(" ");
    Valve v = new Valve(id, f);
    for (String neighb : neighbs) {
      if (neighb.length() == 0) {
        continue;
      }
      v.addNeighbour(neighb.substring(0, 2));
    }
    return v;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (!(o instanceof Valve v)) return false;
    return id.equals(v.id);
  }

  @Override
  public int compareTo(Valve v) {
    return Integer.compare(dist, v.dist);
  }

}

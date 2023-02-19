package aoc;

import java.util.*;


public class Volcano {
  private Map<String, Valve> valves;
  private int idCounter;
  private Map<String, Map<Set<String>, Map<Integer, Integer>>> pressures;
  private Map<String, Map<String, Path>> paths;
  private Set<String> all;

  public Volcano() {
    valves = new HashMap();
    pressures = new HashMap();
    paths = new HashMap();
  }

  public void addValve(Valve v) {
    valves.put(v.id, v);
  }
  public int getPressure() {
    int i = 1;
    for (Valve s : valves.values()) {
      fillPaths(s);
    }
    setAll();
    return getPressure("AA", new HashSet<String>(), 30);
  }

  public int getPressure(String current, Set<String> visited, int time) {
    Valve curr = valves.get(current);
    if (time < 2) return 0;
    if (time == 2) return curr.getFlow();
    if (time > 24) System.out.println("Yee");
    Set<String> vis = new HashSet<>(visited);
    vis.add(current);

    if (pressures.get(current) == null) pressures.put(current, new HashMap());
    if (pressures.get(current).get(visited) == null)
      pressures.get(current).put(visited, new HashMap());
    if (pressures.get(current).get(visited).containsKey(time)) {
      return pressures.get(current).get(visited).get(time);
    }

    int startTime = time;
    int ret = 0;
    if (!current.equals("AA")) {
      time--;
      ret = (time) * curr.getFlow();
    }
    int max = ret;
    Map<String, Path> pths = paths.get(current);
    Set<String> filtered = filterPaths(pths, vis);
    for (String valve : filtered) {
      max = Math.max(max, ret + getPressure(valve, vis,
            time - paths.get(current).get(valve).dist));
    }
    pressures.get(current).get(visited).put(startTime, max);
    return max;
  }

  private void fillPaths(Valve a) {
    paths.put(a.id, new HashMap());
    for (Valve valve : valves.values()) {
      valve.dist = Integer.MAX_VALUE/4;
      valve.parent = null;
    }

    a.dist = 0;
    PriorityQueue<Valve> pq = new PriorityQueue();
    pq.add(a);
    Set<String> visited = new HashSet();

    while (!pq.isEmpty()) {
      Valve v = pq.poll();
      visited.add(v.id);

      for (String neigh : v.getNeighbours()) {
        if (visited.contains(neigh)) continue;
        if (valves.get(neigh).dist > v.dist+1) {
          valves.get(neigh).parent = v.id;
          valves.get(neigh).dist = v.dist+1;
        }
        pq.add(valves.get(neigh));
      }
    }

    for (Valve b : valves.values()) {
      if (a == b) continue;
      LinkedList<String> path = new LinkedList();
      Valve parent = b;
      while (parent != null) {
        if (parent.getFlow() != 0)
          path.add(parent.id);
        parent = valves.get(parent.parent);
      }
      Collections.reverse(path);
      paths.get(a.id).put(b.id, new Path(path, b.dist));
    }
  }

  private void setAll() {
    all = new HashSet();
    for (Valve v : valves.values()) {
      if (v.getFlow() != 0) all.add(v.id);
    }
  }

  private Set<String> filterLinkedList(LinkedList<String> path) {
    Set<String> filtered = new HashSet();
    int max = 0;
    for (String s : path) {
      max = Math.max(max, valves.get(s).getFlow());
      if (valves.get(s).getFlow() != max) {
        filtered.add(s);
      }
    }
    return filtered;
  }

  private Set<String> filterPaths(Map<String, Path> pths,
      Set<String> ignore) {
    Set<String> res = new HashSet(all);
    for (Path path : pths.values()) {
      LinkedList<String> p = new LinkedList(path.path);
      for (String ign : ignore) {
        p.remove(ign);
      }
      Set<String> filtered = filterLinkedList(p);
      res.removeAll(filtered);
    }
    for (String ign : ignore) {
      res.remove(ign);
    }
    return res;
  }


  private class Path {
    LinkedList<String> path;
    int dist;

    public Path(LinkedList<String> path, int dist) {
      this.path = path;
      this.dist = dist;
    }
  }

}

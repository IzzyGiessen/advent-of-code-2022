package aoc;

import java.util.*;


public class Volcano {
  private Map<String, Valve> valves;
  private int idCounter;
  private Map<String, Map<Set<String>, Map<Integer, Integer>>> pressures;
  private Map<String, Map<String, Path>> paths;
  private Set<String> all;
  private int count = 0;

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
    LinkedList<String> list = new LinkedList();
    list.offer("AA");
    int res = force(list, 30, Math.min(15, all.size()));

    System.out.println(count);
    return res;
  }

  private int force(LinkedList<String> vis, int t, int size) {
    if (size == 0 || t <= 2) {
      count++;
      int time = 30;
      int score = 0;
      LinkedList<String> copy = new LinkedList(vis);
      String previous = copy.poll();
      LinkedList<Integer> scores = new LinkedList();
      while (!copy.isEmpty()) {
        String next = copy.poll();
        int dist = paths.get(previous).get(next).dist;
        // minus distance and 1 to open the valve
        time -= dist + 1;
        if (time <= 0) break;
        int add = time * valves.get(next).getFlow();
        score += add;
        previous = next;
      }
      return score;
    }
    int max = 0;
    String last = vis.getLast();
    Set<String> filtered = filterPaths(paths.get(last), new HashSet(vis));
    for (String s : filtered) {
      if (vis.contains(s)) continue;
      vis.offer(s);
      max = Math.max(max, force(vis, t-paths.get(last).get(s).dist-1, size-1));
      vis.pollLast();
    }
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

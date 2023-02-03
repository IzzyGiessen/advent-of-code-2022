package aoc;

import java.util.*;

public class Scan {
  private Map<Integer, TreeSet<Integer>> columns;
  private Point sand;
  private int count;
  private boolean rest;

  public Scan() {
    columns = new HashMap<>();
    sand = new Point(500, 0);
  }

  public int getCount() {
    return count;
  }

  public void drawLine(Point a, Point b) {
    if (a == null || b == null) return;
    if (a.x == b.x) {
      if (columns.get(a.x) == null) {
        TreeSet<Integer> ts = new TreeSet<Integer>(Collections.reverseOrder());
        columns.put(a.x, ts);
      }
      for (int y = Math.min(a.y, b.y); y <= Math.max(a.y, b.y); y++) {
        columns.get(a.x).add(y);
      }
    } else {
      for (int x = Math.min(a.x, b.x); x <= Math.max(a.x, b.x); x++) {
        if (columns.get(x) == null) {
          TreeSet<Integer> ts = new TreeSet<Integer>(Collections.reverseOrder());
          columns.put(x, ts);
        }
        columns.get(x).add(a.y);
      }
    }
  }

  public boolean step() {
    TreeSet<Integer> column = columns.get(sand.x);
    if (column == null || column.lower(sand.y) == null)
      return false;
    sand.y = column.lower(sand.y) - 1;
    if (!move()) {
      columns.get(sand.x).add(sand.y);
      sand.x = 500;
      sand.y = 0;
      count++;
    }
    return true;
  }

  public boolean move() {
    Integer left = columns.get(sand.x-1) == null ?
      sand.y+2 : columns.get(sand.x-1).lower(sand.y) == null ?
      sand.y+2 : columns.get(sand.x-1).lower(sand.y);
    Integer right = columns.get(sand.x+1) == null ?
      sand.y+2 : columns.get(sand.x+1).lower(sand.y) == null ?
      sand.y+2 : columns.get(sand.x+1).lower(sand.y);
    if (left == null || left > sand.y+1) {
      sand.x -= 1;
      return true;
    } else if (right == null || right > sand.y+1) {
      sand.x += 1;
      return true;
    } else {
      return false;
    }
  }
}

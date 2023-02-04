package aoc;

import java.util.*;

public class Scan {
  private Map<Integer, TreeSet<Integer>> columns;
  private Point sand;
  private int count;
  private boolean rest;
  private int floor;

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

  public void drawGround() {
    int lowest = 0;
    for (int col : columns.keySet()) {
      if (columns.get(col).first() > lowest) {
        lowest = columns.get(col).first();
      }
    }
    this.floor = lowest + 2;
    for (int col : columns.keySet()) {
      columns.get(col).add(floor);
    }
  }

  public boolean step() {
    TreeSet<Integer> column = columns.get(sand.x);
    if (column == null || column.lower(sand.y) == null) {
      column = new TreeSet<Integer>(Collections.reverseOrder());
      column.add(floor);
      columns.put(sand.x, column);
    }
    sand.y = column.lower(sand.y) - 1;
    if (!move()) {
      count++;
      if (sand.x == 500 && sand.y == 0) return false;
      //System.out.println(sand.x + ", " + sand.y);
      columns.get(sand.x).add(sand.y);
      sand.x = 500;
      sand.y = 0;
    }
    return true;
  }

  public boolean move() {
    TreeSet<Integer> leftColumn = columns.get(sand.x-1);
    TreeSet<Integer> rightColumn = columns.get(sand.x+1);
    if (leftColumn == null) {
      leftColumn = new TreeSet<Integer>(Collections.reverseOrder());
      leftColumn.add(floor);
      columns.put(sand.x-1, leftColumn);
    }
    if (rightColumn == null) {
      rightColumn = new TreeSet<Integer>(Collections.reverseOrder());
      rightColumn.add(floor);
      columns.put(sand.x+1, rightColumn);
    }
    int left = leftColumn.lower(sand.y);
    int right = rightColumn.lower(sand.y);
    if (left > sand.y+1) {
      sand.x -= 1;
      return true;
    } else if (right > sand.y+1) {
      sand.x += 1;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    //sb.append("   123456789");
    for (int i = 0; i <= floor; i++) {
      sb.append(i + "  ");
      for (int col : columns.keySet()) {
        if (columns.get(col).contains(i)) {
          sb.append("#");
        } else {
          sb.append(".");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}

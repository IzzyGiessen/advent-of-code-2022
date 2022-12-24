package aoc;

import java.util.HashSet;
import java.util.Set;

public class Rope {
  public Point head;
  public Point[] tails;
  public Set<Point> travelled;

  public Rope(int tailCount) {
    head = new Point(0, 0);
    tails = new Point[tailCount];
    for (int i = 0; i < tailCount; i++) {
      tails[i] = new Point(0, 0);
    }
    travelled = new HashSet<Point>();
    travelled.add(new Point(0, 0));
  }

  public void step(int steps, char direction) {
    if (steps == 0) return;
    for (int i = 0; i < steps; i++) {
      switch (direction) {
        case 'L':
          head.x -= 1;
          break;
        case 'U':
          head.y += 1;
          break;
        case 'R':
          head.x += 1;
          break;
        case 'D':
          head.y -= 1;
          break;
      }
      for (int j = 0; j < tails.length; j++) {
        follow(tails[j], j);
      }
    }
  }

  public void follow(Point tail, int index) {
    Point h;
    if (index == 0) h = head;
    else h = tails[index-1];

    if (Math.abs(h.x - tail.x) > 1) {
      tail.x += (h.x - tail.x) / Math.abs(h.x - tail.x);
      if (Math.abs(h.y - tail.y) >= 1) {
        tail.y += (h.y - tail.y) / Math.abs(h.y - tail.y);
      }
    }
    if (Math.abs(h.y - tail.y) > 1) {
      tail.y += (h.y - tail.y) / Math.abs(h.y - tail.y);
      if (Math.abs(h.x - tail.x) >= 1) {
        tail.x += (h.x - tail.x) / Math.abs(h.x - tail.x);
      }
    }
    if (index == tails.length-1) {
      Point p = new Point(tail.x, tail.y);
      travelled.add(p);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Head: " + head + "\nTails: ");
    for (int i = 0; i < tails.length; i++) {
      sb.append(tails[i] + ", ");
    }
    return sb.toString();
  }
}

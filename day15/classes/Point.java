package aoc;

public class Point {
  public int x;
  public int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int distance(Point p) {
    return Math.abs(x - p.x) + Math.abs(y - p.y);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (!(o instanceof Point p)) return false;
    return x == p.x && y == p.y;
  }

}

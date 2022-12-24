package aoc;

public class Point {
  public int x;
  public int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (!(o instanceof Point p)) return false;
    return x == p.x && y == p.y;
  }

  @Override
  public int hashCode() {
    return 31 * x + y;
  }
}

package aoc;

public class Signal {
  private Pair<Packet> pair;

  public Signal(Packet left, Packet right) {
    pair = new Pair<Packet>(left, right);
  }

  public boolean isOrdered() {
    return pair.left.compareTo(pair.right) <= 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(pair.left.toString());
    sb.append(pair.right.toString());
    sb.append("\n");
    return sb.toString();
  }

  private class Pair<T> {
    private T left;
    private T right;

    public Pair(T left, T right) {
      this.left = left;
      this.right = right;
    }
  }
}

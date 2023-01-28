package aoc;

public class IntPacket extends Packet {
  private int value;

  public IntPacket(int i) {
    value = i;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value + ",";
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (!(o instanceof IntPacket p)) return false;
    return value == p.value;
  }

  @Override
  public int hashCode() {
    return value;
  }
}

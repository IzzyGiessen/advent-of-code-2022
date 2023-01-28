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
}

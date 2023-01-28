package aoc;

import java.util.List;
import java.util.ArrayList;

import java.lang.StringBuilder;

public class ListPacket extends Packet {
  private List<Packet> content;

  public ListPacket() {
    content = new ArrayList<Packet>();
  }

  public ListPacket(int i) {
    content = new ArrayList<Packet>();
    addValue(i);
  }

  public ListPacket(List content) {
    this.content = content;
  }

  public void addValue(int i) {
    content.add(new IntPacket(i));
  }

  public void addList(ListPacket list) {
    content.add(list);
  }

  public int size() {
    return content.size();
  }

  public Packet get(int i) {
    return content.get(i);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (Packet p : content) {
      sb.append(p.toString());
    }
    sb.append("],");
    return sb.toString();
  }
}

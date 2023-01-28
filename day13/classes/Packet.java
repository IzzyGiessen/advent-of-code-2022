package aoc;

public class Packet implements Comparable<Packet> {

  public int compareTo(Packet p) {
    if (p instanceof IntPacket io) {
      if (this instanceof IntPacket it) {
        return Integer.compare(it.getValue(), io.getValue());
      } else {
        ListPacket lt = (ListPacket) this;
        return lt.compareTo(new ListPacket(io.getValue()));
      }
    } else {
      ListPacket lo = (ListPacket) p;
      if (this instanceof IntPacket it) {
        return (new ListPacket(it.getValue())).compareTo(lo);
      } else {
        int res = 0;
        ListPacket lt = (ListPacket) this;

        for (int i = 0; i < Math.min(lt.size(), lo.size()); i++) {
          res = lt.get(i).compareTo(lo.get(i));

          if (res != 0)
            break;
        }
        if (res == 0)
          res = Integer.compare(lt.size(), lo.size());
        return res;
      }
    }
  }

  @Override
  public String toString() {
    if (this instanceof ListPacket l) {
      return l.toString();
    }
    return ((IntPacket) this).toString();
  }
}

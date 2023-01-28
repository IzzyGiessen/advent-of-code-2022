package aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader( new FileInputStream(
            new File("input/input")
          )
        )
      );
    String line = br.readLine();

    List<Packet> packets = new ArrayList<Packet>();
    do {
      if (line.equals(""))
        continue;

      Stack<ListPacket> lists = new Stack<ListPacket>();
      ListPacket currentList = new ListPacket();
      String num = "";
      for (char c : line.toCharArray()) {
        if (c == '[') {
          lists.push(currentList);
          currentList = new ListPacket();
          lists.peek().addList(currentList);
        } else if (c == ']') {
          if (num.length() > 0) {
            currentList.addValue(Integer.parseInt(num));
            num = "";
          }
          currentList = lists.pop();
        } else if (c == ',') {
          if (num.length() > 0) {
            currentList.addValue(Integer.parseInt(num));
            num = "";
          }
        } else {
          num += (int) (c - '0');
        }
      }
      ListPacket fullList = (ListPacket) currentList.get(0);
      packets.add(fullList);
    } while ((line = br.readLine()) != null);

    Collections.sort(packets);
    ListPacket div1 = new ListPacket();
    div1.addList(new ListPacket(2));
    ListPacket div2 = new ListPacket();
    div2.addList(new ListPacket(6));
    int index1 = packets.indexOf(div1) + 1;
    int index2 = packets.indexOf(div2) + 1;
    System.out.println(index1 * index2);
  }

}

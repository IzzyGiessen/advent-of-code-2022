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

    List<Signal> signals = new ArrayList<Signal>();
    List<Packet> pair = new ArrayList<Packet>();
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
      pair.add(fullList);

      if (pair.size() == 2) {
        signals.add(new Signal(pair.get(0), pair.get(1)));
        pair.clear();
      }
    } while ((line = br.readLine()) != null);

    int sum = 0;
    for (int i = 0; i < signals.size(); i++) {
      Signal signal = signals.get(i);
      System.out.println(signal);
      sum += signal.isOrdered() ? i+1 : 0;
    }
    System.out.println(sum);
  }

}

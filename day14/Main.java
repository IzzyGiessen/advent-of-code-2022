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

    Scan map = new Scan();
    do {
      String[] coords = line.split(" -> ");
      Point a = null;
      Point b = null;
      for (String coord : coords) {
        String[] c = coord.split(",");
        b = a;
        a = new Point(Integer.parseInt(c[0]),
            Integer.parseInt(c[1]));
        map.drawLine(a, b);
      }
    } while ((line = br.readLine()) != null);

    while(map.step()) {
    }
    System.out.println(map.getCount());
  }

}

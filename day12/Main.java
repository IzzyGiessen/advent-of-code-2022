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

    Graph start = new Graph('a');
    Graph end = new Graph('z');
    List<List<Graph>> map = new ArrayList<List<Graph>>();
    do {
      List<Graph> row = new ArrayList<Graph>();
      for (char c : line.toCharArray()) {
        if (c == 'S') {
          row.add(start);
        } else if (c == 'E') {
          row.add(end);
        } else {
          row.add(new Graph(c));
        }
      }
      map.add(row);
    } while ((line = br.readLine()) != null);

    for (int y = 0; y < map.size(); y++) {
      for (int x = 0; x < map.get(0).size(); x++) {
        if (y > 0)
          map.get(y).get(x).addNeighbour(map.get(y-1).get(x));
        if (y < map.size()-1)
          map.get(y).get(x).addNeighbour(map.get(y+1).get(x));
        if (x > 0)
          map.get(y).get(x).addNeighbour(map.get(y).get(x-1));
        if (x < map.get(0).size()-1)
          map.get(y).get(x).addNeighbour(map.get(y).get(x+1));
      }
    }

    System.out.println(Graph.dijkstra(start, end));
  }

}

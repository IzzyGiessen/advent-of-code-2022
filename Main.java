import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import java.io.File;
import java.io.FileInputStream;

public class Main {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File("input/input.txt")
          )
        )
      );

    String line = br.readLine();

    PriorityQueue<Integer> top = new PriorityQueue<>();
    top.add(0);
    top.add(0);
    top.add(0);

    int current = 0;
    while ((line = br.readLine()) != null) {
      if(line.equals("")) {
        int min = top.poll();
        top.offer(Math.max(min, current));
        current = 0;
      } else {
        current += Integer.parseInt(line);
      }
    }

    int sum = 0;
    while (!top.isEmpty()) {
      sum += top.poll();
    }

    System.out.println(sum);

  }

}

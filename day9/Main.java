package aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File("input/input")
          )
        )
      );
    String line = br.readLine();
    Rope rope = new Rope(9);

    do {
      char direction = line.charAt(0);
      int steps = Integer.parseInt(line.substring(2));
      rope.step(steps, direction);
    } while ((line = br.readLine()) != null);

    System.out.println(rope.travelled.size());
  }
}

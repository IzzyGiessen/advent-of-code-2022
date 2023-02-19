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

    Volcano volcano = new Volcano();
    do {
      Valve v = Valve.readLine(line);
      volcano.addValve(v);
    } while ((line = br.readLine()) != null);

    System.out.println(volcano.getPressure());
  }

}
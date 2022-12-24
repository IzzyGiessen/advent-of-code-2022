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

    InstructionList program = new InstructionList();
    do {
      program.addInstruction(program.create(line));
    } while ((line = br.readLine()) != null);

    long sum = 0;
    int time = 0;
    while (program.hasNext() && time < 240) {
      program.next();
      if (time % 40 == 0) {
        System.out.println("");
      }
      if (Math.abs((program.x) - (time % 40)) < 2) {
        System.out.print("#");
      } else {
        System.out.print(".");
      }
      //System.out.println(time + ": " + program.x);
      time++;
    }
  }
}

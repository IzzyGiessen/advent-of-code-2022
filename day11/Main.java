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
        new InputStreamReader(
          new FileInputStream(
            new File("input/input")
          )
        )
      );
    String line = br.readLine();
    KeepAway game = new KeepAway();
    Set<Integer> tests = new HashSet<Integer>();

    do {
      Monkey m = new Monkey();
      while ((line = br.readLine()) != null &&
          !line.equals("")) {
        tests.add(m.readLine(line));
      }
      game.addMonkey(m);
    } while ((line = br.readLine()) != null);
    tests.remove(0);
    long lcm = lcm(tests);

    game.addLCM(lcm);

    int rounds = 10000;
    for (int i = 0; i < rounds; i++) {
      game.playRound();
    }

    System.out.println(game);
  }

  private static long lcm(long a, long b) {
    return a * (b / gcd(a, b));
  }

  private static long lcm(Set<Integer> input) {
    long result = input.iterator().next();
    for(long inp : input)
      result = lcm(result, inp);
    return result;
  }

  private static long gcd(long a, long b) {
    while (b > 0) {
      long temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }
}

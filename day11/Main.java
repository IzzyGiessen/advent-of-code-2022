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
    KeepAway game = new KeepAway();

    do {
      Monkey m = new Monkey();
      while ((line = br.readLine()) != null &&
          !line.equals("")) {
        m.readLine(line);
      }
      game.addMonkey(m);
    } while ((line = br.readLine()) != null);

    for (int i = 0; i < 20; i++) {
      game.playRound();
    }

    System.out.println(game);
  }
}

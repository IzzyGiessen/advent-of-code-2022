import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;

public class Main {
  private static int[][] table = new int[][]{
    {4, 8, 3},
    {1, 5, 9},
    {7, 2, 6}
  };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File("input/input.txt")
          )
        )
      );

    String line = br.readLine();

    int score = 0;
    do {
      char opp = line.charAt(0);
      char me = line.charAt(2);
      score += play(opp, me);
    } while ((line = br.readLine()) != null);

    System.out.println(score);
  }

  private static int play(char opp, char me) {
    return table[opp - 'A'][me-'X'];
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File("input/input.txt")
          )
        )
      );

    String line = br.readLine();

    int sum = 0;
    do {
      int length = line.length();
      Set<Character> set = new HashSet<>();
      for (int i = 0; i < length/2; i++) {
        set.add(line.charAt(i));
      }
      for (int i = length/2; i < length; i++) {
        if (set.contains(line.charAt(i))) {
          char c = line.charAt(i);
          if (c - 'A' < 26) {
            sum += c - 'A' + 27;
          } else {
            sum += c - 'a' + 1;
          }
          break;
        }
      }
    } while ((line = br.readLine()) != null);

    System.out.println(sum);

  }

}

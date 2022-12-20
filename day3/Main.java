import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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
    int counter = 0;
    List<Set<Character>> sets = new ArrayList<>();
    do {
      Set<Character> set = new HashSet<>();
      for (char c : line.toCharArray()) {
        set.add(c);
      }
      sets.add(set);

      if (++counter % 3 == 0) {
        Set<Character> s = sets.get(0);
        for (int i = 1; i < sets.size(); i++) {
          s.retainAll(sets.get(i));
        }
        char c = s.iterator().next();
        if (c - 'A' < 26) {
          sum += c - 'A' + 27;
        } else {
          sum += c - 'a' + 1;
        }
        sets.clear();
      }

    } while ((line = br.readLine()) != null);

    System.out.println(sum);

  }

}

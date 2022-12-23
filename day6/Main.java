import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File("input/input.txt")
          )
        )
      );

    int c = br.read();

    int count = 0;
    int sequence = 14;
    LinkedHashSet<Character> q = new LinkedHashSet<Character>();

    do {
      count++;
      char ch = (char) c;
      if (q.contains(ch)) {
        Iterator<Character> it = q.iterator();
        while (it.hasNext()) {
          char next = it.next();
          if (next == ch) {
            q = new LinkedHashSet<Character>();
            while (it.hasNext()) {
              q.add(it.next());
            }
            break;
          }
        }
      }
      q.add(ch);

      if (q.size() == sequence) {
        System.out.println(count);
        return;
      }

    } while ((c = br.read()) != -1);

  }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;

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
    LinkedList<Character> q = new LinkedList<Character>();

    do {
      if (q.size() < 4) {
        q.offer((char) c);
      } else {
        if ((new HashSet<Character>(q)).size() == q.size()) {
          System.out.println(count);
          return;
        }
        q.poll();
        q.offer((char) c);
      }

      count++;

    } while ((c = br.read()) != -1);

  }

}

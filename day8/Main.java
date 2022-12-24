import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

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
    List<Tree> forest = new ArrayList<Tree>();
    int x;

    do {
      x = line.length();

      for (char c : line.toCharArray()) {
        Tree left = forest.size() % x != 0 ? forest.get(forest.size()-1) : null;
        Tree top = forest.size() >= x ? forest.get(forest.size()-x) : null;
        Tree current = new Tree(c - '0', left, top);
        if (left != null) left.right = current;
        if (top != null) top.bottom = current;
        forest.add(current);
      }
    } while ((line = br.readLine()) != null);

    for (Tree t : forest) {
      t.lookLeft(); t.lookUp();
    }
    for (int i = forest.size()-1; i >= 0; i--) {
      Tree t = forest.get(i);
      t.lookRight(); t.lookDown();
    }
    int sum = 0;
    for (int i = 0; i < forest.size(); i++) {
      sum += forest.get(i).isVisible() ? 1 : 0;
    }
    System.out.println(sum);
  }
}

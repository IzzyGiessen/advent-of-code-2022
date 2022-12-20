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
            new File("input/input.txt")
          )
        )
      );

    String line = br.readLine();

    int total = 0;
    do {
      String[] pair = line.split(",");
      String[] left = pair[0].split("-");
      String[] right = pair[1].split("-");
      int[] l = new int[] {
        Integer.parseInt(left[0]),
        Integer.parseInt(left[1])
      };
      int[] r = new int[] {
        Integer.parseInt(right[0]),
        Integer.parseInt(right[1])
      };
      if (isContained(l, r)) {
        total++;
      }
    } while ((line = br.readLine()) != null);

    System.out.println(total);
  }


  public static boolean isContained(int[] a, int[] b) {
    return (a[0] >= b[0] && a[1] <= b[1]) ||
        (a[0] <= b[0] && a[1] >= b[1]);
  }

}

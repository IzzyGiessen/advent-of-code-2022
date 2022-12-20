import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;

public class Main {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File("input/input.txt")
          )
        )
      );

    String line = br.readLine();

    int max = 0;
    int current = 0;
    while ((line = br.readLine()) != null) {
      if(line.equals("")) {
        if (current > max) {
          max = current;
        }
        current = 0;
      } else {
        current += Integer.parseInt(line);
      }
    }

    System.out.println(max);

  }

}

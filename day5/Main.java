import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class Main {

  // count the amount of piles before initializing
  private static Stack<Character>[] piles;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File("input/input.txt")
          )
        )
      );

    String line = br.readLine();
    boolean loadCrates = false;

    Stack<String> layers = new Stack<>();
    do {
      layers.push(line);
    } while (!(line = br.readLine()).equals(""));

    String[] numbers = layers.pop().split(" ");
    piles = (Stack<Character>[]) new Stack[Integer.parseInt(
        numbers[numbers.length-1]
    )];
    for (int i = 0; i < piles.length; i++) {
      piles[i] = new Stack<Character>();
    }

    while(!layers.isEmpty()) {
      String layer = layers.pop();
      findCrates(layer);
    }

    while ((line = br.readLine()) != null) {
      String[] order = line.split(" ");
      int move = Integer.parseInt(order[1]);
      int from = Integer.parseInt(order[3]) - 1;
      int to = Integer.parseInt(order[5]) - 1;

      for (int i = 0; i < move; i++) {
        piles[to].push(piles[from].pop());
      }
    }

    for (Stack<Character> pile : piles) {
      System.out.print(pile.pop());
    }
  }

  public static void findCrates(String line) {
    int totalIndex = 0;
    int index = line.indexOf('[');
    while (index != -1) {
      index += 1;
      char crate = line.charAt(index);
      line = line.substring(index);
      totalIndex += index;
      Stack<Character> pile = piles[(totalIndex-1)/4];
      if (pile == null) {
        pile = new Stack<>();
        piles[(totalIndex-1)/4] = pile;
      }
      pile.push(crate);
      index = line.indexOf('[');
    }
  }

  public static void printStackList(Stack[] s) {
    System.out.println(s);
    for (Stack<Character> pile : s) {
      for (char c : pile) {
        System.out.print(c + " ");
      }
      System.out.println("");
    }
  }
}

package aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    Tree root = new Tree("/");
    Tree node = root;

    do {
      if (line.charAt(0) == '$') {
        // command
        if (line.charAt(2) == 'c') {
          String dir = line.substring(5);
          if (dir.equals("/")) {
            node = root;
          } else if (dir.equals("..")) {
            node = node.parent;
          }
          else {
            node = node.children.get(dir);
          }
        }
      } else if (line.charAt(0) == 'd') {
        // directory
        String dir = line.substring(4);
        node.addChild(dir);
      } else {
        // file
        String[] file = line.split(" ");
        long size = Long.parseLong(file[0]);
        String fileName = file[1];
        node.addChild(new Tree(fileName, size, node));
      }
    } while ((line = br.readLine()) != null);

    Set<Tree> smallest = new HashSet<Tree>();
    traverse(root, smallest, 100000);
    long sum = 0;
    for (Tree t : smallest) {
      sum += t.size;
    }
    System.out.println(sum);
  }

  public static long traverse(Tree node, Set<Tree> smallest, long limit) {
    if (node.children.size() == 0) return node.size;
    for (String child : node.children.keySet()) {
      node.size += traverse(node.children.get(child), smallest, limit);
    }
    if (node.size <= limit) {
      smallest.add(node);
    }
    return node.size;
  }
}

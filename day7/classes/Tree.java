package aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {
  public String name;
  public Map<String, Tree> children;
  public Tree parent;
  public long size;

  public Tree(String name) {
    this.name = name;
    children = new HashMap<String, Tree>();
    size = 0;
  }

  public Tree(String name, Tree parent) {
    this.name = name;
    this.parent = parent;
    children = new HashMap<String, Tree>();
    size = 0;
  }

  public Tree(String name, long size) {
    this.name = name;
    this.size = size;
    children = new HashMap<String, Tree>();
  }

  public Tree(String name, long size, Tree parent) {
    this.name = name;
    this.size = size;
    this.parent = parent;
    children = new HashMap<String, Tree>();
  }

  public void addChild(String c) {
    if (children.get(c) == null) {
      children.put(c, new Tree(c, this));
    }
  }

  public void addChild(Tree t) {
    if (children.get(t.name) == null) {
      children.put(t.name, t);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name);
    sb.append(": \n");
    for (String child : children.keySet()) {
      sb.append(children.get(child));
    }

    return indent(sb.toString(), 3);
  }

  private String indent(String str, int n) {
    char[] chars = new char[n];
    Arrays.fill(chars, ' ');
    return str.replaceAll("(?m)^", new String(chars));
  }
}

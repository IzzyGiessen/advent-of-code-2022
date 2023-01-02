package aoc;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Monkey implements Comparable<Monkey> {
  private static int idCounter;

  private int inspections;
  private int id;
  private LinkedList<Long> items;
  private String operation;
  private int test;
  private int ifTrue;
  private int ifFalse;

  public Monkey() {
    id = idCounter++;
    items = new LinkedList<Long>();
  }

  public void setItems(int[] items) {
    for (int item : items) {
      this.items.add((long)item);
    }
  }

  public void addItem(long item) {
    items.offer(item);
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public void setTest(int test) {
    this.test = test;
  }

  public void setIfTrue(int ifTrue) {
    this.ifTrue = ifTrue;
  }

  public void setIfFalse(int ifFalse) {
    this.ifFalse = ifFalse;
  }

  public void readLine(String line) {
    String[] segments = line.split(": ");
    if (segments[0].contains("items")) {
      String[] numbers = segments[1].split(", ");
      int[] its = new int[numbers.length];
      int i = 0;
      for (String number : numbers) {
        its[i++] = Integer.parseInt(number);
      }
      setItems(its);
    } else if (segments[0].contains("Operation")) {
      operation = segments[1].substring(6);
    } else if (segments[0].contains("Test")) {
      test = Integer.parseInt(segments[1].substring(13));
    } else if (segments[0].contains("true")) {
      ifTrue = Integer.parseInt(segments[1].substring(16));
    } else if (segments[0].contains("false")) {
      ifFalse = Integer.parseInt(segments[1].substring(16));
    }
  }

  public long[] getThrow() {
    if (isEmpty()) {
      throw new NullPointerException();
    }
    long[] thr = new long[2];
    long item = operate(items.poll());

    thr[0] = (item = item / 3);
    thr[1] = item % test == 0 ? ifTrue : ifFalse;

    return thr;
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  private long operate(long a) {
    inspections++;
    String[] segs = operation.split(" ");
    char operator = segs[1].charAt(0);
    if (segs[2].equals("old")) {
      if (operator == '+') {
        return a * 2;
      } else if (operator == '*') {
        return a * a;
      }
    } else {
      int b = Integer.parseInt(segs[2]);
      if (operator == '+') {
        return a + b;
      } else if (operator == '*') {
        return a * b;
      }
    }
    return -1;
  }

  public int getInspections() {
    return inspections;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Monkey ");
    sb.append(id);
    sb.append(": ");
    sb.append(inspections);
    sb.append("\n");
    return sb.toString();
  }

  @Override
  public int compareTo(Monkey m) {
    return Integer.compare(m.inspections, inspections);
  }
}

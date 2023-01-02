package aoc;

import java.util.ArrayList;
import java.util.List;

import java.util.PriorityQueue;

public class KeepAway {
  private List<Monkey> monkeys;

  public KeepAway() {
    monkeys = new ArrayList<Monkey>();
  }

  public void addMonkey(Monkey m) {
    monkeys.add(m);
  }

  public void playRound() {
    for (Monkey monkey : monkeys) {
      while (!monkey.isEmpty()) {
        long[] move = monkey.getThrow();
        monkeys.get((int)move[1]).addItem(move[0]);
       // System.out.println("Round " + 0 + ": " +
       //     "Move " + move[0] + " to " + move[1] + ".");
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Monkey m : monkeys) {
      sb.append(m.toString());
      sb.append("\n");
    }
    sb.append("\nMultiplication of 2 greatest inspectors: ");
    PriorityQueue<Monkey> pq = new PriorityQueue<Monkey>(monkeys);
    sb.append(pq.poll().getInspections() * pq.poll().getInspections());
    return sb.toString();
  }
}

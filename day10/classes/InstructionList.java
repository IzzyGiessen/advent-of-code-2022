package aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;


public class InstructionList {
  public int x;
  public int time; public Queue<Instruction> instructions;
  public InstructionList() {
    x = 1;
    time = 0;
    instructions = new LinkedList<Instruction>();
  }

  public Instruction create(String instr) {
    String[] parts = instr.split(" ");
    if (parts[0].equals("noop")) {
      return new NoopInstruction(1);
    } else if (parts[0].equals("addx")) {
      int addition = Integer.parseInt(parts[1]);
      return new AddInstruction(2, addition);
    }
    return new NoopInstruction(0);
  }

  public void addInstruction(Instruction instr) {
    instructions.add(instr);
  }

  public void next() {
    if (instructions.peek().length == 0) {
      instructions.poll().execute();
    }

    time++;
    if (hasNext()) {
      instructions.peek().length--;
    }
  }

  public boolean hasNext() {
    return instructions.size() > 0;
  }


  public class AddInstruction extends Instruction {
    private int length;
    private int add;

    public AddInstruction(int length, int addition) {
      super(length);
      this.add = addition;
    }

    @Override
    public void execute() {
      x += add;
    }
  }


  public class NoopInstruction extends Instruction {
    public int length;

    public NoopInstruction(int length) {
      super(length);
    }

    @Override
    public void execute() {}

  }
}

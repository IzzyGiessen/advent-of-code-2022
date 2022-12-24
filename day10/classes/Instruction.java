package aoc;

public abstract class Instruction {
  public int length;

  public Instruction(int length) {
    this.length = length;
  }

  public void execute() {}

}

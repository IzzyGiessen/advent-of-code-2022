public class Test {

  public static void main(String[] args) {
    System.out.println(fac(20));
  }

  private static long fac(int i) {
    if (i == 1) {
      return 1;
    }
    return (long)(i * fac(i-1));
  }

}

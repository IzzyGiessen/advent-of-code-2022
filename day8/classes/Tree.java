public class Tree {
  public int height;
  public Tree left, right, top, bottom;
  public boolean l, r, t, b;

  public Tree(int height, Tree left, Tree top) {
    this.height = height;
    this.left = left;
    this.top = top;

    if (left == null) l = true;
    if (top == null) t = true;
    if (right == null) r = true;
    if (bottom == null) b = true;
  }

  public void lookLeft() {
    if (left == null) return;
    while (!left.l) {
      left = left.left;
    }
    l = height > left.height;
  }

  public void lookUp() {
    if (top == null) return;
    while (!top.t) {
      top = top.top;
    }
    t = height > top.height;
  }

  public void lookRight() {
    if (right == null) return;
    while (!right.r) {
      right = right.right;
    }
    r = height > right.height;
  }

  public void lookDown() {
    if (bottom == null) return;
    while (!bottom.b) {
      bottom = bottom.bottom;
    }
    b = height > bottom.height;
  }

  public boolean isVisible() {
    return l || r || t || b;
  }

  @Override
  public String toString() {
    return String.valueOf(isVisible());
  }
}

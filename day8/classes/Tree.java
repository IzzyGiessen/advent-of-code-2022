public class Tree {
  public int height;
  public Tree left, right, top, bottom;
  public boolean l, r, t, b;
  public int ll, rr, tt, bb;

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
    Tree tree = left;
    while (!tree.l && height > tree.height) {
      tree = tree.left;
      ll++;
    }
    ll++;
    l = height > tree.height;
    ll += l ? tree.ll : 0;
  }

  public void lookUp() {
    if (top == null) return;
    Tree tree = top;
    while (!tree.t && height > tree.height) {
      tree = tree.top;
      tt++;
    }
    tt++;
    t = height > tree.height;
    tt += t ? tree.tt : 0;
  }

  public void lookRight() {
    if (right == null) return;
    Tree tree = right;
    while (!tree.r && height > tree.height) {
      tree = tree.right;
      rr++;
    }
    rr++;
    r = height > tree.height;
    rr += r ? tree.rr : 0;
  }

  public void lookDown() {
    if (bottom == null) return;
    Tree tree = bottom;
    while (!tree.b && height > tree.height) {
      tree = tree.bottom;
      bb++;
    }
    bb++;
    b = height > tree.height;
    bb += b ? tree.bb : 0;
  }

  public boolean isVisible() {
    return l || r || t || b;
  }

  public int getScenicScore() {
    return ll * rr * tt * bb;
  }

  @Override
  public String toString() {
    return String.valueOf(getScenicScore());
  }
}

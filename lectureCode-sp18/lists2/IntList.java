public class IntList {
  public int first;
  public IntList rest;

  public IntList(int f, IntList r) {
    first = f;
    rest = r;
  }

  /**
   * Return the size of the list using... recursion!
   */
  public int size() {
    if (rest == null) {
      return 1;
    }
    return 1 + this.rest.size();
  }

  /**
   * Return the size of the list using no recursion!
   */
  public int iterativeSize() {
    IntList p = this;
    int totalSize = 0;
    while (p != null) {
      totalSize += 1;
      p = p.rest;
    }
    return totalSize;
  }

  /**
   * Returns the ith item of this IntList.
   */
  public int get(int i) {
    if (i == 0) {
      return first;
    }
    return rest.get(i - 1);
  }

  public void addAdjacent() {
    IntList p = this;
    while (p.rest != null) {
      if (p.first == p.rest.first) {
        p.first *= 2;
        p.rest = p.rest.rest;
      } else {
        p = p.rest;
      }
    }
  }

  public void print() {
    IntList p = this;
    while (p != null) {
      System.out.print(p.first + " ");
      p = p.rest;
    }
    System.out.println();
  }

  public void squareAdd(int x) {
    IntList p = this;
    while (true) {
      if (p.rest == null) {
        p.rest = new IntList(p.first * p.first, null);
        p.rest.rest = new IntList(x, null);
        break;
      } else {
        IntList tmp = new IntList(p.first * p.first, p.rest);
        p.rest = tmp;
        p = tmp.rest;
      }
    }
  }

  public static void main(String[] args) {
    IntList L = new IntList(15, null);
    L = new IntList(10, L);
    L = new IntList(5, L);

    System.out.println(L.get(1));

    L = new IntList(3, null);
    L = new IntList(2, L);
    L = new IntList(1, L);
    L = new IntList(1, L);

    L.addAdjacent();
    L.print();

    L = new IntList(2, null);
    L = new IntList(1, L);
    L.print();

    L.squareAdd(5);
    L.print();

    L.squareAdd(7);
    L.print();
  }
}
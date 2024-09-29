public class LinkedListDeque<T> {
  private class Node {
    private T item;
    private Node next;
    private Node prev;

    public Node(T item, Node next, Node prev) {
      this.item = item;
      this.next = next;
      this.prev = prev;
    }
  }

  private Node sentinel;
  private int size;

  /**
   * Creates an empty linked list deque.
   */
  public LinkedListDeque() {
    sentinel = new Node(null, null, null);
    sentinel.prev = sentinel;
    sentinel.next = sentinel;
    size = 0;
  }

  /**
   * Creates a deep copy of `other`.
   */
  // public LinkedListDeque(LinkedListDeque<T> other) {
  // sentinel = new Node(null, null, null);
  // sentinel.prev = sentinel;
  // sentinel.next = sentinel;
  // size = 0;
  //
  // Node prev = sentinel;
  // Node p = other.sentinel.next;
  // while (p != other.sentinel) {
  // prev.next = new Node(p.item, null, prev);
  // prev = prev.next;
  // p = p.next;
  // size++;
  // }
  // prev.next = sentinel;
  // sentinel.prev = prev;
  // }

  /**
   * Gets the item at the given index, where 0 is the front,
   * 1 is the next item, and so forth. If no such item exists,
   * returns null. Must not alter the deque!
   */
  public T get(int index) { // `get` must use iteration, not recursion.
    if (size == 0 || index < 0) {
      return null;
    }

    if (index >= size) {
      return null;
    }

    int cnt = 0;
    Node p = sentinel;
    while (cnt <= index) {
      p = p.next;
      cnt++;
    }
    return p.item;
  }

  /**
   * Helper function for `getRecursive`
   */
  private T nakedGetRecursive(int index, Node p) {
    if (index == 0) {
      return p.item;
    }

    return nakedGetRecursive(index - 1, p.next);
  }

  /**
   * Same as get, but uses recursion.
   */
  public T getRecursive(int index) {
    if (size == 0 || index < 0) {
      return null;
    }

    if (index >= size) {
      return null;
    }

    Node p = sentinel.next;
    return nakedGetRecursive(index, p);
  }

  /**
   * Returns true if deque is empty, false otherwise.
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the number of items in the deque.
   */
  public int size() { // `size` must take constant time.
    return size;
  }

  /**
   * Prints the items in the deque from first to last,
   * separated by a space. Once all the items have
   * been printed, print out a new line.
   */
  public void printDeque() {
    Node p = sentinel.next;
    while (p != sentinel) {
      System.out.print(p.item);
      System.out.print(" ");
      p = p.next;
    }
    System.out.println();
  }

  /**
   * Adds an item of type T to the front of the deque.
   */
  public void addFirst(T item) {
    /**
     * `add` and `remove` operations must not involve any
     * looping or recursion. A single such operation must
     * take “constant time”, i.e. execution time should
     * not depend on the size of the deque.
     */
    Node p = new Node(item, sentinel.next, sentinel);
    sentinel.next = p;
    p.next.prev = p;
    size++;
  }

  /**
   * Adds an item of type T to the back of the deque.
   */
  public void addLast(T item) {
    Node p = new Node(item, sentinel, sentinel.prev);
    sentinel.prev = p;
    p.prev.next = p;
    size++;
  }

  /**
   * Removes and returns the item at the front of the deque.
   * If no such item exists, returns null.
   */
  public T removeFirst() {
    if (size == 0) {
      return null;
    }

    Node p = sentinel.next;
    sentinel.next = sentinel.next.next;
    sentinel.next.prev = sentinel;
    size--;
    return p.item;
  }

  /**
   * Removes and returns the item at the back of the deque.
   * If no such item exists, returns null.
   */
  public T removeLast() {
    if (size == 0) {
      return null;
    }

    Node p = sentinel.prev;
    sentinel.prev = sentinel.prev.prev;
    sentinel.prev.next = sentinel;
    size--;
    return p.item;
  }
}

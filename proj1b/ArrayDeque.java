public class ArrayDeque<T> implements Deque<T> {
  private int size;
  private T[] items;
  private int front;
  private int rear;
  private double usageRatio;

  private void calcUsageRatio() {
    usageRatio = (double) size / items.length;
  }

  private int defaultInitSize = 8;

  public ArrayDeque() {
    front = 0;
    rear = 0;
    items = (T[]) new Object[defaultInitSize];
    size = 0;
    calcUsageRatio();
  }

  /**
   * Gets the item at the given index, where 0 is the front,
   * 1 is the next item, and so forth. If no such item exists,
   * returns null. Must not alter the deque!
   */
  @Override
  public T get(int index) {
    // `get` and `size` must take constant time.
    if (index >= size || index < 0) {
      return null;
    }
    return items[(front + index + items.length) % items.length];
  }

  /**
   * Returns the number of items in the deque.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns true if deque is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Prints the items in the deque from first to last,
   * separated by a space. Once all the items have
   * been printed, print out a new line.
   */
  @Override
  public void printDeque() {
    int p = front;
    while (p != rear) {
      System.out.print(items[p]);
      p = (p + 1 + items.length) % items.length;
    }
    System.out.println();
  }

  private int resizeFactor = 2;
  private double minUsageRatio = 0.25;

  private void resizeDeque() {
    if (usageRatio >= 1) {
      T[] tmp = (T[]) new Object[items.length * resizeFactor];
      System.arraycopy(items, front, tmp, tmp.length - (items.length - front), items.length - front);
      System.arraycopy(items, 0, tmp, 0, rear);
      front = tmp.length - (items.length - front);
      items = tmp;
    }

    if (usageRatio < minUsageRatio && items.length > defaultInitSize) {
      T[] tmp = (T[]) new Object[items.length / resizeFactor];
      if (front < rear) {
        System.arraycopy(items, front, tmp, 0, rear - front + 1);
        rear = rear - front;
        front = 0;
      }
      if (front > rear) {
        System.arraycopy(items, front, tmp, tmp.length - (items.length - front), items.length - front);
        System.arraycopy(items, 0, tmp, 0, rear);
        front = tmp.length - (items.length - front);
      }
      items = tmp;
    }

    calcUsageRatio();
  }

  /**
   * Adds an item of type T to the front of the deque.
   */
  @Override
  public void addFirst(T item) {
    /**
     * `add` and `remove` must take constant time, except during resizing operations.
     */
    front = (front - 1 + items.length) % items.length;
    items[front] = item;
    size++;
    calcUsageRatio();
    resizeDeque();
  }

  /**
   * Adds an item of type T to the back of the deque.
   */
  @Override
  public void addLast(T item) {
    items[rear] = item;
    rear = (rear + 1 + items.length) % items.length;
    size++;
    calcUsageRatio();
    resizeDeque();
  }

  /**
   * Removes and returns the item at the front of the deque.
   * If no such item exists, returns null.
   */
  @Override
  public T removeFirst() {
    if (size == 0) {
      return null;
    }

    T tmp = items[front];
    items[front] = null;
    front = (front + 1 + items.length) % items.length;
    size--;
    calcUsageRatio();
    resizeDeque();
    return tmp;
  }

  /**
   * Removes and returns the item at the back of the deque.
   * If no such item exists, returns null.
   */
  @Override
  public T removeLast() {
    if (size == 0) {
      return null;
    }

    rear = (rear - 1 + items.length) % items.length;
    T tmp = items[rear];
    items[rear] = null;
    size--;
    calcUsageRatio();
    resizeDeque();
    return tmp;
  }
}

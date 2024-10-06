import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
  private void compEntireDeque(
      StudentArrayDeque<Integer> stuDeque, ArrayDequeSolution<Integer> stdDeque,
      String mesg
  ) {
    for (int i = 0; i < stdDeque.size(); i++) {
      Integer expect = stdDeque.get(i);
      Integer actual = stuDeque.get(i);

      assertEquals(mesg, expect, actual);
    }
  }

  private void testAddFirstOnce(
      StudentArrayDeque<Integer> stuDeque, ArrayDequeSolution<Integer> stdDeque,
      StringBuilder mesg
  ) {
    int item = StdRandom.uniform(10);
    stdDeque.addFirst(item);
    stuDeque.addFirst(item);

    mesg.append("addFirst(").append(item).append(")\n");
    String mesg_str = mesg.toString();

    assertEquals(mesg_str, stdDeque.size(), stuDeque.size());

    compEntireDeque(stuDeque, stdDeque, mesg_str);
  }

//  @Test
  public void testAddFirst() {
    // `stuDeque` is the deque being tested.
    StudentArrayDeque<Integer> stuDeque = new StudentArrayDeque<>();

    // `stdDeque` is the correct reference deque.
    ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<>();

    // `mesg` stores the series of method calls.
    StringBuilder mesg = new StringBuilder();

    for (int i = 0; i < 100; i += 1) {
      testAddFirstOnce(stuDeque, stdDeque, mesg);
    }
  }

  private void testAddLastOnce(
      StudentArrayDeque<Integer> stuDeque, ArrayDequeSolution<Integer> stdDeque,
      StringBuilder mesg
  ) {
    int item = StdRandom.uniform(10);
    stdDeque.addLast(item);
    stuDeque.addLast(item);

    mesg.append("addLast(").append(item).append(")\n");
    String mesg_str = mesg.toString();

    assertEquals(mesg_str, stdDeque.size(), stuDeque.size());

    compEntireDeque(stuDeque, stdDeque, mesg_str);
  }

//  @Test
  public void testAddLast() {
    // `stuDeque` is the deque being tested.
    StudentArrayDeque<Integer> stuDeque = new StudentArrayDeque<>();

    // `stdDeque` is the correct reference deque.
    ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<>();

    // `mesg` stores the series of method calls.
    StringBuilder mesg = new StringBuilder();

    for (int i = 0; i < 100; i += 1) {
      testAddLastOnce(stuDeque, stdDeque, mesg);
    }
  }

  private void testRemoveFirstOnce(
      StudentArrayDeque<Integer> stuDeque, ArrayDequeSolution<Integer> stdDeque,
      StringBuilder mesg
  ) {
    Integer expect = stdDeque.removeFirst();
    Integer actual = stuDeque.removeFirst();

    mesg.append("removeFirst()\n");
    String mesg_str = mesg.toString();

    assertEquals(mesg_str, stdDeque.size(), stuDeque.size());

    assertEquals(mesg_str, expect, actual);

    compEntireDeque(stuDeque, stdDeque, mesg_str);
  }

//  @Test
  public void testRemoveFirst() {
    // `stuDeque` is the deque being tested.
    StudentArrayDeque<Integer> stuDeque = new StudentArrayDeque<>();

    // `stdDeque` is the correct reference deque.
    ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<>();

    // `mesg` stores the series of method calls.
    StringBuilder mesg = new StringBuilder();

    for (int i = 0; i < 200; i += 1) {
      double numberBetweenZeroAndOne = StdRandom.uniform();
      if (numberBetweenZeroAndOne < 0.5) {
        stuDeque.addFirst(i);
        stdDeque.addFirst(i);
      }
    }

    for (int i = 0; i < 100; i += 1) {
      testRemoveFirstOnce(stuDeque, stdDeque, mesg);
    }
  }

  private void testRemoveLastOnce(
      StudentArrayDeque<Integer> stuDeque, ArrayDequeSolution<Integer> stdDeque,
      StringBuilder mesg
  ) {
    Integer expect = stdDeque.removeLast();
    Integer actual = stuDeque.removeLast();

    mesg.append("removeLast()\n");
    String mesg_str = mesg.toString();

    assertEquals(mesg_str, stdDeque.size(), stuDeque.size());

    assertEquals(mesg_str, expect, actual);

    compEntireDeque(stuDeque, stdDeque, mesg_str);
  }

//  @Test
  public void testRemoveLast() {
    // `stuDeque` is the deque being tested.
    StudentArrayDeque<Integer> stuDeque = new StudentArrayDeque<>();

    // `stdDeque` is the correct reference deque.
    ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<>();

    // `mesg` stores the series of method calls.
    StringBuilder mesg = new StringBuilder();

    for (int i = 0; i < 200; i += 1) {
      double numberBetweenZeroAndOne = StdRandom.uniform();
      if (numberBetweenZeroAndOne < 0.5) {
        stuDeque.addLast(i);
        stdDeque.addLast(i);
      }
    }

    for (int i = 0; i < 100; i += 1) {
      testRemoveLastOnce(stuDeque, stdDeque, mesg);
    }
  }

  @Test
  public void testRandOps() {
    // `stuDeque` is the deque being tested.
    StudentArrayDeque<Integer> stuDeque = new StudentArrayDeque<>();

    // `stdDeque` is the correct reference deque.
    ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<>();

    // `mesg` stores the series of method calls.
    StringBuilder mesg = new StringBuilder();

    for (int i = 0; i < 400; i++) {
      int op = StdRandom.uniform(4);
      switch (op) {
        case 0:
          testAddFirstOnce(stuDeque, stdDeque, mesg);
          break;
        case 1:
          testAddLastOnce(stuDeque, stdDeque, mesg);
          break;
        case 2:
          testRemoveFirstOnce(stuDeque, stdDeque, mesg);
          break;
        case 3:
          testRemoveLastOnce(stuDeque, stdDeque, mesg);
          break;
      }
    }
  }
}

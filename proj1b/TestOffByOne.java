import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {
  // You must use this CharacterComparator and not instantiate
  // new ones, or the autograder might be upset.
  static CharacterComparator offByOne = new OffByOne();

  @Test
  public void testOffByOne() {
    assertTrue(offByOne.equalChars('a', 'b'));
    assertTrue(offByOne.equalChars('b', 'a'));

    assertTrue(offByOne.equalChars('g', 'h'));
    assertTrue(offByOne.equalChars('h', 'g'));

    assertFalse(offByOne.equalChars('a', 'e'));
    assertFalse(offByOne.equalChars('z', 'a'));
    assertFalse(offByOne.equalChars('a', 'a'));

    assertFalse(offByOne.equalChars('a', 'B'));

    assertTrue(offByOne.equalChars('&', '%'));
    assertTrue(offByOne.equalChars('z', '{'));

    assertFalse(offByOne.equalChars('&', '}'));
  }

  // Your tests go here.
  /* Uncomment this class once you've created your CharacterComparator interface and OffByOne class. */
}

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
  // You must use this palindrome, and not instantiate
  // new Palindromes, or the autograder might be upset.
  static Palindrome palindrome = new Palindrome();

  @Test
  public void testWordToDeque() {
    Deque d = palindrome.wordToDeque("persiflage");
    String actual = "";
    for (int i = 0; i < "persiflage".length(); i++) {
      actual += d.removeFirst();
    }
    assertEquals("persiflage", actual);
  } /* Uncomment this class once you've created your Palindrome class. */

  @Test
  public void testIsPalindrome() {
    assertTrue(palindrome.isPalindrome(""));

    assertTrue(palindrome.isPalindrome("a"));
    assertTrue(palindrome.isPalindrome("z"));

    assertFalse(palindrome.isPalindrome("cat"));
    assertTrue(palindrome.isPalindrome("racecar"));

    assertFalse(palindrome.isPalindrome("Racecar"));
  }

  @Test
  public void testIsPalindromeOffByOne() {
    CharacterComparator cc = new OffByOne();
    assertTrue(palindrome.isPalindrome("", cc));

    assertTrue(palindrome.isPalindrome("a", cc));
    assertTrue(palindrome.isPalindrome("z", cc));

    assertFalse(palindrome.isPalindrome("cat", cc));
    assertTrue(palindrome.isPalindrome("flake", cc));

    assertFalse(palindrome.isPalindrome("Flake", cc));
    assertTrue(palindrome.isPalindrome("&lak%", cc));
  }
}

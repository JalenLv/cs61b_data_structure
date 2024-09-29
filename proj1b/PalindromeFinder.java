/**
 * This class outputs all palindromes in the words file in the current directory.
 */
public class PalindromeFinder {
  public static void main(String[] args) {
    int minLength = 4;
    In in = new In("../library-sp18/data/words.txt");
    Palindrome palindrome = new Palindrome();

//    while (!in.isEmpty()) {
//      String word = in.readString();
//      if (word.length() >= minLength && palindrome.isPalindrome(word)) {
//        System.out.println(word);
//      }
//    }

    /**
     * finds all palindromes in the words file that are off by one character
     */
//    CharacterComparator cc = new OffByOne();
//    while (!in.isEmpty()) {
//      String word = in.readString();
//      if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
//        System.out.println(word);
//      }
//    }

    /**
     * finds all palindromes in the words file that are off by N characters
     */
    CharacterComparator ccN = new OffByN(5);
    while (!in.isEmpty()) {
      String word = in.readString();
      if (word.length() >= minLength && palindrome.isPalindrome(word, ccN)) {
        System.out.println(word);
      }
    }
  } /* Uncomment this class once you've written isPalindrome. */
}

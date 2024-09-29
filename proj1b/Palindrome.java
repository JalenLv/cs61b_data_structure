public class Palindrome {
  public Deque<Character> wordToDeque(String word) {
    char[] tempCharArray = word.toCharArray();
    Deque<Character> x = new LinkedListDeque<Character>();
    for (char c : tempCharArray) {
      x.addLast(c);
    }
    return x;
  }

  public boolean isPalindrome(String word) {
    if (word.length() < 2)
      return true;

    Deque<Character> x = wordToDeque(word);
    while (x.size() > 1) {
      char first = x.removeFirst();
      char last = x.removeLast();
      if (first != last)
        return false;
    }
    return true;
  }

  public boolean isPalindrome(String word, CharacterComparator cc) {
    if (word.length() < 2)
      return true;

    Deque<Character> x = wordToDeque(word);
    while (x.size() > 1) {
      char first = x.removeFirst();
      char last = x.removeLast();
      if (!cc.equalChars(first, last))
        return false;
    }
    return true;
  }
}

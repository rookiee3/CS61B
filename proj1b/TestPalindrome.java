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
    }

    @Test
    public void testisPalindrome() {
        //length = 0
        assertTrue(palindrome.isPalindrome(""));
        System.out.println("case 1 passed");
        //length = 1
        assertTrue(palindrome.isPalindrome("a"));
        System.out.println("case 2 passed");
        //normal condition
        assertTrue(palindrome.isPalindrome("aa"));
        System.out.println("case 3 passed");

        assertTrue(palindrome.isPalindrome("aba"));
        System.out.println("case 4 passed");

        assertTrue(palindrome.isPalindrome("abba"));
        System.out.println("case 5 passed");

        assertFalse(palindrome.isPalindrome("abc"));
        System.out.println("case 6 passed");

        assertFalse(palindrome.isPalindrome("cat"));
        System.out.println("case 7 passed");

        assertFalse(palindrome.isPalindrome("Aa"));
        System.out.println("case 8 passed");
    }

    @Test
    public void testOverloadedisPalindrome_OffByOne() {

        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        System.out.println("case 1 passed");

        assertFalse(palindrome.isPalindrome("abcde", obo));
        System.out.println("case 2 passed");

        assertTrue(palindrome.isPalindrome("acdb", obo));
        System.out.println("case 3 passed");

        assertTrue(palindrome.isPalindrome("a", obo));
        System.out.println("case 4 passed");

        assertFalse(palindrome.isPalindrome("aa", obo));
        System.out.println("case 5 passed");

        assertTrue(palindrome.isPalindrome("fe", obo));
        System.out.println("case 6 passed");

        assertTrue(palindrome.isPalindrome("", obo));
        System.out.println("case 7 passed");

        assertTrue(palindrome.isPalindrome("1432", obo));
        System.out.println("case 8 passed");

    }
}

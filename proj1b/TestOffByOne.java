import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testequalChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        System.out.println("case1 passed");

        assertFalse(offByOne.equalChars('a', 'c'));
        System.out.println("case2 passed");

        assertTrue(offByOne.equalChars('&', '%'));
        System.out.println("case3 passed");

        assertFalse(offByOne.equalChars(' ', '0'));
        System.out.println("case4 passed");

        assertFalse(offByOne.equalChars('a', 'A'));
        System.out.println("case5 passed");
    }
}

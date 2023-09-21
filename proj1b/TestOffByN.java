import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator ob5 = new OffByN(5);

    @Test
    public void testequalChars() {
        assertTrue(ob5.equalChars('a', 'f'));
        System.out.println("case1 passed");

        assertTrue(ob5.equalChars('f', 'a'));
        System.out.println("case2 passed");

        assertFalse(ob5.equalChars('f', 'h'));
        System.out.println("case3 passed");
    }

}

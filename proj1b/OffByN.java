public class OffByN implements CharacterComparator {

    private int n;

    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (int)x - (int)y == n || (int)x - (int)y == -n;
    }

}

package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private double frequency;
    private int state;
    private int period;
    private int temp;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        temp = 0;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
        temp = state & (state >>> 3) % period;
        return normalize(temp);
    }

    private double normalize(int x) {
        return (double) (2 * x) / period - 1;
    }
}

package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator{
    private double frequency;
    private int state;
    private int period;

    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
        return normalize(state % period);
    }

    private double normalize(int x) {
        return (double) (2 * x) / period - 1;
    }
}

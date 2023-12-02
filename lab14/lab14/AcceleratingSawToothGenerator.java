package lab14;

import lab14lib.GeneratorDrawer;
import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator{
    private int state;
    private int period;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double acc) {
        state = 0;
        this.period = period;
        this.factor = period / 10 * acc;
    }

    public double next() {
        state = (state + 1);
        if (state == period) change();
        return normalize(state % period);
    }

    private double normalize(int x) {
        return (double) (2 * x) / period - 1;
    }

    private double change() {
        state = 0;
        period += factor;
        return normalize(state % period);
    }
}

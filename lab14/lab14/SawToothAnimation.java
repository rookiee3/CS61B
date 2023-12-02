package lab14;

import lab14lib.Generator;
import lab14lib.GeneratorAudioAnimator;

public class SawToothAnimation {
    public static void main(String[] args) {
        Generator generator = new SawToothGenerator(512);
        GeneratorAudioAnimator ga = new GeneratorAudioAnimator(generator);
        ga.drawAndPlay(500,400000);
    }
}

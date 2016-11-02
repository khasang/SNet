package io.khasang.snet.entity.common;

import io.khasang.snet.util.Generator;

import java.util.Random;

/**
 * Generates Strings
 */
public class StringGenerator implements Generator<String> {

    private Random random;

    public StringGenerator() {
        random = new Random(42);
    }

    @Override
    public String create() {
        int str_lenght = random.nextInt(1000);
        char[] seq = new char[str_lenght];

        for (int i = 0; i < str_lenght; i++) {
            Character c;
            do {
                c  = (char) random.nextInt(255);
            } while (!Character.isLetterOrDigit(c));
            seq[i] = c;
        }
        return new String(seq);
    }

}

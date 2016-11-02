package io.khasang.snet.entity.common;

import io.khasang.snet.entity.Picture;
import io.khasang.snet.util.Generator;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Random;

/**
 * Generate random Picture objects
 */
@Component
public class PicturesGenerator implements Generator<Picture>, Iterable<Picture> {

    private final String desc = "Random generated picture entity instance.";
    private int amountOfPictures;
    private Random random;

    public PicturesGenerator() {
        this.random = new Random(42);
    }

    public PicturesGenerator(int amountOfPictures) {
        this.random = new Random(42);
        this.amountOfPictures = amountOfPictures;
    }

    @Override
    public Picture create() {
        return new Picture(desc,getRandomGeneratedByteArray(random.nextInt(10240)),random.nextLong());
    }

    @Override
    public Iterator<Picture> iterator() {
        return new Iterator<Picture>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < amountOfPictures;
            }

            @Override
            public Picture next() {
                index++;
                return PicturesGenerator.this.create();
            }
        };
    }

    private byte[] getRandomGeneratedByteArray(int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = (byte) random.nextInt(255);
        }
        return result;
    }

}

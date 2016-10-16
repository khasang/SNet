package io.khasang.snet.service.common;

import io.khasang.snet.entity.CityLocation;
import io.khasang.snet.util.Generator;

import java.util.Iterator;
import java.util.Random;

/**
 * Generator CityLocation's instances
 */
public class CityLocationGenerator implements Generator<CityLocation>, Iterable<CityLocation> {

    private static long count = 0;
    private final long id = count++;
    private Random random;
    private Cities[] cities;
    private int amount;

    public CityLocationGenerator(int amount) {
        this.amount = amount;
        random = new Random(42);
        cities = Cities.values();
    }

    public CityLocationGenerator() {
        this.amount = 0;
        random = new Random(42);
        cities = Cities.values();
    }

    @Override
    public CityLocation create() {
        CityLocation location = new CityLocation();
        Cities randomCity = getRandomCity();
        location.setId(id);
        location.setCityName(randomCity.city);
        location.setLocation(randomCity.location);
        return location;
    }

    @Override
    public Iterator<CityLocation> iterator() {
        return new Iterator<CityLocation>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < amount;
            }

            @Override
            public CityLocation next() {
                index++;
                return CityLocationGenerator.this.create();
            }
        };
    }

    private Cities getRandomCity() {
        return cities[random.nextInt(cities.length)];
    }
}

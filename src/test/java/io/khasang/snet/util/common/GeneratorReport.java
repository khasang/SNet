package io.khasang.snet.util.common;

import io.khasang.snet.entity.WeatherReport;
import io.khasang.snet.util.Generator;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;
import java.util.Random;

@Component
public class GeneratorReport implements Generator<WeatherReport> {
    private Random random;

    public GeneratorReport() {
        this.random = new Random(20);
    }

    @Override
    public WeatherReport create() {
        return new WeatherReport(random.nextLong(), random.nextFloat(), random.nextFloat(), new GregorianCalendar());
    }
}

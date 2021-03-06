package io.khasang.snet.entity.common;

import io.khasang.snet.entity.WeatherReport;
import io.khasang.snet.util.Generator;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;
import java.util.Random;

@Component
public class ReportGenerator implements Generator<WeatherReport> {
    private Random random;

    public ReportGenerator() {
        this.random = new Random(20);
    }

    @Override
    public WeatherReport create() {
        return new WeatherReport(random.nextLong(), random.nextFloat(), random.nextFloat(), new GregorianCalendar());
    }
}

package io.khasang.snet.service.common;

/**
 * Makers of cities
 */
enum Cities {
    one("Saint-Petersburg",new int[]{30,60}), two("Helsinki",new int[]{25,60}), three("Tallin",new int[]{26,59}),
    four("Narva",new int[]{28,60}), five("Viipri",new int[]{30,60}), six("Riga",new int[]{25,55});

    String city;
    int[] location;

    Cities(String city, int[] location) {
        this.city = city;
        this.location = location;
    }
}

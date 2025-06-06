package com.dreamlabs.osm.pbf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        var random = new Random();
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            if (i % 2 == 0) {
                objects.add(i);
            } else if (i % 3 == 0) {
                objects.add(String.valueOf(random.nextInt()));
            } else {
                objects.add(random.nextDouble());
            }
        }

        long start = System.currentTimeMillis();
        for (Object object : objects) {
            switch (object) {
                case Integer i -> System.out.println("int");
                case String s -> System.out.println("String");
                case Double d -> System.out.println("Double");
                default -> System.out.println("Unrecognized type: " + object.getClass());
            }
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("Elapsed time %d", elapsed);
    }
}

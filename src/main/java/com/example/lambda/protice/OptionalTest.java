package com.example.lambda.protice;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalTest {
    public static void main(String[] args) {
//        Stream<String> stringStream = Stream.of("Lamurudu", "Okanbi", "Oduduwa", null);
//        stringStream
//                .map(item -> {
//                    if (item == null) {
//                        return "返回值为空!";
//                    }
//                    return item;
//                })
//                .collect(Collectors.toList())
//                .forEach(System.out::println);


        Optional<String> optionalS = Optional.ofNullable(test("abc"));

        optionalS.ifPresent(System.out::println);

        Optional.of(null);

    }

    private static String test(String str) {
        return str;
    }
}

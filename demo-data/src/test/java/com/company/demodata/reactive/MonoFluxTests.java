package com.company.demodata.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxOperator;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class MonoFluxTests {
    @Test
    public void givenMono_whenSubscribeThenReturnSingleValue() {
        Mono<String> helloMono = Mono.just("Hello");
        StepVerifier.create(helloMono)
                .expectNext("Hello")
                .verifyComplete();
    }

    @Test
    public void givenFlux_whenSubscribeThenReturnMultipleValue() {
        Flux<String> stringFlux = Flux.just("Hello", "World");
        StepVerifier.create(stringFlux)
                .expectNext("Hello")
                .expectNext("World")
                .verifyComplete();
    }

    @Test
    public void givenFluxPublisher_whenSubscribeThenReturnMultipleValuesWithError(){
        Flux<String> stringFlux = Flux.just("Hello", "Spring Reactive", "Error")
                .map(str -> {
                    if (str.equals("Error"))
                        throw new RuntimeException("Throwing Error");
                    return str; });
        StepVerifier
                .create(stringFlux)
                .expectNext("Hello")
                .expectNext("Spring Reactive")
                .expectError()
                .verify();
    }
}

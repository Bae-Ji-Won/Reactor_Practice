package com.example;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class Operator4Test {

    Operator4 op = new Operator4();

    @Test
    void fluxDelayAndLimit() {
        StepVerifier.create(op.fluxDelayAndLimit())
                .expectNext(1,2,3,4,5,6,7,8,9,10)
                .verifyComplete();
    }

    @Test
    void fluxSample() {
        StepVerifier.create(op.fluxSample())
                .expectNextCount(1000)
                .verifyComplete();
    }
}
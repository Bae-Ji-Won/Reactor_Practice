package com.example;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Operator4 {
    // limit    : 출력 개수 제한
    public Flux<Integer> fluxDelayAndLimit(){
        return Flux.range(1,10)
                .delaySequence(Duration.ofSeconds(2))   // 값 출력하기 전 2초의 지연시간을 지정
                .log()
                .limitRate(2);      // 2개씩 출력하도록 제한
    }

    // sample
    // 
    public Flux<Integer> fluxSample(){
        return Flux.range(1,100)
                .delayElements(Duration.ofMillis(100))  // 값 출력 전 100밀리초의 지연을 추가
                .sample(Duration.ofMillis(300))     // 지정된 주기(300밀리초)동안 마지막으로 방출된 요소만 샘플링 후 반환
                .log();
    }
}

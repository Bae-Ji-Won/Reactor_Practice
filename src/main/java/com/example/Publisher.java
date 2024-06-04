package com.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Publisher {

    /* Flux : 0개에서 여러 개의 결과를 방출할 수 있는 반응형 스트림입니다.
     데이터 스트림이 끝없이 계속될 수도 있으며, 여러 데이터를 처리해야 할 때 사용됩니다.
     예를 들어, 여러 개의 데이터 항목을 반환하는 데이터베이스 쿼리나, 스트림으로 데이터를 연속적으로 처리해야 할 경우 Flux를 사용합니다.
    */

    public Flux<Integer> startFlux(){
        // 1부터 10까지의 정수를 차례로 방출
        return Flux.range(1,10).log();
    }

    public Flux<String> startFlux2(){
        return Flux.fromIterable(List.of("a","b","c","d")).log();
    }

    /* Mono : 0 또는 1개의 결과만을 방출할 수 있는 반응형 스트림입니다.
    즉, Mono는 최대 한 개의 데이터를 처리할 때 사용됩니다.
    예를 들어, 데이터베이스에서 하나의 항목을 조회하거나, 단일 값을 반환하는 HTTP 요청을 처리할 때 Mono를 사용할 수 있습니다.
     */

    public Mono<Integer> startMono(){
        // 단 하나의 값인 1을 방출하는 Mono를 생성
        return Mono.just(1).log();
    }

    public Mono<?> startMono2(){
        //  아무 값도 방출하지 않는
        return Mono.empty().log();
    }

    public Mono<?> startMono3(){
        return Mono.error(new Exception("Error"));
    }
}

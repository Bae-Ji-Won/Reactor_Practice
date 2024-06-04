package com.example;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Operator1 {

    // map
    /*
        입력받는 값을 원하는 형태로 변환하여 출력
     */
    public Flux<Integer> fluxMap(){
        return Flux.range(1,5)
                .map(i -> i *2)
                .log();
    }

    // filter
    /*
        if문과 같이 필터를 적용시켜 원하는 값만 출력되도록 설정
     */
    public Flux<Integer> fluxFilter(){
        return Flux.range(1,10)
                .filter(i -> i > 5)
                .log();
    }

    // take
    /*
        지정한 개수만 출력하도록 설정
     */
    public Flux<Integer> fluxFilterTake(){
        return Flux.range(1,10)
                .filter(i -> i > 5)
                .take(3)
                .log();
    }

    // flatmap
    // 2중 for문과 비슷한 구조임
    // 처음 i=1일때, 1*10 = 10부터 10의 값을 모두 출력함
    // 따라서 10~109까지의 값이 출력됨
    /*
        flatMap은 각 원본 요소를 새로운 Flux로 변환하고, 이 Flux들을 병합하여 단일 Flux로 만듭니다.
        병합된 Flux들은 비동기적으로 실행될 수 있으며, 순서를 보장하지 않습니다. 여러 작업을 병렬로 수행하고자 할 때 유용합니다.
     */
    public Flux<Integer> fluxFlatMap(){
        return Flux.range(1,10)
                .flatMap(i -> Flux.range(i*10,10)
                        .delayElements(Duration.ofMillis(100)))
                .log();
    }

}

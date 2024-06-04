package com.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Operator2 {

    // concatmap
    /* flatmap과 동작 과정은 같지만
     concatMap은 각 원본 요소를 새로운 Flux로 변환하고, 이 Flux들을 순차적으로 연결하여 단일 Flux로 만듭니다.
     변환된 Flux들은 순차적으로 실행되며, 순서가 보장됩니다.
     순서를 지키면서 작업을 수행하고자 할 때 유용합니다.
     */
    public Flux<Integer> fluxConcatMap(){
        return Flux.range(1,10)
                .concatMap(i -> Flux.range(i*10, 10)
                        .delayElements(Duration.ofMillis(100)))
                .log();
    }

    // flatmapmany : mono to flux로 만드는 연산자
    public Flux<Integer> monoFluxMapMany(){
        return Mono.just(10)
                .flatMapMany(i -> Flux.range(1, i))
                .log();
    }

    // defaultifempty : filter를 통과하지 않아 반환되는 값이 없을때 default값을 지정하여 해당 값을 반환
    public Mono<Integer> defaultIfEmpty(){
        return Mono.just(100)
                    .filter(i -> i > 100)
                    .defaultIfEmpty(30);
    }

    // switchifempty : defalutifempty와 구조는 같습니다. 반환하는 값이 없을때 지정한 값을 반환해 주는데
    // 이때, 단일 기본값을 반환하지 않고 Mono, Flux로 대체가 가능하며 복잡한 로직이나 여러 값을 반환할때 사용합니다.
    public Mono<Integer> switchIfEmpty1(){
        return Mono.just(100)
                .filter(i -> i > 100)
                .switchIfEmpty(Mono.just(30).map(i -> i*2));
    }

    public Mono<Integer> switchIfEmpty2(){
        return Mono.just(100)
                .filter(i -> i > 100)
                .switchIfEmpty(Mono.error(new RuntimeException("Not exists value...")));
    }

    // merge : 여러개의 Flux를 병합하여 하나의 Flux 생성
    // 순서를 보장하지 않음, 만약 순서보장을 원한다면 concat사용
    public Flux<String> fluxMerge(){
        return Flux.merge(Flux.fromIterable(List.of("1","2","3")),Flux.just("4"))
                .log();
    }

    public Flux<String> monoMerge(){
        return Mono.just("1").mergeWith(Mono.just("2")).mergeWith(Mono.just("3"));
    }

    /*  zip : merge와 같은 구조로 2개의 값을 하나로 뭉치는 것인데 전부 뭉치는 것이 아니라
    본인이 원하는 구조로 합칠 수 있음
    예) (a,b,c) (d,e,f)가 주어졌을때 (a,d)(b,e)(c,f)로 묶을 수 있음
     */
    public Flux<String> fluxZip(){
       return Flux.zip(Flux.just("a","b","c"),Flux.just("d","e","f"))
                .map(i -> i.getT1() + i.getT2())    // getT1 : "a","b,"c"중 첫번째 값인 "a"
                                                    // getT2 : "d","e,"f"중 첫번째 값인 "d"
                .log();
    }

    public Mono<Integer> monoZip(){
        return Mono.zip(Mono.just(1), Mono.just(2),Mono.just(3))
                .map(i -> i.getT1() + i.getT2() + i.getT3());
    }
}

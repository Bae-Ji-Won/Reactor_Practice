package com.example;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Scheduler1 {
    /*
     subscribeOn(Schedulers.boundedElastic())은 Flux의 구독이 boundedElastic 스케줄러에서 실행되도록 합니다.
     이는 전체 Flux 파이프라인이 지정된 스케줄러에서 실행됨을 의미합니다. 따라서 모든 map 연산과 최종 구독 처리는 boundedElastic 스케줄러의 스레드에서 실행됩니다.
     */
    public Flux<Integer> fluxMapWithSubscribeOn(){
        return Flux.range(1,10)
                .map(i -> i*2)
                .subscribeOn(Schedulers.boundedElastic())
                .log()
                .subscribeOn(Schedulers.parallel())
                .log();
    }

    /*
    publishOn(Schedulers.parallel())은 publishOn을 호출한 시점 이후의 연산이 parallel 스케줄러에서 실행되도록 합니다.
    첫 번째 map 연산은 호출 스레드(일반적으로 main 스레드)에서 실행되고, publishOn 이후의 연산은 parallel 스케줄러의 스레드에서 실행됩니다.
    
    boundedElastic 스레드와 publishOn 스레드를 동시에 사용함
    따라서 하나의 스트림 체인에서 여러 번의 스레드 변경이 필요한 경우 여러 번 사용될 수 있습니다.
     */
    public Flux<Integer> fluxMapWithPublishOn(){
        return Flux.range(1,10)
                .map(i -> i+1)
                .publishOn(Schedulers.boundedElastic())
                .log()
                .publishOn(Schedulers.parallel())
                .log()
                .map(i -> i*2);
    }
}

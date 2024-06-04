package com.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Operator3 {
    //count : 개수를 반환
    public Mono<Long> fluxCount(){
        return Flux.range(1,10).count();
    }

    // distinct : 중복 제거
    public Flux<String> fluxDistinct(){
        return Flux.fromIterable(List.of("a","b","a","b","c"))
                .distinct();
    }

    // reduce : 이전 값을 다음 수식에도 더해줌 1,2,3 => 1+2 = 3 , 3+3 = 6
    public Mono<Integer> fluxReduce(){
    return Flux.range(1,10)
                .reduce((i,j)->i+j)
                .log();
    }

    // groupby : 조건에 따라 그룹을 나누고 그룹별로 값을 더해 별로의 값을 출력할 수 있음
    // 예) (1,2,3,4,5)일때, (1,3,5)(2,4)로 그룹을 나누고 (1+3+5)(2+4) => (9)(6)으로 출력 가능
    public Flux<Integer> fluxGroupBy(){
        return Flux.range(1,10)
                .groupBy(i -> (i % 2 == 0) ? "even": "odd")
                .flatMap(group -> group.reduce((i,j) -> i+j))
                .log();
    }
}
